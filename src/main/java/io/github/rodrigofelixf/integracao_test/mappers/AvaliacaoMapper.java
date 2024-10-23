package io.github.rodrigofelixf.integracao_test.mappers;


import io.github.rodrigofelixf.integracao_test.dtos.AvaliacaoFormRequest;
import io.github.rodrigofelixf.integracao_test.dtos.AvaliacaoResponse;
import io.github.rodrigofelixf.integracao_test.models.Avaliacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AvaliacaoMapper {

    @Mapping(source = "id", target = "codigoAvaliacao")
    AvaliacaoResponse toAvaliacaoResponse(Avaliacao entidade);

    List<AvaliacaoResponse> toAvaliacaoResponse(List<Avaliacao> entidades);

    Avaliacao toEntity(AvaliacaoFormRequest form);


}
