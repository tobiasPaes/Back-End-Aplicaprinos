package project.ufrn.pw.api_rest.service;

import java.util.List;

import org.springframework.stereotype.Service;
import project.ufrn.pw.api_rest.domain.Pedido;
import project.ufrn.pw.api_rest.repository.PedidoRepository;

@Service
public class PedidoService extends GenericService<Pedido, PedidoRepository>{
    public PedidoService(PedidoRepository repository) {
        super(repository);
    }

    @Override
	public List<Pedido> list() {		
		throw new UnsupportedOperationException("Unimplemented method 'list'");
	}
}
