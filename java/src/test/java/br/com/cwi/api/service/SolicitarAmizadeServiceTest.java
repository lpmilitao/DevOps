package br.com.cwi.api.service;

import br.com.cwi.api.domain.Solicitacao;
import br.com.cwi.api.factories.SimpleFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.repository.SolicitacaoRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.BuscarUsuarioService;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import br.com.cwi.api.validator.VerificarAmizadeExistenteValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SolicitarAmizadeServiceTest {

    @InjectMocks
    private SolicitarAmizadeService tested;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private BuscarUsuarioService buscarUsuarioService;

    @Mock
    private SolicitacaoRepository solicitacaoRepository;

    @Mock
    private VerificarAmizadeExistenteValidator verificarAmizadeExistenteValidator;

    @Captor
    private ArgumentCaptor<Solicitacao> solicitacaoCaptor;

    @Test @DisplayName("Deve solicitar a amizade corretamente quando os dois usuários não " +
            "são amigos e nem tem uma solicitação de amizade já feita.")
    void deveSolicitarAmizade(){

        Usuario usuario = UsuarioFactory.get();
        Usuario amigo = UsuarioFactory.get();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarUsuarioService.porId(amigo.getId())).thenReturn(amigo);

        tested.solicitar(amigo.getId());

        verify(solicitacaoRepository).save(solicitacaoCaptor.capture());

        Solicitacao solicitacao = solicitacaoCaptor.getValue();
        assertEquals(usuario.getId(), solicitacao.getUsuario().getId());
        assertEquals(amigo.getId(), solicitacao.getAmigo().getId());
    }

    @Test @DisplayName("Deve lançar uma exceção quando os dois usuários já forem amigos")
    void deveRecusarSolicitacaoPoisAmizadeJaExiste(){

        Usuario usuario = UsuarioFactory.get();
        Usuario amigo = UsuarioFactory.get();
        usuario.comecarAmizade(amigo);

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarUsuarioService.porId(amigo.getId())).thenReturn(amigo);

        doThrow(ResponseStatusException.class)
                .when(verificarAmizadeExistenteValidator).jaSaoAmigos(usuario.getAmigos(), amigo);

        assertThrows(ResponseStatusException.class, () -> {
            tested.solicitar(amigo.getId());
        });

        verify(solicitacaoRepository, never()).save(solicitacaoCaptor.capture());
    }

    @Test @DisplayName("Deve lançar uma exceção quando o usuário já enviou uma solicitação para aquela pessoa.")
    void deveRecusarSolicitacaoPoisSolicitacaoJaExiste(){

        Usuario usuario = UsuarioFactory.get();
        Usuario amigo = UsuarioFactory.get();
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setId(SimpleFactory.getRandomLong());
        solicitacao.setUsuario(usuario);
        solicitacao.setAmigo(amigo);
        usuario.getSoliciteiAmizade().add(solicitacao);
        amigo.getMeSolicitaram().add(solicitacao);

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarUsuarioService.porId(amigo.getId())).thenReturn(amigo);

        doThrow(ResponseStatusException.class)
                .when(verificarAmizadeExistenteValidator).jaSolicitado(usuario.getSoliciteiAmizade(), amigo);

        assertThrows(ResponseStatusException.class, () -> {
            tested.solicitar(amigo.getId());
        });

        verify(solicitacaoRepository, never()).save(solicitacaoCaptor.capture());
    }


}
