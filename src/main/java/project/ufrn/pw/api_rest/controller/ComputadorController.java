package project.ufrn.pw.api_rest.controller;

import org.springframework.web.bind.annotation.*;
import project.ufrn.pw.api_rest.domain.Usuario;
import project.ufrn.pw.api_rest.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/computador")
public class ComputadorController {

    UsuarioService service;

    public ComputadorController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public Usuario create(@RequestBody Usuario p, @PathVariable Long id){
        return this.create(p, id);
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
