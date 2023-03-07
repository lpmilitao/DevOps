package br.com.cwi.api.domain;

import br.com.cwi.api.security.domain.Usuario;
import lombok.*;

import javax.persistence.*;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
public class Gato {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cor;

    private String apelido;

    private String imagem;

    @ManyToOne
    @JoinColumn(name = "dono_id")
    private Usuario dono;

}