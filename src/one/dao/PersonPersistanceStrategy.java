package one.dao;

import one.model.Person;

import java.util.List;

public class PersonPersistanceStrategy {
    //kompozicija
    private PersonDao persistanceStrategy;

    public PersonPersistanceStrategy(PersonDao personDao) {
        this.persistanceStrategy = personDao;
    }

    public void setPersistanceStrategy(PersonDao persistanceStrategy) {
        this.persistanceStrategy = persistanceStrategy;
    }

    public void writePersons(List<Person> personList) throws DaoException{
        persistanceStrategy.writeObjects(personList);
    }

    public List<Person> readPersons()throws DaoException{
        return persistanceStrategy.readObjects();
    }
}
