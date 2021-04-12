package one.dao;

import one.model.Person;

import java.util.List;

//MARKER interface
public interface PersonDao extends Dao<Person> {

    default boolean validate(List<Person> personList){
        return personList!=null && !personList.isEmpty();
    }

    default boolean isValidInput(List<Person> personList){
        return validate(personList);
    }

    default boolean isInvalidInput(List<Person> personList){
        return !isValidInput(personList);
    }

}
