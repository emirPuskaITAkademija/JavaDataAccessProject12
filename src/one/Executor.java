package one;

import one.dao.DaoException;
import one.dao.PersonPersistanceStrategy;
import one.dao.dat.PersonSerializableDao;
import one.dao.json.PersonJsonDao;
import one.dao.txt.PersonFileDao;
import one.dao.xml.PersonXMLDao;
import one.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Executor {
    public static void main(String[] args) throws DaoException {
        Person albin = new Person(123, "Albin", "Fehrić", 23);
        Person melisa = new Person(321, "Melisa", "Herović", 13);
        Person adna = new Person(132, "Adna", "Kuduzović", 3);
        Person harun = new Person(312, "Harun", "Bucalović", 33);
        Person boris = new Person(213, "Borislav", "Domazet", 34);
        List<Person> personList = new ArrayList<>(Arrays.asList(albin, melisa, adna, harun, boris));

        //java.util.Arrays$ArrayList
        //java.util.ArrayList
        /**
         * trajno sačuvati podatke
         */
        PersonPersistanceStrategy persistanceStrategy = new PersonPersistanceStrategy(new PersonFileDao());
        persistanceStrategy.writePersons(personList);//person.txt
        persistanceStrategy.setPersistanceStrategy(new PersonSerializableDao());
        persistanceStrategy.writePersons(personList);//person.dat
        persistanceStrategy.setPersistanceStrategy(new PersonXMLDao());
        persistanceStrategy.writePersons(personList);//person.xml
        persistanceStrategy.setPersistanceStrategy(new PersonJsonDao());
        persistanceStrategy.writePersons(personList);//person.json
        /**
         * čitamo snimljene podatke
         */
        System.out.println("Čitamo iz TXT fajla");
        persistanceStrategy.setPersistanceStrategy(new PersonFileDao());
        for (Person person : persistanceStrategy.readPersons()) {
            System.out.println(person);
        }
        System.out.println();
        System.out.println("Čitamo iz DAT fajla");
        persistanceStrategy.setPersistanceStrategy(new PersonSerializableDao());
        for (Person person : persistanceStrategy.readPersons()) {
            System.out.println(person);
        }
        System.out.println();
        System.out.println("Čitamo iz XML fajla");
        persistanceStrategy.setPersistanceStrategy(new PersonXMLDao());
        persistanceStrategy.readPersons().forEach(System.out::println);

        System.out.println();
        System.out.println("Čitamo iz JSON fajla");
        persistanceStrategy.setPersistanceStrategy(new PersonJsonDao());
        persistanceStrategy.readPersons().forEach(System.out::println);

    }
}
