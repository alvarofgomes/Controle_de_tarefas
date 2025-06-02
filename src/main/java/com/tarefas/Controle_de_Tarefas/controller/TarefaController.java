package com.tarefas.Controle_de_Tarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tarefas.Controle_de_Tarefas.dto.TarefaDTO;
import com.tarefas.Controle_de_Tarefas.model.Tarefa;
import com.tarefas.Controle_de_Tarefas.model.Usuario;
import com.tarefas.Controle_de_Tarefas.service.TarefaService;
import com.tarefas.Controle_de_Tarefas.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
    	
    	log.info("Iniciando criação de tarefa para usuário ID: {}", dto.getUsuarioId());
    	
        Usuario usuario = usuarioService.buscarPorId(dto.getUsuarioId());

        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setDataEntrega(dto.getDataEntrega());
        tarefa.setConcluida(dto.getConcluida());
        tarefa.setUsuario(usuario);

        Tarefa salva = tarefaService.salvar(tarefa);
        
        log.info("Tarefa criada com sucesso - Descrição: {}", salva.getDescricao());
        
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
    	
    	log.info("Criando uma tarefa para usuário: {}", dto.getUsuarioId());
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
