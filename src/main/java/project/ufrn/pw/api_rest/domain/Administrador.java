package project.ufrn.pw.api_rest.domain;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import project.ufrn.pw.api_rest.controller.AdministradorController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Administrador extends AbstractEntity{
    String username;
    String login;
    String password;
    boolean admin = true;

    @Override
    public void partialUpdate(AbstractEntity e) {
        if (e instanceof Administrador ad){
            this.username = ad.username;
            this.login = ad.login;
            this.password = ad.password;
        }
    }

    @Data
    public static class DtoRequest{
        @NotBlank(message = "Usuário com nome em branco")
        String username;        
        @NotBlank(message = "Login com nome em branco")
        String login;
        @NotBlank(message = "Password com nome em branco")
        String password;

        public static Administrador convertToEntity(DtoRequest dto, ModelMapper mapper){
            return mapper.map(dto, Administrador.class);
        }
    } 

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class DtoResponse extends RepresentationModel<DtoResponse>{
        String username;
        String login;
        public static DtoResponse convertToDto(Administrador ad, ModelMapper mapper){
            return mapper.map(ad, DtoResponse.class);
        }

        public void generateLinks(Long id){
            add(linkTo(AdministradorController.class).slash(id).withSelfRel());
            add(linkTo(AdministradorController.class).withRel("usuario"));
            add(linkTo(AdministradorController.class).slash(id).withRel("delete"));
        }
    }
}