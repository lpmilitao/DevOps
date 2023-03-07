package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.GatoDetalhadoResponse;
import br.com.cwi.api.domain.Gato;
import br.com.cwi.api.factories.GatoFactory;
import br.com.cwi.api.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class GatoDetalhadoMapperTest {

    @Test @DisplayName("Deve retornar response de gato detalhado corretamente quando gato for válido")
    void deveRetornarResponseCorretamenteECompleto(){

        Gato gato = GatoFactory.get();

        GatoDetalhadoResponse response = GatoDetalhadoMapper.toResponse(gato);

        assertEquals(gato.getId(), response.getId());
        assertEquals(gato.getNome(), response.getNome());
        assertEquals(gato.getCor(), response.getCor());
        assertEquals(gato.getApelido(), response.getApelido());
        assertEquals(gato.getImagem(), response.getImagem());
        assertEquals(gato.getDono().getId(), response.getDonoId());
        assertEquals(gato.getDono().getNomeCompleto(), response.getDono());
    }

    @Test @DisplayName("Deve retornar response incompleto quando o dono for inválido")
    void deveRetornarResponseSemDono(){

        Gato gato = GatoFactory.get();
        gato.setDono(new Usuario());

        GatoDetalhadoResponse response = GatoDetalhadoMapper.toResponse(gato);

        assertEquals(gato.getId(), response.getId());
        assertEquals(gato.getNome(), response.getNome());
        assertEquals(gato.getCor(), response.getCor());
        assertEquals(gato.getApelido(), response.getApelido());
        assertEquals(gato.getImagem(), response.getImagem());
        assertNull(response.getDonoId());
        assertNull(response.getDono());
    }

    @Test @DisplayName("Deve retornar response incompleto quando o gato for inválido")
    void deveRetornarResponseNulo(){

        Gato gato = new Gato();
        gato.setDono(new Usuario());

        GatoDetalhadoResponse response = GatoDetalhadoMapper.toResponse(gato);

        assertNull(response.getId());
        assertNull(response.getNome());
        assertNull(response.getCor());
        assertNull(response.getApelido());
        assertNull(response.getImagem());
        assertNull(response.getDonoId());
        assertNull(response.getDono());
    }

}