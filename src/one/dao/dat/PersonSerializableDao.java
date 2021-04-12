package one.dao.dat;

import one.dao.DaoException;
import one.dao.PersonDao;
import one.model.Person;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PersonSerializableDao implements PersonDao {
    static final String DATOTEKA = "person.dat";

    @Override
    public List<Person> readObjects() throws DaoException {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATOTEKA))){
            return (ArrayList<Person>) ois.readObject();
        }catch (Exception exception){
            throw new DaoException(exception.getMessage());
        }
    }

    @Override
    public void writeObjects(List<Person> objects) throws DaoException {
        if(isInvalidInput(objects)){
            return;
        }
        System.out.println("POZVAN sam ja SerializableDao");
        //ObjectOutputStream, FileOutputStream
        try(ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(DATOTEKA))){
            ous.writeObject(objects);
        }catch (Exception exception){
            throw new DaoException(exception.getMessage());
        }
    }
}
