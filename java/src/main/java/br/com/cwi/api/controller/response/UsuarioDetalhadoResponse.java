package br.com.cwi.api.controller.response;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class UsuarioDetalhadoResponse {

    private Long id;

    private String nomeCompleto;

    private String apelido;

    private String email;

    private List<GatoResponse> gatos = new ArrayList<>();

    private String imagemPerfil;

    private LocalDate dataNascimento;

}