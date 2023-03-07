package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.SolicitacaoResponse;
import br.com.cwi.api.domain.Solicitacao;
import br.com.cwi.api.factories.SolicitacaoFactory;
import br.com.cwi.api.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class SolicitacaoMapperTest {

    @Test @DisplayName("Deve mapear corretamente uma solicitação quando ela for válida")
    void deveMapearCorretamenteComSolicitacaoCompleta(){
        Solicitacao solicitacao = SolicitacaoFactory.get();

        SolicitacaoResponse response = SolicitacaoMapper.toResponse(solicitacao);

        assertEquals(solicitacao.getId(), response.getId());
        assertEquals(solicitacao.getUsuario().getId(), response.getUsuarioId());
        assertEquals(solicitacao.getUsuario().getNomeCompleto(), response.getUsuario());
        assertEquals(solicitacao.getAmigo().getId(), response.getAmigoId());
        assertEquals(solicitacao.getAmigo().getNomeCompleto(), response.getAmigo());
    }

    @Test @DisplayName("Deve retornar nulo o usuário quando inválido")
    void deveRetornarNuloUsuarioInvalido(){
        Solicitacao solicitacao = SolicitacaoFactory.get();
        solicitacao.setUsuario(new Usuario());

        SolicitacaoResponse response = SolicitacaoMapper.toResponse(solicitacao);

        assertEquals(solicitacao.getId(), response.getId());
        assertNull(response.getUsuarioId());
        assertNull(response.getUsuario());
        assertEquals(solicitacao.getAmigo().getId(), response.getAmigoId());
        assertEquals(solicitacao.getAmigo().getNomeCompleto(), response.getAmigo());
    }

    @Test @DisplayName("Deve retornar nulo o amigo quando inválido")
    void deveRetornarNuloAmigoInvalido(){
        Solicitacao solicitacao = SolicitacaoFactory.get();
        solicitacao.setAmigo(new Usuario());

        SolicitacaoResponse response = SolicitacaoMapper.toResponse(solicitacao);

        assertEquals(solicitacao.getId(), response.getId());
        assertEquals(solicitacao.getUsuario().getId(), response.getUsuarioId());
        assertEquals(solicitacao.getUsuario().getNomeCompleto(), response.getUsuario());
        assertNull(response.getAmigoId());
        assertNull(response.getAmigo());
    }

    @Test @DisplayName("Deve retornar response nulo quando a solicitação for inválida")
    void deveRetornarResponseNulo(){
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setAmigo(new Usuario());
        solicitacao.setUsuario(new Usuario());

        SolicitacaoResponse response = SolicitacaoMapper.toResponse(solicitacao);

        assertNull(response.getId());
        assertNull(response.getUsuarioId());
        assertNull(response.getUsuario());
        assertNull(response.getAmigoId());
        assertNull(response.getAmigo());
    }

}