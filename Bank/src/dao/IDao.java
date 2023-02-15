package dao;

import java.util.List;

public interface IDao<T,ID> {
    List<T> findAll();
    T findById(ID id);
    T save(T t);
    List<T> saveAll(List<T> t);
    T update(T t);
    boolean deleteByID(ID id);
    boolean delete(T t);

}
