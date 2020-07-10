package com.link.common.persist.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.link.common.persist.repository.BaseRepository;


public interface BaseService<T,ID extends Serializable, R extends BaseRepository<T,ID>>
{
    /**
             * 获取Repository
     * @return
     */
    R getRepository();
    
    T saveOne(T entity); 
    <TT,TID extends Serializable> TT saveOne(BaseRepository<TT, TID> baseRepository, TT entity);
    
    T updateOne(ID id, T entity);
    T updateOne(T entity, T db); 
    <TT,TID extends Serializable> TT updateOne(BaseRepository<TT, TID> baseRepository, TID id, TT entity);
    
    T saveOrUpdate(ID id, T t);
     
    void deleteById(ID id);
    
    Optional<T> findById(ID id);
     
    List<T> findAll();

    List<T> findAll(Example<T> example);
    
    Page<T> findAll(Pageable pageable);

    boolean exists(ID id);
    
}