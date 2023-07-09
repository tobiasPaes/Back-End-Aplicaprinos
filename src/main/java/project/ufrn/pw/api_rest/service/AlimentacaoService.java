package project.ufrn.pw.api_rest.service;

import org.springframework.stereotype.Service;

import project.ufrn.pw.api_rest.domain.Alimentacao;
import project.ufrn.pw.api_rest.repository.AlimentacaoRepositoty;

@Service
public class AlimentacaoService extends GenericService<Alimentacao, AlimentacaoRepositoty>{
    public AlimentacaoService(AlimentacaoRepositoty repositoty){
        super(repositoty);
    }
    
}
