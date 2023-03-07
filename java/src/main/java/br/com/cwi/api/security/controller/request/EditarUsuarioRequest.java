package br.com.cwi.api.security.controller.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class EditarUsuarioRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String apelido;

    @URL
    private String imagemPerfil;

}
