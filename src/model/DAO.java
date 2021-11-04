package model;

import java.sql.Connection;
import java.util.List;

public interface DAO<T> {
    T get(long id);
    List<T> getAll(Connection conn);
}