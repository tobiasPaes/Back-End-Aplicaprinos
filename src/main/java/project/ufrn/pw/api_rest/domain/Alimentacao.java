package project.ufrn.pw.api_rest.domain;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import project.ufrn.pw.api_rest.controller.AlimentacaoController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alimentacao extends AbstractEntity{
    String piquete;
    Float consumoConcentradoDiario;

    @Override
    public void partialUpdate(AbstractEntity e) {
        if (e instanceof Alimentacao al){
            this.piquete = al.piquete;
            this.consumoConcentradoDiario = al.consumoConcentradoDiario;
        }
    }


    @Data
    public static class DtoRequest{
        @NotBlank(message = "piquete esta em branco, preencha todos os campos")
        String piquete;

        @NotBlank(message = "quantidade de concentrado consumido diariamente em branco, preencha todos os campos")
        Float consumoConcentradoDiario;

        public static Alimentacao convertToEntity(DtoRequest dto, ModelMapper mapper){
            return mapper.map(dto, Alimentacao.class);
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class DtoResponse extends RepresentationModel<DtoResponse>{
        Long id;
        String piquete;

        public static DtoResponse convertToDto(Alimentacao al, ModelMapper mapper){
            return mapper.map(al, DtoResponse.class);
        }

        public void generateLinks(Long id){
             add(linkTo(AlimentacaoController.class).slash(id).withSelfRel());
             add(linkTo(AlimentacaoController.class).withRel("usuario"));
             add(linkTo(AlimentacaoController.class).slash(id).withRel("delete"));
        }
    }
}
