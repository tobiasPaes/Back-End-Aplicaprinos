package project.ufrn.pw.api_rest.service;

import java.util.List;

import org.springframework.stereotype.Service;
import project.ufrn.pw.api_rest.domain.Produto;
import project.ufrn.pw.api_rest.repository.ProdutoRepository;

@Service
public class ProdutoService extends GenericService<Produto, ProdutoRepository>{
    public ProdutoService(ProdutoRepository repository){
        super(repository);
    }

    @Override
	public List<Produto> list() {		
		throw new UnsupportedOperationException("Unimplemented method 'list'");
	}
}
