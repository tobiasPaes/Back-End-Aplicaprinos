package project.ufrn.pw.api_rest.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import project.ufrn.pw.api_rest.domain.Usuario;
import project.ufrn.pw.api_rest.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/computador")
public class UsuarioController {

    UsuarioService service;
    ModelMapper mapper;

    public UsuarioController(UsuarioService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario.DtoResponse create(@RequestBody Usuario.DtoRequest p) {

        Usuario usuario = this.service.create(Usuario.DtoRequest.convertToEntity(p, mapper));
        return Usuario.DtoResponse.convertToDto(usuario, mapper);
    }

    @GetMapping
    public List<Usuario> list(){
        return this.service.list();
    }

    @PutMapping("{id}")
    public Usuario update(@RequestBody Usuario p, @PathVariable Long id){
        return this.service.update(p, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        this.service.delete(id);
    }
}