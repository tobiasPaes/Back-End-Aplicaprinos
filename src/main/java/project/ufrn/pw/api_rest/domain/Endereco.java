package project.ufrn.pw.api_rest.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import project.ufrn.pw.api_rest.controller.EnderecoController;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Endereco extends AbstractEntity {
    String rua;

    @Data
    public static class DtoRequest {
        @NotBlank(message = "Rua com nome em branco")
        String rua;

        public static Endereco convertToEntity(DtoRequest dto, ModelMapper mapper) {
            return mapper.map(dto, Endereco.class);
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class DtoResponse extends RepresentationModel<DtoResponse> {
        String rua;
        
        public static DtoResponse convertToDto(Endereco e, ModelMapper mapper) {
            return mapper.map(e, DtoResponse.class);
        }

        public void generateLinks(Long id){
             add(linkTo(EnderecoController.class).slash(id).withSelfRel());
             add(linkTo(EnderecoController.class).slash(id).withRel("delete"));
             add(linkTo(EnderecoController.class).withRel("usuários"));
        }
    }
}