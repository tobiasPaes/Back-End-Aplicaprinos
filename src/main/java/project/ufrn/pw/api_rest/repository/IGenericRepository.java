package project.ufrn.pw.api_rest.repository;

import org.springframework.data.repository.ListCrudRepository;
import project.ufrn.pw.api_rest.domain.AbstractEntity;

public interface IGenericRepository<E extends AbstractEntity> extends ListCrudRepository<E, Long> {
    
}