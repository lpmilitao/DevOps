package br.com.cwi.api.security.controller;

import br.com.cwi.api.controller.response.AmigoResponse;
import br.com.cwi.api.controller.response.SolicitacaoResponse;
import br.com.cwi.api.controller.response.UsuarioDetalhadoResponse;
import br.com.cwi.api.security.controller.request.EditarUsuarioRequest;
import br.com.cwi.api.security.controller.request.UsuarioRequest;
import br.com.cwi.api.security.controller.response.UsuarioResponse;
import br.com.cwi.api.security.service.DesfazerAmizadeService;
import br.com.cwi.api.security.service.DetalharUsuarioService;
import br.com.cwi.api.security.service.EditarUsuarioService;
import br.com.cwi.api.security.service.IncluirUsuarioService;
import br.com.cwi.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.cwi.api.security.domain.Funcao.Nomes.USUARIO;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IncluirUsuarioService incluirUsuarioService;

    @Autowired
    private DetalharUsuarioService detalharUsuarioService;

    @Autowired
    private SolicitarAmizadeService solicitarAmizadeService;

    @Autowired
    private AceitarAmizadeService aceitarAmizadeService;

    @Autowired
    private ListarSolicitacoesService listarSolicitacoesService;

    @Autowired
    private ListarAmigosService listarAmigosService;

    @Autowired
    private DesfazerAmizadeService desfazerAmizadeService;

    @Autowired
    private ListarUsuariosService listarUsuariosService;

    @Autowired
    private EditarUsuarioService editarUsuarioService;

    @PostMapping
    public UsuarioResponse incluir(@RequestBody UsuarioRequest request) {
        return incluirUsuarioService.incluir(request);
    }

    @Secured(USUARIO)
    @GetMapping("/eu")
    public UsuarioDetalhadoResponse detalhar(){
        return detalharUsuarioService.detalhar();
    }

    @Secured(USUARIO)
    @PostMapping("/{amigoId}/solicitar")
    public void solicitarAmizade(@PathVariable Long amigoId){
        solicitarAmizadeService.solicitar(amigoId);
    }

    @Secured(USUARIO)
    @PutMapping("/{solicitacaoId}/aceitar")
    public void aceitarAmizade(@PathVariable Long solicitacaoId){
        aceitarAmizadeService.aceitar(solicitacaoId);
    }

    @Secured(USUARIO)
    @GetMapping("/solicitacoes-feitas")
    public List<SolicitacaoResponse> listarSolicitacoesFeitasPeloUsuario(){
        return listarSolicitacoesService.peloUsuario();
    }

    @Secured(USUARIO)
    @GetMapping("/solicitacoes")
    public List<SolicitacaoResponse> listarSolicitacoesFeitasParaOUsuario(){
        return listarSolicitacoesService.paraUsuario();
    }

    @Secured(USUARIO)
    @GetMapping("/amigos")
    public Page<AmigoResponse> listarAmigos(@RequestParam String filtro, Pageable pageable){
        return listarAmigosService.listar(filtro, pageable);
    }

    @Secured(USUARIO)
    @DeleteMapping("/amigos/{id}")
    public void desfazerAmizade(@PathVariable Long id){
        desfazerAmizadeService.desfazer(id);
    }

    @Secured(USUARIO)
    @GetMapping
    public Page<UsuarioDetalhadoResponse> listarUsuarios(@RequestParam String filtro, Pageable pageable){
        return listarUsuariosService.listar(filtro, pageable);
    }

    @Secured(USUARIO)
    @PutMapping
    public void editarUsuario(@RequestBody EditarUsuarioRequest request){
        editarUsuarioService.editar(request);
    }



}
