package project.ufrn.pw.api_rest.domain;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import jakarta.validation.constraints.NotBlank;
import project.ufrn.pw.api_rest.controller.AdminController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;



public class Admin extends Usuario{
    
    public static class DtoRequest{
        @NotBlank(message = "Usuário com nome em branco")
        String username;        
        @NotBlank(message = "Login com nome em branco")
        String login;
        @NotBlank(message = "Password com nome em branco")
        String password;

        public static Admin convertToEntity(DtoRequest dto, ModelMapper mapper){
            return mapper.map(dto, Admin.class);
        }
    } 

    public static class DtoResponse extends RepresentationModel<DtoResponse>{
        String username;
        String login;
        public static DtoResponse convertToDto(Admin ad, ModelMapper mapper){
            return mapper.map(ad, DtoResponse.class);
        }

        public void generateLinks(Long id){
            add(linkTo(AdminController.class).slash(id).withSelfRel());
            add(linkTo(AdminController.class).withRel("usuários"));
            add(linkTo(AdminController.class).slash(id).withRel("delete"));
        }
    }
}
