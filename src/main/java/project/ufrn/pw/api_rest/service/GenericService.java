package project.ufrn.pw.api_rest.service;

import jakarta.persistence.EntityNotFoundException;
import project.ufrn.pw.api_rest.domain.AbstractEntity;
import project.ufrn.pw.api_rest.repository.IGenericRepository;
import java.util.*;

public abstract class GenericService<E extends AbstractEntity, R extends IGenericRepository<E>> implements IGenericService<E>{

    R repository;
    
    public GenericService(R repository){
        this.repository = repository;
    }
    
    @Override
    public E create(E e) {
        return (E) this.repository.save(e);
    }

    @Override
    public void delete(Long id){
        repository.deleteById(id);
    }

    @Override
    public E update(E e, Long id){
        Optional<E> banco = repository.findById(id);
        if(banco.isPresent()){
            return (E) this.repository.save(e);
        }else{
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<E> list(){
        return (List<E>) this.repository.findAll();
    }

    @Override
    public E getById(Long id){
        Optional<E> eBanco = repository.findById(id);
        if(eBanco.isPresent()){
            return (E) eBanco.get();
        }else{
            throw new EntityNotFoundException();
        }
        // return (E) this.repository.findById(id);
    }
}