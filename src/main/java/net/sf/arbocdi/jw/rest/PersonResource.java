package net.sf.arbocdi.jw.rest;

import io.swagger.annotations.Api;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import net.sf.arbocdi.jw.data.Person;
import net.sf.arbocdi.jw.data.PersonStore;

/**
 *
 * @author arbocdi
 */
@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Api(basePath = "/api/person") 
public class PersonResource {
    @Inject
    private PersonStore personStore;
    
    @GET
    @Path("{email}")
    public Person findPerson(@PathParam("email")String email) throws Exception{
        return this.personStore.findPerson(email);
    }
    @GET
    public List<Person> findPersons() throws Exception{
        return this.personStore.getPersons();
    }
    @DELETE
    @Path("{email}")
    public void deletePerson(@PathParam("email")String email) throws Exception{
        this.personStore.deletePerson(email);
    }
    @POST
    public void addPerson(Person person) throws Exception{
        this.personStore.addPerson(person);
    }
    

}
