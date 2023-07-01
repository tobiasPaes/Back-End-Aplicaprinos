package project.ufrn.pw.api_rest.service;

import java.util.List;

import org.springframework.stereotype.Service;
import project.ufrn.pw.api_rest.domain.Endereco;
import project.ufrn.pw.api_rest.repository.EnderecoRepository;

@Service
public class EnderecoService extends GenericService<Endereco, EnderecoRepository> {
    
    public EnderecoService(EnderecoRepository repository) {
        super(repository);
    }

	@Override
	public List<Endereco> list() {
		
		throw new UnsupportedOperationException("Unimplemented method 'list'");
	}

	@Override
    public Endereco getById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }
}