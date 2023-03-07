package br.com.cwi.api.security.domain;

import br.com.cwi.api.domain.Comentario;
import br.com.cwi.api.domain.Gato;
import br.com.cwi.api.domain.Publicacao;
import br.com.cwi.api.domain.Solicitacao;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Usuario {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    private String email;

    private String apelido;

    private LocalDate dataNascimento;

    private String senha;

    @URL
    private String imagemPerfil;

    private boolean ativo;

    @OneToMany (mappedBy = "dono")
    private List<Gato> gatos = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "amizade",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "amigo_id"))
    private List<Usuario> amigos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Solicitacao> soliciteiAmizade = new ArrayList<>();

    @OneToMany(mappedBy = "amigo")
    private List<Solicitacao> meSolicitaram = new ArrayList<>();

    @OneToMany(mappedBy = "autor")
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Permissao> permissoes = new ArrayList<>();

    @OneToMany(mappedBy = "autor")
    private List<Publicacao> publicacoes = new ArrayList<>();

    public void adicionarPermissao(Permissao permissao) {
        this.permissoes.add(permissao);
        permissao.setUsuario(this);
    }

    public void adicionarGato(Gato novoGato){
        this.gatos.add(novoGato);
        novoGato.setDono(this);
    }

    public void adicionarPublicacao(Publicacao novaPublicacao){
        this.publicacoes.add(novaPublicacao);
        novaPublicacao.setAutor(this);
    }

    public void adicionarComentario(Comentario novoComentario){
        this.comentarios.add(novoComentario);
        novoComentario.setAutor(this);
    }

    public void comecarAmizade(Usuario amigo){
        this.getAmigos().add(amigo);
        amigo.getAmigos().add(this);
    }

    public void terminarAmizade(Usuario exAmigo){
        this.getAmigos().removeIf(amigo -> amigo.getId() == exAmigo.getId());
        exAmigo.getAmigos().removeIf(amigo -> amigo.getId() == this.id);

    }

}
