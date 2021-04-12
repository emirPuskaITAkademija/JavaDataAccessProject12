package one.dao;

public class DaoException extends Exception{

    public DaoException(String message) {
        super("DAO Exception: " +message);
    }
}
