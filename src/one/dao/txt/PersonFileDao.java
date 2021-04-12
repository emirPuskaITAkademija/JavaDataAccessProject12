package one.dao.txt;

import one.dao.DaoException;
import one.dao.PersonDao;
import one.model.Person;

import java.io.*;
import java.util.*;

public class PersonFileDao implements PersonDao {
    static final String FILE_NAME = "person.txt";

    @Override
    public List<Person> readObjects() throws DaoException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME));) {
            List<Person> objects = new ArrayList<>();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                //nin;name;surname;age
                //rrazbijemo liniju po tzv. token ;
                StringTokenizer stringTokenizer = new StringTokenizer(line, ";");
                int nin = Integer.parseInt(stringTokenizer.nextToken());
                String name = stringTokenizer.nextToken();
                String surname = stringTokenizer.nextToken();
                int age = Integer.parseInt(stringTokenizer.nextToken());
                Person object = new Person(nin, name, surname, age);
                objects.add(object);
            }
            return objects;
        } catch (IOException exception) {
            throw new DaoException(exception.getMessage());
        }
    }

    @Override
    public void writeObjects(List<Person> objects) throws DaoException {
        if (objects == null || objects.isEmpty()) {
            return;
        }
        System.out.println("POZVAN SAM JA FileDAO");
        //objects iz TRANZIJENTNO -> PERZISTENTNO
        //nin;name;surname;age
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(FILE_NAME));) {
            Iterator<Person> objectsIterator = objects.iterator();
            while (objectsIterator.hasNext()) {
                Person object = objectsIterator.next();
                String line = new StringJoiner(";")
                        .add(object.getNationalIdentificationNumber() + "")
                        .add(object.getName())
                        .add(object.getSurname())
                        .add(object.getAge() + "").toString();
                printWriter.println(line);
            }
        } catch (IOException e) {
            throw new DaoException(e.getMessage());
        }
    }
}
