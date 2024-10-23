package io.github.rodrigofelixf.integracao_test.controllers;

import io.github.rodrigofelixf.integracao_test.dtos.AvaliacaoFormRequest;
import io.github.rodrigofelixf.integracao_test.dtos.AvaliacaoResponse;
import io.github.rodrigofelixf.integracao_test.models.Avaliacao;
import io.github.rodrigofelixf.integracao_test.repositories.AvaliacaoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AvaliacaoControllerTest {


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;




    Avaliacao avaliacao;

    @BeforeAll
    public void init() {
        this.avaliacao = new Avaliacao();
        avaliacao.setComentario("Bom");
        avaliacao.setNota(10);
    }

    @Test
    public void criarNovaAvaliacaoTest() {
        AvaliacaoFormRequest formRequest = new AvaliacaoFormRequest(5, "Mediano");

        HttpEntity<AvaliacaoFormRequest> httpEntity = new HttpEntity<>(formRequest);

        ResponseEntity<AvaliacaoResponse> response = this.testRestTemplate
                .exchange("/avaliacao", HttpMethod.POST, httpEntity, AvaliacaoResponse.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().nota(), 5);
    }


    @Test
    public void buscarAvaliacoesTest() {
        ResponseEntity<AvaliacaoResponse[]> response = this.testRestTemplate
                .exchange("/avaliacao", HttpMethod.GET, null, AvaliacaoResponse[].class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void buscarAvaliacaoPorIdTest() {
        Avaliacao avaliacaoSalva = this.avaliacaoRepository.save(avaliacao);

        ResponseEntity<AvaliacaoResponse> response = this.testRestTemplate
                .exchange("/avaliacao/" + avaliacaoSalva.getId(), HttpMethod.GET, null, AvaliacaoResponse.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().nota(), 10);
        assertEquals(response.getBody().comentario(), "Bom");
    }

    @Test
    public void alterarAvaliacaoTest() {
        Avaliacao avaliacaoSalva = this.avaliacaoRepository.save(avaliacao);
        AvaliacaoFormRequest formRequest = new AvaliacaoFormRequest(5, "Mediano");

        HttpEntity<AvaliacaoFormRequest> httpEntity = new HttpEntity<>(formRequest);

        ResponseEntity<AvaliacaoResponse> response = this.testRestTemplate
                .exchange("/avaliacao/" + avaliacaoSalva.getId(), HttpMethod.PUT, httpEntity, AvaliacaoResponse.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().nota(), 5);
        assertEquals(response.getBody().comentario(), "Mediano");

    }


    @Test
    public void removerAvaliacaoTest() {

        Avaliacao avaliacaoSalva = this.avaliacaoRepository.save(this.avaliacao);

        ResponseEntity<Void> response = this.testRestTemplate
                .exchange("/avaliacao/" + avaliacaoSalva.getId(), HttpMethod.DELETE, null, Void.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNull(response.getBody());
    }
}