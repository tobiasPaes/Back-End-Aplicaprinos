package project.ufrn.pw.api_rest.domain;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    
}
