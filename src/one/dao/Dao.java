package one.dao;

import java.util.List;

public interface Dao<E> {
    /**
     * <p>Ove metoda treba da pročita objekte trajno snimljene
     * u nekom mediju i da nam vrati listu učitanih objekata</p>
     *
     * @return objects
     */
    public List<E> readObjects() throws DaoException;

    /**
     * <p>Ova metoda treba da snimi trajno objekt
     * koje je dobila kao parametar u neki medij</p>
     *
     * @param objects
     */
    public void writeObjects(List<E> objects) throws DaoException;

}
