package com.tarefas.Controle_de_Tarefas.controller;

import com.tarefas.Controle_de_Tarefas.dto.TarefaDTO;
import com.tarefas.Controle_de_Tarefas.model.Tarefa;
import com.tarefas.Controle_de_Tarefas.model.Usuario;
import com.tarefas.Controle_de_Tarefas.service.TarefaService;
import com.tarefas.Controle_de_Tarefas.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private UsuarioService usuarioService;

    // POST /tarefas
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid TarefaDTO dto) {
    	Usuario usuario = usuarioService.buscarPorId(dto.getUsuarioId());

        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setDataEntrega(dto.getDataEntrega());
        tarefa.setConcluida(dto.getConcluida());
        tarefa.setUsuario(usuario);

        Tarefa salva = tarefaService.salvar(tarefa);
        return ResponseEntity.ok(salva);
    }

    // GET
    @GetMapping
    public ResponseEntity<List<Tarefa>> listarPorStatus(@RequestParam Boolean concluida) {
        List<Tarefa> lista = tarefaService.listarPorStatus(concluida);
        return ResponseEntity.ok(lista);
    }

    // PUT /tarefas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody @Valid TarefaDTO dto) {
    	Usuario usuario = usuarioService.buscarPorId(dto.getUsuarioId());
    	
        Tarefa nova = new Tarefa();
        nova.setDescricao(dto.getDescricao());
        nova.setDataEntrega(dto.getDataEntrega());
        nova.setConcluida(dto.getConcluida());
        nova.setUsuario(usuario);

        Tarefa atualizada = tarefaService.atualizar(id, nova);
        return ResponseEntity.ok(atualizada);
    }

    // DELETE /tarefas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
