package one.dao.json;

import one.dao.DaoException;
import one.dao.PersonDao;
import one.model.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JSONDataAccessObject -> klasa koja demonstrira čitanje i pisanje objekat u/iz JSON fajla.
 */
public class PersonJsonDao implements PersonDao {

    static final String FILE_NAME = "person.json";

    @Override
    public List<Person> readObjects() throws DaoException {
        try(FileReader fr = new FileReader(FILE_NAME)){
            //JSONObject -> Person
            //JSONArray -> List<Person>
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray)jsonParser.parse(fr);
            List<Person> personList = new ArrayList<>();
            for(int i = 0; i<jsonArray.size(); i++){
                JSONObject jsonObject =(JSONObject) jsonArray.get(i);
                Person person = new Person();
                person.setNationalIdentificationNumber(Integer.parseInt(jsonObject.get("nin").toString()));
                person.setName(jsonObject.get("name").toString());
                person.setSurname(jsonObject.get("surname").toString());
                person.setAge(Integer.parseInt(jsonObject.get("age").toString()));
                personList.add(person);
            }
            return personList;
        }catch (Exception e){
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void writeObjects(List<Person> objects) throws DaoException {
        if(isInvalidInput(objects)){
            return;
        }
        //List<Person>-> JSONArray
        //Person -> JSONObject
        JSONArray jsonArray = new JSONArray();
        for(Person person : objects){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("nin",person.getNationalIdentificationNumber());
            jsonObject.put("name", person.getName());
            jsonObject.put("surname", person.getSurname());
            jsonObject.put("age", person.getAge());
            jsonArray.add(jsonObject);
        }
        String jsonString = jsonArray.toJSONString();//OVAJ STRING upisujemo u fajl
        try(FileWriter fw = new FileWriter(FILE_NAME)) {
            fw.write(jsonString);
        }catch (IOException e){
            throw new DaoException(e.getMessage());
        }

    }
}
