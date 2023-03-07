package br.com.cwi.api.service;

import br.com.cwi.api.domain.Gato;
import br.com.cwi.api.repository.GatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class BuscarGatoService {

    @Autowired
    private GatoRepository gatoRepository;

    public Gato porId(Long id){
        return gatoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                NOT_FOUND, "Gato não encontrado."
        ));
    }

}