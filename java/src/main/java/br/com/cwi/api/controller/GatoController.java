package br.com.cwi.api.controller;

import br.com.cwi.api.controller.request.IncluirGatoRequest;
import br.com.cwi.api.controller.response.GatoDetalhadoResponse;
import br.com.cwi.api.controller.response.IdResponse;
import br.com.cwi.api.service.EditarGatoService;
import br.com.cwi.api.service.IncluirGatoService;
import br.com.cwi.api.service.ListarGatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static br.com.cwi.api.security.domain.Funcao.Nomes.USUARIO;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/gatos")
public class GatoController {

    @Autowired
    private IncluirGatoService incluirGatoService;

    @Autowired
    private EditarGatoService editarGatoService;

    @Autowired
    private ListarGatosService listarGatosService;

    @Secured(USUARIO)
    @PostMapping
    @ResponseStatus(CREATED)
    public IdResponse incluirGato(@Valid @RequestBody IncluirGatoRequest request){
        return incluirGatoService.incluir(request);
    }

    @Secured(USUARIO)
    @PutMapping("/{id}")
    public void editarGato(@PathVariable Long id, @Valid @RequestBody IncluirGatoRequest request){
        editarGatoService.editar(id, request);
    }

    @Secured(USUARIO)
    @GetMapping()
    public List<GatoDetalhadoResponse> listarMeusGatos(){
        return listarGatosService.listarMeusGatos();
    }

    @Secured(USUARIO)
    @GetMapping("/{id}")
    public List<GatoDetalhadoResponse> listarGatosDoUsuario(@PathVariable Long id){
        return listarGatosService.listarGatosDoUsuario(id);
    }


}
