package project.ufrn.pw.api_rest.domain;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import project.ufrn.pw.api_rest.controller.PedidoController;
import project.ufrn.pw.api_rest.domain.Pedido;
import org.springframework.hateoas.RepresentationModel;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

 

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pedido extends AbstractEntity{
    String formaPagamento;
    Float valor;
    
    @ManyToMany
    @JoinTable(
        name = "pedidos_produtos",
        joinColumns = {
            @JoinColumn(name = "pedido_id", referencedColumnName = "id")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "produto_id")
        }
    )
    ArrayList<Produto> products = new ArrayList<Produto>();

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class DtoResponse extends RepresentationModel<DtoResponse>{
        String formaPagamento;
        Float valor;


        public static DtoResponse convertToDto(Pedido p, ModelMapper mapper){
            return mapper.map(p, DtoResponse.class);
        }

        public void generateLinks(Long id){
             add(linkTo(PedidoController.class).slash(id).withSelfRel());
             add(linkTo(PedidoController.class).slash(id).withRel("delete"));
             add(linkTo(PedidoController.class).withRel("pedido"));
        }
    }

    @Data
    public static class DtoRequest{
        @NotBlank(message = "Sem forma de pagamento")
        String formaPagamento;

        public static Pedido convertToEntity(DtoRequest dto, ModelMapper mapper){ 
            return mapper.map(dto, Pedido.class);
        }
    }

    
}
