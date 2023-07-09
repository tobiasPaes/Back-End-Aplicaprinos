package project.ufrn.pw.api_rest.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import project.ufrn.pw.api_rest.domain.Caprino;
import project.ufrn.pw.api_rest.domain.Caprino.DtoResponse;
import project.ufrn.pw.api_rest.service.CaprinoService;

@RestController
@RequestMapping("/caprino")
public class CaprinoController {
    CaprinoService service;
    ModelMapper mapper;

    public CaprinoController(CaprinoService service, ModelMapper mapper){
        this.service = service;
        this.mapper = mapper;
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

    /* --Atualizar--
    @PutMapping("{id}")
    public Caprino update(@RequestBody Usuario p, @PathVariable Long id) {
        return this.service.update(p, id);
    }
    */


















}
