package project.ufrn.pw.api_rest.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import project.ufrn.pw.api_rest.domain.Alimentacao;
import project.ufrn.pw.api_rest.domain.Alimentacao.DtoResponse;
import project.ufrn.pw.api_rest.repository.AlimentacaoRepositoty;
import project.ufrn.pw.api_rest.service.AlimentacaoService;

@RestController
@RequestMapping("/alimentacao")
public class AlimentacaoController {
    AlimentacaoRepositoty repository;
    AlimentacaoService service;
    ModelMapper mapper;

    public AlimentacaoController(AlimentacaoService service, ModelMapper mapper, AlimentacaoRepositoty repository){
        this.service = service;
        this.mapper = mapper;
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Alimentacao.DtoResponse create(@RequestBody Alimentacao.DtoRequest a){
        Alimentacao cap = this.service.create(Alimentacao.DtoRequest.convertToEntity(a, mapper));

        Alimentacao.DtoResponse res = Alimentacao.DtoResponse.convertToDto(cap, mapper);
        res.generateLinks(cap.getId());

        return res;
    }

    @GetMapping
    public List<DtoResponse> list(){
        return this.service.list().stream().map(
            atual -> {
                Alimentacao.DtoResponse res = Alimentacao.DtoResponse.convertToDto(atual, mapper);
                res.generateLinks(atual.getId());
                return res;
            }
        ).toList();
    }

    @GetMapping("{id}")
    public Alimentacao.DtoResponse getById(@PathVariable Long id){
        Alimentacao al = this.service.getById(id);
        Alimentacao.DtoResponse res = Alimentacao.DtoResponse.convertToDto(al, mapper);
        res.generateLinks(id);
        return res;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        this.service.delete(id);
    }

    @PutMapping("{id}")
    public Alimentacao.DtoResponse update(@RequestBody Alimentacao.DtoRequest dtoRequest, @PathVariable Long id){
        Alimentacao al = Alimentacao.DtoRequest.convertToEntity(dtoRequest, mapper);
        Alimentacao.DtoResponse response = Alimentacao.DtoResponse.convertToDto(this.service.update(al, id), mapper);
        response.generateLinks(id);
        return response;
    }
}
