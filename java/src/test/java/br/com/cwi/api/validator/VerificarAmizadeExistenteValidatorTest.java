package br.com.cwi.api.validator;

import br.com.cwi.api.domain.Solicitacao;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.factories.UsuarioFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class VerificarAmizadeExistenteValidatorTest {

    @InjectMocks
    private VerificarAmizadeExistenteValidator tested;

    @Test @DisplayName("Não deve fazer nada quando os usuários não tem uma amizade")
    void naoFazNadaSeUsuariosNaoSaoAmigos(){
        Usuario usuario = UsuarioFactory.get();
        Usuario amigo = UsuarioFactory.get();

        tested.jaSaoAmigos(usuario.getAmigos(), amigo);
    }

    @Test @DisplayName("Deve lançar uma exceção quando os usuários já tiverem uma amizade.")
    void deveLancarUmaExcecaoPorqueOsUsuariosSaoAmigos(){
        Usuario usuario = UsuarioFactory.get();
        Usuario amigo = UsuarioFactory.get();
        usuario.comecarAmizade(amigo);

        assertThrows(ResponseStatusException.class, () -> {
            tested.jaSaoAmigos(usuario.getAmigos(), amigo);
        });
    }

    @Test @DisplayName("Não deve fazer nada quando um usuário ainda não enviou uma solicitação para o outro.")
    void naoFazNadaSeUsuarioNaoSolicitouAmizade(){
        Usuario usuario = UsuarioFactory.get();
        Usuario amigo = UsuarioFactory.get();

        tested.jaSolicitado(usuario.getSoliciteiAmizade(), amigo);
    }

    @Test @DisplayName("Deve lançar uma exceção quando um usuário já enviou solitação ao outro.")
    void deveLancarUmaExcecaoPorqueJaSolicitouAmizade(){
        Usuario usuario = UsuarioFactory.get();
        Usuario amigo = UsuarioFactory.get();
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setUsuario(usuario);
        solicitacao.setAmigo(amigo);
        usuario.getSoliciteiAmizade().add(solicitacao);

        assertThrows(ResponseStatusException.class, () -> {
            tested.jaSolicitado(usuario.getSoliciteiAmizade(), amigo);
        });
    }

}
