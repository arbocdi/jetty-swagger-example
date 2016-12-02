package net.sf.arbocdi.jw.data;

import java.util.List;

/**
 *
 * @author arbocdi
 */
public interface PersonStore {

    void addPerson(Person person) throws Exception;

    List<Person> getPersons() throws Exception;

    Person findPerson(String email) throws Exception;

    void deletePerson(String email) throws Exception;
}
