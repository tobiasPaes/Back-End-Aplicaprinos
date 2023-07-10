package project.ufrn.pw.api_rest.service;


import org.springframework.stereotype.Service;
import project.ufrn.pw.api_rest.domain.Administrador;
import project.ufrn.pw.api_rest.repository.AdministradorRepository;

@Service
public class AdministradorService extends GenericService<Administrador, AdministradorRepository> {
    
    public AdministradorService(AdministradorRepository repository){
        super(repository);
    }
}
