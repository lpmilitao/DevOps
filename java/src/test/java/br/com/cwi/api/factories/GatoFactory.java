package br.com.cwi.api.factories;

import br.com.cwi.api.domain.Gato;

public class GatoFactory {

    public static Gato get() {
        Gato gato = new Gato();
        gato.setId(SimpleFactory.getRandomLong());
        gato.setNome("Gato teste");
        gato.setCor("Preto");
        gato.setApelido("Miau");
        gato.setImagem("");
        gato.setDono(UsuarioFactory.get());
        return gato;
    }

}
