package br.com.cwi.api.validator;

import br.com.cwi.api.domain.Solicitacao;
import br.com.cwi.api.security.domain.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class VerificarAmizadeExistenteValidator {

    public void jaSaoAmigos(List<Usuario> amigos , Usuario amigo){

        boolean jaEAmigo = amigos.stream()
                .anyMatch(amigoDoUsuario -> amigoDoUsuario.getId() == amigo.getId());

        if(jaEAmigo){
            throw new ResponseStatusException(BAD_REQUEST, "Você já é amigo dessa pessoa.");
        }
    }

    public void jaSolicitado(List<Solicitacao> solicitacoes, Usuario amigo){

        boolean jaSolicitou = solicitacoes.stream()
                .anyMatch(solicitacao -> solicitacao.getAmigo().getId() == amigo.getId());

        if(jaSolicitou){
            throw new ResponseStatusException(BAD_REQUEST, "Você já solicitou uma amizade com essa pessoa.");
        }
    }

}
