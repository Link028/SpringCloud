package com.link.common.persist.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.link.common.persist.repository.BaseRepository;

public abstract class BaseServiceImpl<T,ID extends Serializable,R extends BaseRepository<T,ID>> implements BaseService<T,ID,R> 
{ 
	@Autowired
    protected R baseRepository;
    
    @Override
    public R getRepository() {
        return baseRepository;
    }
	 
    @Override
    public T saveOne(T entity) {
        return saveOne(baseRepository,entity);
    }
    
    @Override
    public <TT, TID extends Serializable> TT saveOne(BaseRepository<TT, TID> baseRepository, TT entity) {
        return baseRepository.save(entity);
    }

    
    @Transactional
    @Override
    public T updateOne(ID id,T entity) {
        return updateOne(baseRepository,id,entity);
    }

    @Transactional
    @Override
    public T updateOne(T entity,T tdb) {
    	 
        return null ;
    }


    @Transactional
    @Override
    public <TT, TID extends Serializable> TT updateOne(BaseRepository<TT, TID> baseRepository, TID id, TT entity) {
        return null;
    }

  
    @Transactional
    @Override
    public T saveOrUpdate(ID id, T t){
        if(id!=null){
        	Optional<T> db=findById(id);
            if(db!=null){
                return updateOne(id,t);
            }
        }
        return saveOne(t);
    }
     
    @Override
    public void deleteById(ID id) { 
        baseRepository.deleteById(id);
    }
  
    @Override
    public Optional<T> findById(ID id) { 
        return baseRepository.findById(id);
    }
     
    @Override
    public List<T> findAll(Example<T> example) {
        return baseRepository.findAll(example);
    }
    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }
 
    @Override
    public boolean exists(ID id) { 
        return baseRepository.existsById(id);
    }
}