package com.tarefas.Controle_de_Tarefas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tarefas.Controle_de_Tarefas.model.Tarefa;
import com.tarefas.Controle_de_Tarefas.model.Usuario;
import com.tarefas.Controle_de_Tarefas.repository.TarefaRepository;
import com.tarefas.Controle_de_Tarefas.service.TarefaService;

@ExtendWith(MockitoExtension.class)
class TarefaServiceTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @InjectMocks
    private TarefaService tarefaService;

    private Tarefa tarefa;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Álvaro");

        tarefa = new Tarefa();
        tarefa.setId(1L);
        tarefa.setDescricao("Estudar Java");
        tarefa.setConcluida(false);
        tarefa.setDataEntrega(LocalDate.now().plusDays(3));
        tarefa.setUsuario(usuario);
    }

    @Test
    void deveSalvarTarefa() {
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefa);

        Tarefa salva = tarefaService.salvar(tarefa);

        assertNotNull(salva);
        assertEquals("Estudar Java", salva.getDescricao());
        verify(tarefaRepository, times(1)).save(tarefa);
    }

    @Test
    void deveListarTarefasPorStatus() {
        when(tarefaRepository.findByConcluida(false)).thenReturn(Arrays.asList(tarefa));

        List<Tarefa> lista = tarefaService.listarPorStatus(false);

        assertEquals(1, lista.size());
        assertFalse(lista.get(0).getConcluida());
        verify(tarefaRepository, times(1)).findByConcluida(false);
    }

    @Test
    void deveAtualizarTarefa() {
        Tarefa nova = new Tarefa();
        nova.setDescricao("Revisar Spring");
        nova.setConcluida(true);
        nova.setDataEntrega(LocalDate.now().plusDays(2));
        nova.setUsuario(usuario);

        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(nova);

        Tarefa atualizada = tarefaService.atualizar(1L, nova);

        assertEquals("Revisar Spring", atualizada.getDescricao());
        assertTrue(atualizada.getConcluida());
    }

    @Test
    void deveDeletarTarefa() {
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));

        tarefaService.deletar(1L);

        verify(tarefaRepository, times(1)).delete(tarefa);
    }
} 
