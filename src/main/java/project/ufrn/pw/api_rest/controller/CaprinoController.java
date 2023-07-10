package project.ufrn.pw.api_rest.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import project.ufrn.pw.api_rest.domain.Caprino;
import project.ufrn.pw.api_rest.domain.Caprino.DtoResponse;
import project.ufrn.pw.api_rest.repository.CaprinoRepository;
import project.ufrn.pw.api_rest.service.CaprinoService;

@RestController
@RequestMapping("/caprino")
public class CaprinoController {
    CaprinoService service;
    CaprinoRepository repository;
    ModelMapper mapper;

    public CaprinoController(CaprinoService service, ModelMapper mapper, CaprinoRepository repository){
        this.service = service;
        this.mapper = mapper;
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Caprino.DtoResponse create(@RequestBody Caprino.DtoRequest c){
        Caprino cap = this.service.create(Caprino.DtoRequest.convertToEntity(c, mapper));

        Caprino.DtoResponse res = Caprino.DtoResponse.convertToDto(cap, mapper);
        res.generateLinks(cap.getId());

        return res;
    }

    @GetMapping
    public List<DtoResponse> list(){
        return this.service.list().stream().map(
            atual -> {
                Caprino.DtoResponse res = Caprino.DtoResponse.convertToDto(atual, mapper);
                res.generateLinks(atual.getId());
                return res;
            }
        ).toList();
    }

    @GetMapping("{id}")
    public Caprino.DtoResponse getById(@PathVariable Long id){
        Caprino cap = this.service.getById(id);
        Caprino.DtoResponse res = Caprino.DtoResponse.convertToDto(cap, mapper);
        res.generateLinks(id);
        return res;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        this.service.delete(id);
    }

    @PutMapping("{id}")
    public Caprino.DtoResponse update(@RequestBody Caprino.DtoRequest dtoRequest, @PathVariable Long id){
        Caprino ca = Caprino.DtoRequest.convertToEntity(dtoRequest, mapper);
        Caprino.DtoResponse response = Caprino.DtoResponse.convertToDto(this.service.update(ca, id), mapper);
        response.generateLinks(id);
        return response;
    }
}
