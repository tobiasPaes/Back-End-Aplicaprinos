package project.ufrn.pw.api_rest.service;

import org.springframework.stereotype.Service;

import project.ufrn.pw.api_rest.domain.Caprino;
import project.ufrn.pw.api_rest.repository.CaprinoRepository;

@Service
public class CaprinoService extends GenericService<Caprino, CaprinoRepository>{
    public CaprinoService(CaprinoRepository repository){
        super(repository);
    }
}
