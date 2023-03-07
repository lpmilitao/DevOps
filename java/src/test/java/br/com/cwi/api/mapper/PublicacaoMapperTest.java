package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.request.IncluirPublicacaoRequest;
import br.com.cwi.api.controller.response.PublicacaoResponse;
import br.com.cwi.api.domain.Publicacao;
import br.com.cwi.api.factories.PublicacaoFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class PublicacaoMapperTest {

    @Test @DisplayName("Deve retornar uma publicação corretamente quando receber um request válido")
    void deveMapearUmaEntidadePublicacaoCorretamente(){

        Publicacao publicacaoEsperada = PublicacaoFactory.get();
        IncluirPublicacaoRequest request = new IncluirPublicacaoRequest();
        request.setTitulo("Amo meu gatinho");
        request.setMensagem("Meu gatinho é muito fofo");
        request.setImagem("");

        Publicacao publicacaoRecebida =  PublicacaoMapper.toEntity(request);

        assertEquals(publicacaoEsperada.getTitulo(), publicacaoRecebida.getTitulo());
        assertEquals(publicacaoEsperada.getMensagem(), publicacaoRecebida.getMensagem());
        assertEquals(publicacaoEsperada.getImagem(), publicacaoRecebida.getImagem());
    }

    @Test @DisplayName("Deve retornar nulo quando o request for inválido")
    void deveRetornarNuloUmRequestInvalido(){

        IncluirPublicacaoRequest request = new IncluirPublicacaoRequest();

        Publicacao publicacaoRecebida =  PublicacaoMapper.toEntity(request);

        assertNull(publicacaoRecebida.getTitulo());
        assertNull(publicacaoRecebida.getMensagem());
        assertNull(publicacaoRecebida.getImagem());
    }

    @Test @DisplayName("Deve retornar um response completo quando a publicação for válida")
    void deveRetornarUmResponseCompleto() {

        Publicacao publicacao = PublicacaoFactory.getPublicacaoPublica();
        Usuario autor = UsuarioFactory.get();
        publicacao.setAutor(autor);


        PublicacaoResponse response = PublicacaoMapper.toResponse(publicacao);

        assertEquals(publicacao.getId(), response.getId());
        assertEquals(publicacao.getTitulo(), response.getTitulo());
        assertEquals(publicacao.getMensagem(), response.getMensagem());
        assertEquals(publicacao.getImagem(), response.getImagem());
        assertEquals(publicacao.getCurtidas(), response.getCurtidas());
        assertEquals(publicacao.getVisualizacao(), response.getVisualizacao());
        assertEquals(publicacao.getDataPublicacao(), response.getDataPublicacao());
        assertEquals(publicacao.getAutor().getId(), response.getAutorId());
        assertEquals(publicacao.getAutor().getNomeCompleto(), response.getNomeAutor());
        assertEquals(publicacao.getComentarios().size(), response.getComentarios());
    }

    @Test @DisplayName("Deve retornar um response incompleto quando a publicação for inválida")
    void deveRetornarUmResponseIncompleto() {

        Publicacao publicacao = new Publicacao();
        publicacao.setAutor(new Usuario());

        PublicacaoResponse response = PublicacaoMapper.toResponse(publicacao);

        assertNull(response.getId());
        assertNull(response.getTitulo());
        assertNull(response.getMensagem());
        assertNull(response.getImagem());
        assertNull(response.getCurtidas());
        assertNull(response.getVisualizacao());
        assertNull(response.getDataPublicacao());
        assertNull(response.getAutorId());
        assertNull(response.getNomeAutor());
        assertEquals(0, response.getComentarios());
    }

    @Test @DisplayName("Deve retornar corretamente o nome do autor da publicação quando informado.")
    void deveRetornarCorretamenteOAutor() {

        Publicacao publicacao = PublicacaoFactory.getPublicacaoPublica();
        Usuario autor = UsuarioFactory.get();
        publicacao.setAutor(autor);

        PublicacaoResponse response = PublicacaoMapper.toResponse(publicacao);

        assertEquals(publicacao.getAutor().getId(), response.getAutorId());
        assertEquals(publicacao.getAutor().getNomeCompleto(), response.getNomeAutor());
    }

    @Test @DisplayName("Deve retornar as informações do quando o autor nulas quando ele não for informado.")
    void deveRetornarIformaçõesDoAutorNulas() {

        Publicacao publicacao = new Publicacao();
        publicacao.setAutor(new Usuario());

        PublicacaoResponse response = PublicacaoMapper.toResponse(publicacao);

        assertNull(response.getAutorId());
        assertNull(response.getNomeAutor());
    }


}
