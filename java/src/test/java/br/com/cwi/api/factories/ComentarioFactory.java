package br.com.cwi.api.factories;

import br.com.cwi.api.domain.Comentario;

public class ComentarioFactory {

    public static Comentario get() {
        Comentario comentario = new Comentario();
        comentario.setId(SimpleFactory.getRandomLong());
        comentario.setMensagem("Também amo gatinhos!");
        return comentario;
    }

}
