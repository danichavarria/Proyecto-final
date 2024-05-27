package org.danieska.services;

import org.danieska.dao.IGenericDao;

import java.util.List;

public interface IGenericService<T> extends IGenericDao<T> {
    List<T> getAll();
    void deleteAll();
    T getById(int i);
    T getId(Long i);
    T getByName(String name);
}
