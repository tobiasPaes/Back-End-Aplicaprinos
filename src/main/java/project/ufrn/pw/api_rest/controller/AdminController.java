package project.ufrn.pw.api_rest.controller;

import org.springframework.web.bind.annotation.*;
import project.ufrn.pw.api_rest.domain.Admin;
import project.ufrn.pw.api_rest.domain.Admin.DtoResponse;
import project.ufrn.pw.api_rest.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController{
    AdminService service;
    ModelMapper mapper;

    public AdminController(AdminService service, ModelMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Admin.DtoResponse create(@RequestBody Admin.DtoRequest a){
        Admin ad = this.service.create(Admin.DtoRequest.convertToEntity(a, mapper));

        Admin.DtoResponse res = Admin.DtoResponse.convertToDto(ad, mapper);
        res.generateLinks(ad.getId());

        return res;
    }

    @GetMapping
    public List<DtoResponse> list(){
        return this.service.list().stream().map(
            atual -> {
                Admin.DtoResponse res = Admin.DtoResponse.convertToDto(atual, mapper);
                res.generateLinks(atual.getId());
                return res;
            }
        ).toList();
    }

    @GetMapping("{id}")
    public Admin.DtoResponse getById(@PathVariable Long id){
        Admin al = this.service.getById(id);
        Admin.DtoResponse res = Admin.DtoResponse.convertToDto(al, mapper);
        res.generateLinks(id);
        return res;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        this.service.delete(id);
    }

    /* --Atualizar--
    @PutMapping("{id}")
    public Admin update(@RequestBody Admin ad, @PathVariable Long id) {
        return this.service.update(ad, id);
    }
    */
}
