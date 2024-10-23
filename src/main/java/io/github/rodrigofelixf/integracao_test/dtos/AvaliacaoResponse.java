package io.github.rodrigofelixf.integracao_test.dtos;

import java.time.LocalDateTime;

public record AvaliacaoResponse(
        Long codigoAvaliacao,
        Integer nota,
        String comentario,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {
}
