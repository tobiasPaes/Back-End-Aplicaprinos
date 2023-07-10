package project.ufrn.pw.api_rest.domain;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import project.ufrn.pw.api_rest.controller.CaprinoController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import java.util.List;

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

    @ManyToMany
    @JoinTable( 
        name = "cap_us", 
        joinColumns = {
            @JoinColumn(
                name = "caprino_id",
                referencedColumnName = "id"
            )
        }, inverseJoinColumns = {
            @JoinColumn(name = "user_id")
        }
    )
    List<Usuario> user;

    @Override
    public void partialUpdate(AbstractEntity e) {
        if (e instanceof Caprino cap){
            this.idade = cap.idade;
            this.vacina = cap.vacina;
            this.genero = cap.genero;
            this.qntReproducao = cap.qntReproducao;
            this.pesoInicial = cap.pesoInicial;
        }
    }

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

        public static Caprino convertToEntity(DtoRequest dto, ModelMapper mapper){
            return mapper.map(dto, Caprino.class);
        }
    }

    @Data
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
             add(linkTo(CaprinoController.class).withRel("usuario"));
             add(linkTo(CaprinoController.class).slash(id).withRel("delete"));
        }
    }
}
