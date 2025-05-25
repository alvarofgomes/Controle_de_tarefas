// Aqui a gente cria um método que busca todas as tarefas de um usuário específico.

package com.tarefas.Controle_de_Tarefas.repository;

import com.tarefas.Controle_de_Tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByUsuarioId(Long usuarioId);
}
