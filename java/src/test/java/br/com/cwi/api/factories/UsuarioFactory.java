package br.com.cwi.api.factories;

import br.com.cwi.api.security.controller.request.UsuarioRequest;
import br.com.cwi.api.security.domain.Usuario;

import java.time.LocalDate;

public class UsuarioFactory {

    public static Usuario get() {
        Usuario usuario = new Usuario();
        usuario.setId(SimpleFactory.getRandomLong());
        usuario.setNomeCompleto("Usuário de teste");
        usuario.setEmail("teste@cwi.com.br");
        usuario.setApelido("Testinho");
        usuario.setDataNascimento(LocalDate.of(2004, 4, 16));
        usuario.setAtivo(true);
        usuario.setImagemPerfil("");
        return usuario;
    }

    public static UsuarioRequest getRequest() {
        UsuarioRequest request = new UsuarioRequest();
        request.setNomeCompleto("Usuário de teste");
        request.setEmail("teste@cwi.com.br");
        request.setSenha("654321");
        request.setApelido("Testinho");
        request.setDataNascimento(LocalDate.of(2004, 4, 16));
        request.setImagemPerfil("");
        return request;
    }


}
