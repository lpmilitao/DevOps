package br.com.cwi.api.security.mapper;

import br.com.cwi.api.controller.response.UsuarioDetalhadoResponse;
import br.com.cwi.api.mapper.GatoMapper;
import br.com.cwi.api.security.domain.Usuario;

import static java.util.stream.Collectors.toList;

public class UsuarioDetalhadoMapper {

    public static UsuarioDetalhadoResponse toResponse(Usuario entity){
        return UsuarioDetalhadoResponse.builder()
                .id(entity.getId())
                .nomeCompleto(entity.getNomeCompleto())
                .email(entity.getEmail())
                .apelido(entity.getApelido())
                .dataNascimento(entity.getDataNascimento())
                .imagemPerfil(entity.getImagemPerfil())
                .gatos(entity.getGatos().stream().map(
                        GatoMapper::toResponse).collect(toList()))
                .build();
    }

}
