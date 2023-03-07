package br.com.cwi.api.repository;

import br.com.cwi.api.domain.Publicacao;
import br.com.cwi.api.domain.VisualizacaoPublicacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {

    Page<Publicacao> findByVisualizacaoLike(VisualizacaoPublicacao visualizacao, Pageable pageable);
}
