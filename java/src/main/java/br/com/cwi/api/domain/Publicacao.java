package br.com.cwi.api.domain;

import br.com.cwi.api.security.domain.Usuario;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensagem;

    @URL
    private String imagem;

    private Long curtidas;

    @Enumerated(STRING)
    private VisualizacaoPublicacao visualizacao;

    private LocalDate dataPublicacao;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @OneToMany(mappedBy = "publicacao")
    private List<Comentario> comentarios = new ArrayList<>();

    public void adicionarComentario(Comentario novoComentario){
        this.comentarios.add(novoComentario);
        novoComentario.setPublicacao(this);
    }


}