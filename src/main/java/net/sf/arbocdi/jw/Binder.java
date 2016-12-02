package net.sf.arbocdi.jw;

import javax.inject.Scope;
import javax.inject.Singleton;
import net.sf.arbocdi.jw.data.InMemoryPersonStore;
import net.sf.arbocdi.jw.data.PersonStore;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 *
 * @author arbocdi
 */
public class Binder extends AbstractBinder {

    @Override
    protected void configure() {
        this.bind(InMemoryPersonStore.class).to(PersonStore.class).in(Singleton.class);
    }

}
