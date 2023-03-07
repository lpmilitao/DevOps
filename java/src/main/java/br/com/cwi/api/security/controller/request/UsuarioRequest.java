package br.com.cwi.api.security.controller.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class UsuarioRequest {

    @NotBlank
    private String nomeCompleto;

    @NotNull @Email
    private String email;

    private String apelido;

    @NotNull
    private LocalDate dataNascimento;

    @NotBlank
    private String senha;

    @URL
    private String imagemPerfil;
}
