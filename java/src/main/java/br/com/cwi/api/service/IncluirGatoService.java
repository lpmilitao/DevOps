package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.IncluirGatoRequest;
import br.com.cwi.api.controller.response.IdResponse;
import br.com.cwi.api.domain.Gato;
import br.com.cwi.api.repository.GatoRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.api.mapper.GatoMapper.toEntity;
import static br.com.cwi.api.mapper.IdMapper.toResponse;

@Service
public class IncluirGatoService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GatoRepository gatoRepository;

    @Transactional
    public IdResponse incluir(IncluirGatoRequest request) {

        Usuario dono = usuarioAutenticadoService.get();

        Gato gato = toEntity(request);
        dono.adicionarGato(gato);

        gatoRepository.save(gato);
        usuarioRepository.save(dono);

        return toResponse(gato.getId());
    }
}
