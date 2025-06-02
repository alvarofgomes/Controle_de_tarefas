package com.tarefas.Controle_de_Tarefas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarefas.Controle_de_Tarefas.exception.ResourceNotFoundException;
import com.tarefas.Controle_de_Tarefas.model.Tarefa;
import com.tarefas.Controle_de_Tarefas.repository.TarefaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa salvar(Tarefa tarefa) {
    	
    	log.info("Salvando nova tarefa para usuário ID: {}",tarefa.getUsuario().getId());
        if (tarefa.getDescricao() == null || tarefa.getDescricao().isBlank()) {
        	throw new IllegalArgumentException("A descrição da tarefa é obrigatória.");
        }

        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarPorUsuario(Long usuarioId) {
        return tarefaRepository.findByUsuarioId(usuarioId);
    }

    public Tarefa buscarPorId(Long id) {
    	log.debug("Buscando tarefa por ID: {}", id);
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa com ID " + id + " não encontrada"));
    }

    public void deletar(Long id) {
        tarefaRepository.deleteById(id);
    }
    
    public List<Tarefa> listarPorStatus(Boolean concluida) {
        return tarefaRepository.findByConcluida(concluida);
    }

    public Tarefa atualizar(Long id, Tarefa novaTarefa) {
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefa.setDescricao(novaTarefa.getDescricao());
            tarefa.setDataEntrega(novaTarefa.getDataEntrega());
            tarefa.setConcluida(novaTarefa.getConcluida());
            return tarefaRepository.save(tarefa);
        }).orElseThrow(() -> new ResourceNotFoundException("Tarefa com ID " + id + " não encontrada"));
    }
    
}