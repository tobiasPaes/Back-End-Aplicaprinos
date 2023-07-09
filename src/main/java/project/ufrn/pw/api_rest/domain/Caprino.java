package project.ufrn.pw.api_rest.domain;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import project.ufrn.pw.api_rest.controller.CaprinoController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Caprino extends AbstractEntity{
    int idade;
    boolean vacina;
    String genero;
    int qntReproducao;
    float pesoInicial;
    float pesoFinal;

    @OneToOne
    @JoinColumn(name = "alimentacao_id")
    Alimentacao alim;

    @Data
    public static class DtoRequest{
        @NotBlank(message = "idade em branco, por favor insira todos os campos")
        int idade;
        
        @NotBlank(message = "vacina em branco, por favor insira todos os campos")
        boolean vacina;

        @NotBlank(message = "genero em branco, por favor insira todos os campos")
        String genero;

        @NotBlank(message = "quantidade de reproducao em branco, por favor insira todos os campos")
        int qntReproducao;

        @NotBlank(message = "peso em branco, por favor insira todos os campos")
        float pesoInicial;
        
        // @NotBlank(message = "alimentacao em branco, por favor insira todos os campos")
        // Long alim;

        public static Caprino convertToEntity(DtoRequest dto, ModelMapper mapper){
            return mapper.map(dto, Caprino.class);
        }
    }

    // @Data
    @EqualsAndHashCode(callSuper = true)
    public static class DtoResponse extends RepresentationModel<DtoResponse>{
        int idade;
        boolean vacina;
        String genero;
        int qntReproducao;
        float pesoInicial;

        public static DtoResponse convertToDto(Caprino c, ModelMapper mapper){
            return mapper.map(c, DtoResponse.class);
        }

        public void generateLinks(Long id){
            add(linkTo(CaprinoController.class).slash(id).withSelfRel());
             add(linkTo(CaprinoController.class).withRel("usuários"));
             add(linkTo(CaprinoController.class).slash(id).withRel("delete"));
        }
    }
}
