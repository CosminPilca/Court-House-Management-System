package Repository;

import java.util.List;

public interface IRepository<T> {
    void create(T entity);
    T read(String id);
    void update(T entity);
    void delete(String id);
    List<T> getAll();
}