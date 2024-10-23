package io.github.rodrigofelixf.integracao_test.controllers;

import io.github.rodrigofelixf.integracao_test.dtos.AvaliacaoFormRequest;
import io.github.rodrigofelixf.integracao_test.dtos.AvaliacaoResponse;
import io.github.rodrigofelixf.integracao_test.services.AvaliacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/avaliacao")
@RestController
public class AvaliacaoController {

    private final AvaliacaoService service;

    public AvaliacaoController(AvaliacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AvaliacaoResponse> cadastrar(@RequestBody AvaliacaoFormRequest form) {
        return ResponseEntity.ok().body(this.service.cadastrar(form));
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoResponse>> buscar() {
        return ResponseEntity.ok().body(this.service.buscar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoResponse> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(this.service.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoResponse> alterar(@RequestBody AvaliacaoFormRequest form, @PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(this.service.alterar(form, id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) throws Exception {
        this.service.remover(id);
        return ResponseEntity.ok().build();
    }
}
