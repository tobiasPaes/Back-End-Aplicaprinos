package project.ufrn.pw.api_rest.controller;

import org.springframework.web.bind.annotation.*;
import project.ufrn.pw.api_rest.domain.Pedido;
import project.ufrn.pw.api_rest.domain.Pedido.DtoResponse;
import project.ufrn.pw.api_rest.service.PedidoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/Pedido")
public class PedidoController {

    PedidoService service;
    ModelMapper mapper;

    public PedidoController(PedidoService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido.DtoResponse create(@RequestBody Pedido.DtoRequest p) {
        Pedido pedido = this.service.create(Pedido.DtoRequest.convertToEntity(p, mapper));

        Pedido.DtoResponse res = Pedido.DtoResponse.convertToDto(pedido, mapper);
        res.generateLinks(pedido.getId());

        return res;
    }

    @GetMapping
    public List<DtoResponse> list() {
        return this.service.list().stream().map(
            elementoAtual -> {
                Pedido.DtoResponse res = Pedido.DtoResponse.convertToDto(elementoAtual, mapper);
                res.generateLinks(elementoAtual.getId());
                return res;
            }).toList();
    }

    @GetMapping("{id}")
    public Pedido.DtoResponse getById(@PathVariable Long id){
        Pedido pedido = this.service.getById(id);
        Pedido.DtoResponse response = Pedido.DtoResponse.convertToDto(pedido, mapper);
        response.generateLinks(pedido.getId());

        return response;
    }

    @PutMapping("{id}")
    public Pedido update(@RequestBody Pedido p, @PathVariable Long id) {
        return this.service.update(p, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.service.delete(id);
    }

    // tem tds
}
