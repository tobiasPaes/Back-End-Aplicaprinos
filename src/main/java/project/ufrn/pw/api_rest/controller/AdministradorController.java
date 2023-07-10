package project.ufrn.pw.api_rest.controller;

import org.springframework.web.bind.annotation.*;
import project.ufrn.pw.api_rest.domain.Administrador;
import project.ufrn.pw.api_rest.domain.Administrador.DtoResponse;
import project.ufrn.pw.api_rest.repository.AdministradorRepository;
import project.ufrn.pw.api_rest.service.AdministradorService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdministradorController{
    AdministradorRepository repository;
    AdministradorService service;
    ModelMapper mapper;

    public AdministradorController(AdministradorService service, ModelMapper mapper, AdministradorRepository repository){
        this.service = service;
        this.mapper = mapper;
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Administrador.DtoResponse create(@RequestBody Administrador.DtoRequest a){
        Administrador ad = this.service.create(Administrador.DtoRequest.convertToEntity(a, mapper));

        Administrador.DtoResponse res = Administrador.DtoResponse.convertToDto(ad, mapper);
        res.generateLinks(ad.getId());

        return res;
    }

    @GetMapping
    public List<DtoResponse> list(){
        return this.service.list().stream().map(
            atual -> {
                Administrador.DtoResponse res = Administrador.DtoResponse.convertToDto(atual, mapper);
                res.generateLinks(atual.getId());
                return res;
            }
        ).toList();
    }

    @GetMapping("{id}")
    public Administrador.DtoResponse getById(@PathVariable Long id){
        Administrador al = this.service.getById(id);
        Administrador.DtoResponse res = Administrador.DtoResponse.convertToDto(al, mapper);
        res.generateLinks(id);
        return res;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        this.service.delete(id);
    }

    // @PutMapping("{id}")
    // public Administrador update(@PathVariable("id") Long id, @RequestBody Administrador administrador) {
    //     return repository.findById(id)
    //             .map(u -> {
    //                 if (administrador.getLogin() != null) {
    //                     u.setLogin(administrador.getLogin());
    //                 }
    //                 if (administrador.getUsername() != null) {
    //                     u.setUsername(administrador.getUsername());
    //                 }
    //                 if (administrador.getPassword() != null) {
    //                     u.setPassword(administrador.getPassword());
    //                 }
    //                 return repository.save(u);
    //             }).orElseThrow();
    // }
    @PutMapping("{id}")
    public Administrador.DtoResponse update(@RequestBody Administrador.DtoRequest dtoRequest, @PathVariable Long id){
        Administrador ad = Administrador.DtoRequest.convertToEntity(dtoRequest, mapper);
        Administrador.DtoResponse response = Administrador.DtoResponse.convertToDto(this.service.update(ad, id), mapper);
        response.generateLinks(id);
        return response;
    }
}
