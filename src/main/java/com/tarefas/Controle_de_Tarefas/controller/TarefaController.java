package com.tarefas.Controle_de_Tarefas.controller;

import com.tarefas.Controle_de_Tarefas.dto.TarefaDTO;
import com.tarefas.Controle_de_Tarefas.model.Tarefa;
import com.tarefas.Controle_de_Tarefas.model.Usuario;
import com.tarefas.Controle_de_Tarefas.service.TarefaService;
import com.tarefas.Controle_de_Tarefas.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @PostMapping
    @Operation(summary = "Criar uma nova tarefa")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos ou usuário não encontrado")
    })
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

    @GetMapping
    @Operation(summary = "Listar tarefas por status de conclusão")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefas listadas com sucesso")
    })
    public ResponseEntity<List<Tarefa>> listarPorStatus(@RequestParam Boolean concluida) {
        List<Tarefa> lista = tarefaService.listarPorStatus(concluida);
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar uma tarefa existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos ou usuário não encontrado")
    })
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

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma tarefa")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Tarefa deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
