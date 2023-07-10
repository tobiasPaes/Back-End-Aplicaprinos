package project.ufrn.pw.api_rest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import project.ufrn.pw.api_rest.controller.UsuarioController;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario extends AbstractEntity {
    String username;
    String login;
    String password;

    @ManyToOne
    @JoinColumn(name = "adm_id")
    Administrador ad;

    @ManyToMany(mappedBy = "user")
    List<Caprino> cap;

    @Override
    public void partialUpdate(AbstractEntity e) {
        if (e instanceof Usuario u){
            this.username = u.username;
            this.login = u.login;
            this.password = u.password;
        }
    }

    @Data
    public static class DtoRequest {
        @NotBlank(message = "Usuário com nome em branco")
        String username;
        @NotBlank(message = "Login com nome em branco")
        String login;
        @NotBlank(message = "Password com nome em branco")
        String password;

        public static Usuario convertToEntity(DtoRequest dto, ModelMapper mapper) {
            return mapper.map(dto, Usuario.class);
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class DtoResponse extends RepresentationModel<DtoResponse> {
        String username;
        String login;

        public static DtoResponse convertToDto(Usuario u, ModelMapper mapper){
            return mapper.map(u, DtoResponse.class);
        }

        public void generateLinks(Long id){
             add(linkTo(UsuarioController.class).slash(id).withSelfRel());
             add(linkTo(UsuarioController.class).withRel("usuários"));
             add(linkTo(UsuarioController.class).slash(id).withRel("delete"));
        }
    }
}