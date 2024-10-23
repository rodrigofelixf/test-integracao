package io.github.rodrigofelixf.integracao_test.repositories;

import io.github.rodrigofelixf.integracao_test.models.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
}
