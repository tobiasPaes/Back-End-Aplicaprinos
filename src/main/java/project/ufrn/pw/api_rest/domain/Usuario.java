package project.ufrn.pw.api_rest.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends AbstractEntity{
    String username;
    String login;
    String password;
    Boolean isAdmin;
}