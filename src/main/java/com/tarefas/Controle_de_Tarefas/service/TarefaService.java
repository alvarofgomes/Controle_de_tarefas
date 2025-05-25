package com.tarefas.Controle_de_Tarefas.service;

import com.tarefas.Controle_de_Tarefas.model.Tarefa;
import com.tarefas.Controle_de_Tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa salvar(Tarefa tarefa) {
        // Exemplo de regra de negócio não salvar tarefas sem descrição
        if (tarefa.getDescricao() == null || tarefa.getDescricao().isBlank()) {
            throw new RuntimeException("A descrição da tarefa é obrigatória.");
        }

        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarPorUsuario(Long usuarioId) {
        return tarefaRepository.findByUsuarioId(usuarioId);
    }

    public Optional<Tarefa> buscarPorId(Long id) {
        return tarefaRepository.findById(id);
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
            tarefa.setConcluida(novaTarefa.isConcluida());
            return tarefaRepository.save(tarefa);
        }).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }
    
}