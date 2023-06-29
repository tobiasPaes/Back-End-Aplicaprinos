package project.ufrn.pw.api_rest.domain;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario extends AbstractEntity{
    String username;
    String login;
    String password;
    Boolean isAdmin;

    // @Data
    // public static class DtoRequest{
    //     @NotBlank(message = "")
    // }
}