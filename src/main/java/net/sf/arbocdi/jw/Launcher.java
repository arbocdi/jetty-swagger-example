package net.sf.arbocdi.jw;

//import io.swagger.jaxrs.config.DefaultJaxrsConfig;
import io.swagger.jaxrs.config.DefaultJaxrsConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 *
 * @author arbocdi
 */
public class Launcher {

    public static void main(String[] args) throws Exception {
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.addHandler(buildSwaggerUI());
        
        // Формирование контекста сервлетов
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        contexts.addHandler(context);

        // Главный ресурс
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig
                .register(JacksonFeature.class)
                .packages(new String[]{"net.sf.arbocdi.jw.rest", "io.swagger.jaxrs.json", "io.swagger.jaxrs.listing"})
                .register(new Binder());
        
        ServletContainer sc = new ServletContainer(resourceConfig);
        ServletHolder holder = new ServletHolder(sc);
        
        context.addServlet(holder, "/api/*");
        
        // Setup Swagger servlet
        ServletHolder swaggerServlet = context.addServlet(DefaultJaxrsConfig.class, "/swagger-core");
        swaggerServlet.setInitOrder(2);
        swaggerServlet.setInitParameter("api.version", "1.0.0");


        
        // Стартуем
        Server jettyServer = new Server(new QueuedThreadPool(128, 8));
        ServerConnector http = new ServerConnector(jettyServer);
        http.setPort(8383);
        jettyServer.addConnector(http);
        jettyServer.setHandler(contexts);

        try {
            jettyServer.start();
            jettyServer.join();

        } finally {
            //jettyServer.destroy();
        }
    }

    public static ContextHandler buildSwaggerUI() throws Exception {
        ResourceHandler rh = new ResourceHandler();
        rh.setResourceBase(Launcher.class.getClassLoader()
                .getResource("META-INF/resources/webjars/swagger-ui/2.1.4")
                .toURI().toString());
        ContextHandler context = new ContextHandler();
        context.setContextPath("/docs/");
        context.setHandler(rh);
        return context;
    }

   
}
