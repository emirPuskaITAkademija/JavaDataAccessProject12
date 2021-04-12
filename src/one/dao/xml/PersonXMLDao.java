package one.dao.xml;

import one.dao.DaoException;
import one.dao.PersonDao;
import one.model.Person;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PersonXMLDao implements PersonDao {

    static final String FILE_NAME = "person.xml";

    @Override
    public List<Person> readObjects() throws DaoException {
        try(XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(FILE_NAME))){
            List<Person> personList = (List<Person>) xmlDecoder.readObject();
            return personList;
        }catch (IOException e){
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void writeObjects(List<Person> objects) throws DaoException {
        if(isInvalidInput(objects)){
            return;
        }
        try(XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(FILE_NAME))){
            xmlEncoder.writeObject(objects);
        }catch (IOException exception){
            System.err.println(exception.getMessage());
        }
    }
}
