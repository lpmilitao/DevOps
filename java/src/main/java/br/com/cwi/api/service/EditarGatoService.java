package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.IncluirGatoRequest;
import br.com.cwi.api.domain.Gato;
import br.com.cwi.api.repository.GatoRepository;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditarGatoService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private GatoRepository gatoRepository;

    @Autowired
    private BuscarGatoService buscarGatoService;

    @Transactional
    public void editar(Long id, IncluirGatoRequest request) {

        Gato gato = buscarGatoService.porId(id);

        if (!(gato.getNome().equalsIgnoreCase(request.getNome()))){
            gato.setNome(request.getNome());
        }

        if (!(gato.getApelido().equalsIgnoreCase(request.getApelido()))){
            gato.setApelido(request.getApelido());
        }

        if (!(gato.getImagem().equalsIgnoreCase(request.getImagem()))){
            gato.setImagem(request.getImagem());
        }

        gatoRepository.save(gato);
    }
}
