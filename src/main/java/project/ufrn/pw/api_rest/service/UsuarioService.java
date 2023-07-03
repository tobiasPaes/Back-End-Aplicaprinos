package project.ufrn.pw.api_rest.service;


import org.springframework.stereotype.Service;
import project.ufrn.pw.api_rest.domain.Usuario;
import project.ufrn.pw.api_rest.repository.UsuarioRepository;

@Service
public class UsuarioService extends GenericService<Usuario, UsuarioRepository>{
    public UsuarioService(UsuarioRepository repository){
        super(repository);
    }
}
