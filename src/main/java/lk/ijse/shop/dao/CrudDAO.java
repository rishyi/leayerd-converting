package lk.ijse.shop.dao;

import java.sql.SQLException;

public interface CrudDAO<T> extends SuperDAO{

    public boolean add(T entity) throws SQLException, ClassNotFoundException;
    public boolean update(T entity) throws SQLException, ClassNotFoundException;
}
