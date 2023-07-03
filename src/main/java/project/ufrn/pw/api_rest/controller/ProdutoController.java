package project.ufrn.pw.api_rest.controller;

import org.springframework.web.bind.annotation.*;
import project.ufrn.pw.api_rest.domain.Produto;
import project.ufrn.pw.api_rest.service.ProdutoService;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/Produtos")
public class ProdutoController {

    ProdutoService service;
    ModelMapper mapper;

    public ProdutoController(ProdutoService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto.DtoResponse create(@RequestBody Produto.DtoRequest p) {
        
        Produto produto = this.service.create(Produto.DtoRequest.convertToEntity(p, mapper));
        return Produto.DtoResponse.convertToDto(produto, mapper);
    }

    @GetMapping
    public List<Produto.DtoResponse> list(){

        return this.service.list().stream().map(
            elementoAtual -> {
                Produto.DtoResponse response = Produto.DtoResponse.convertToDto(elementoAtual, mapper);
                response.generateLinks(elementoAtual.getId());
                return response;
            }).toList();
    }
    
    @PutMapping("{id}")
    public Produto update(@RequestBody Produto p, @PathVariable Long id) {
        return this.service.saveAndFlush(p, id);
    }

    
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.service.delete(id);
    }

     @GetMapping("{id}")
    public Produto.DtoResponse getById(@PathVariable Long id){
        Produto p = this.service.getById(id);
        Produto.DtoResponse res = Produto.DtoResponse.convertToDto(p, mapper);
        return res;
    }

    //tem tds
}