package project.ufrn.pw.api_rest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.modelmapper.ModelMapper;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario extends AbstractEntity {
    String username;
    String login;
    String password;
    Boolean isAdmin;

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
    public static class DtoResponse {
        String username;
        String password;
        String login;

        public static DtoResponse convertToDto(Usuario p, ModelMapper mapper) {
            return mapper.map(p, DtoResponse.class);
        }
    }
}

// {
//  "username": "Bruno",
//  "login": "brunin",
//  "password": "brunoooo",
//  "isAdmin": true
// }

// localhost:8080/computador