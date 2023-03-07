package br.com.cwi.api.security.mapper;

import br.com.cwi.api.security.controller.request.UsuarioRequest;
import br.com.cwi.api.security.controller.response.UsuarioResponse;
import br.com.cwi.api.security.domain.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequest request) {
        Usuario entity = new Usuario();
        entity.setNomeCompleto(request.getNomeCompleto());
        entity.setEmail(request.getEmail());
        entity.setApelido(request.getApelido());
        entity.setDataNascimento(request.getDataNascimento());
        entity.setImagemPerfil(request.getImagemPerfil());
        return entity;
    }

    public static UsuarioResponse toResponse(Usuario entity) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(entity.getId());
        response.setNome(entity.getNomeCompleto());
        response.setEmail(entity.getEmail());
        return response;
    }
}
