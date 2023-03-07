package br.com.cwi.api.repository;

import br.com.cwi.api.domain.Gato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatoRepository extends JpaRepository<Gato, Long> {
}
