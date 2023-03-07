package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.request.IncluirGatoRequest;
import br.com.cwi.api.controller.response.GatoResponse;
import br.com.cwi.api.domain.Gato;
import br.com.cwi.api.factories.GatoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class GatoMapperTest {

    @Test @DisplayName("Deve retornar uma entidade corretamente quando o request for válido")
    void deveRetornarUmaEntidadeGatoCorretamente(){

        Gato gatoEsperado = GatoFactory.get();
        IncluirGatoRequest request = new IncluirGatoRequest();
        request.setNome("Gato teste");
        request.setCor("Preto");
        request.setApelido("Miau");
        request.setImagem("");

        Gato gatoRecebido = GatoMapper.toEntity(request);

        assertEquals(gatoEsperado.getNome(), gatoRecebido.getNome());
        assertEquals(gatoEsperado.getCor(), gatoRecebido.getCor());
        assertEquals(gatoEsperado.getApelido(), gatoRecebido.getApelido());
        assertEquals(gatoEsperado.getImagem(), gatoRecebido.getImagem());
    }


    @Test @DisplayName("Deve retornar uma entidade nula quando o request for inválido")
    void deveRetornarUmaEntidadeGatoInválida(){

        IncluirGatoRequest request = new IncluirGatoRequest();

        Gato gatoRecebido = GatoMapper.toEntity(request);

        assertNull(gatoRecebido.getNome());
        assertNull(gatoRecebido.getCor());
        assertNull(gatoRecebido.getApelido());
        assertNull(gatoRecebido.getImagem());
    }

    @Test @DisplayName("Deve retornar uma response corretamente quando a entidade gato for válida")
    void deveRetornarUmResponseCorretamente(){

        Gato gato = GatoFactory.get();

        GatoResponse response = GatoMapper.toResponse(gato);

        assertEquals(gato.getNome(), response.getNome());
        assertEquals(gato.getApelido(), response.getApelido());
    }

    @Test @DisplayName("Deve retornar uma response nula quando a entidade gato for inválida")
    void deveRetornarUmResponseInvalido(){

        Gato gato = new Gato();

        GatoResponse response = GatoMapper.toResponse(gato);

        assertNull(response.getNome());
        assertNull(response.getApelido());
    }

}