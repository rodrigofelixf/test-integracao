package io.github.rodrigofelixf.integracao_test.services;

import io.github.rodrigofelixf.integracao_test.dtos.AvaliacaoFormRequest;
import io.github.rodrigofelixf.integracao_test.dtos.AvaliacaoResponse;
import io.github.rodrigofelixf.integracao_test.mappers.AvaliacaoMapper;
import io.github.rodrigofelixf.integracao_test.models.Avaliacao;
import io.github.rodrigofelixf.integracao_test.repositories.AvaliacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository repository;
    private final AvaliacaoMapper mapper;

    public AvaliacaoService(AvaliacaoRepository repository, AvaliacaoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public AvaliacaoResponse cadastrar(AvaliacaoFormRequest form) {

        Avaliacao entidadeParaSalvar = this.mapper.toEntity(form);
        Avaliacao entidadeSalva = this.repository.save(entidadeParaSalvar);
        return this.mapper.toAvaliacaoResponse(entidadeSalva);
    }

    public List<AvaliacaoResponse> buscar() {
        return this.mapper.toAvaliacaoResponse(this.repository.findAll());
    }

    public AvaliacaoResponse buscarPorId(Long id) throws Exception {

        return this.mapper.toAvaliacaoResponse(this.buscarEntidadePorId(id));
    }

    private Avaliacao buscarEntidadePorId(Long id) throws Exception {
        return this.repository.findById(id).orElseThrow(
                () -> new Exception("Avaliacao não encontrada"));
    }

    public AvaliacaoResponse alterar(AvaliacaoFormRequest form, Long id) throws Exception {

        Avaliacao avaliacaoExistente = buscarEntidadePorId(id);

        avaliacaoExistente.setNota(form.nota());
        avaliacaoExistente.setComentario(form.comentario());
        Avaliacao avaliacaoAtualizada = repository.save(avaliacaoExistente);

        return mapper.toAvaliacaoResponse(avaliacaoAtualizada);

    }

    public void remover(Long id) throws Exception {
        Avaliacao avaliacaoExistente = buscarEntidadePorId(id);
        this.repository.delete(avaliacaoExistente);
    }

}
