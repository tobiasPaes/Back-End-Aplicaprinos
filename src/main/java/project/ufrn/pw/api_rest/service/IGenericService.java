package project.ufrn.pw.api_rest.service;

import java.util.List;
import project.ufrn.pw.api_rest.domain.AbstractEntity;

public interface IGenericService<E extends AbstractEntity> {
    public E create(E e);
    public E update(E e, Long id);
    public void delete(Long id);
    public List<E> list();
}