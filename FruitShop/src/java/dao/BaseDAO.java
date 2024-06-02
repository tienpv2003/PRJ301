package dao;
import java.util.List;
public interface BaseDAO<T> {
    T findById(int id);
    List<T> getAll();
    boolean insert(T obj);
    boolean update(T obj);
    boolean delete(String id);
}
