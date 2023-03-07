package br.com.cwi.api.controller;

import br.com.cwi.api.controller.request.IncluirComentarioRequest;
import br.com.cwi.api.controller.request.IncluirPublicacaoRequest;
import br.com.cwi.api.controller.response.PublicacaoResponse;
import br.com.cwi.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.com.cwi.api.security.domain.Funcao.Nomes.USUARIO;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/publicacoes")
public class PublicacaoController {

    @Autowired
    private IncluirPublicacaoService incluirPublicacaoService;

    @Autowired
    private IncluirComentarioService incluirComentarioService;

    @Autowired
    private ListarPublicacoesService listarPublicacoesService;

    @Autowired
    private CurtirPublicacaoService curtirPublicacaoService;

    @Autowired
    private ListarPublicacoesUsuarioService listarPublicacoesUsuarioService;

    @Secured(USUARIO)
    @PostMapping
    @ResponseStatus(CREATED)
    public void publicar(@Valid @RequestBody IncluirPublicacaoRequest request){
        incluirPublicacaoService.publicar(request);
    }

    @Secured(USUARIO)
    @PostMapping("/{id}/comentar")
    @ResponseStatus(CREATED)
    public void comentar(@PathVariable Long id, @Valid @RequestBody IncluirComentarioRequest request){
        incluirComentarioService.comentar(id, request);
    }

    @Secured(USUARIO)
    @GetMapping
    public Page<PublicacaoResponse> listarPublicacoes(Pageable pageable){
        return listarPublicacoesService.listar(pageable);
    }

    @Secured(USUARIO)
    @PutMapping("/{idPublicacao}/curtir")
    public void curtirPublicacao(@PathVariable Long idPublicacao){
        curtirPublicacaoService.curtir(idPublicacao);
    }

    @Secured(USUARIO)
    @DeleteMapping("/{idPublicacao}/curtir")
    public void retirarCurtirPublicacao(@PathVariable Long idPublicacao){
        curtirPublicacaoService.retirarCurtir(idPublicacao);
    }

    @Secured(USUARIO)
    @GetMapping("/usuario/{id}")
    public Page<PublicacaoResponse> listarPublicacoesDoUsuario(@PathVariable Long id){
        return listarPublicacoesUsuarioService.listar(id);
    }

    @Secured(USUARIO)
    @GetMapping("/publicas")
    public Page<PublicacaoResponse> listarTodasPublicacoesPublicas(Pageable pageable){
        return listarPublicacoesService.listarPublicas(pageable);
    }

}