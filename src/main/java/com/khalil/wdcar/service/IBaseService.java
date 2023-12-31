package com.khalil.wdcar.service;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<E, D extends Serializable> extends IRsqlService<E, D > {
    D save(D dto);

    D update(D dto);

    void delete(Long id);

    D findById(Long id);

    List<D> findAll();
}
