package project.ufrn.pw.api_rest.service;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import project.ufrn.pw.api_rest.domain.AbstractEntity;
import project.ufrn.pw.api_rest.repository.IGenericRepository;
import java.util.*;

@Service
public abstract class GenericService<E extends AbstractEntity, R extends IGenericRepository> implements IGenericRepository<E>{

    R repository;


    public GenericService(R repository){
        this.repository = repository;
    }


 
    public E create(E e) {
        return (E) this.repository.save(e);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<E> list(){
        return (List<E>) repository.findAll();
    }

    public E update(E e, Long id){
        Optional<E> banco = repository.findById(id);
        if(banco.isPresent()){
            return (E) repository.save(banco);
        }else{
            throw new EntityNotFoundException();
        }
    }


}