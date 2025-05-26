package com.tarefas.Controle_de_Tarefas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tarefas.Controle_de_Tarefas.model.Usuario;
import com.tarefas.Controle_de_Tarefas.repository.UsuarioRepository;
import com.tarefas.Controle_de_Tarefas.service.UsuarioService;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    public UsuarioServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("João");
        usuario.setEmail("joao@email.com");

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario salvo = usuarioService.salvar(usuario);

        assertNotNull(salvo);
        assertEquals("João", salvo.getNome());
        assertEquals("joao@email.com", salvo.getEmail());
    }

    @Test
    void deveBuscarUsuarioPorId() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Maria");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario encontrado = usuarioService.buscarPorId(1L);

        assertNotNull(encontrado);
        assertEquals("Maria", encontrado.getNome());
    }

    @Test
    void deveBuscarUsuarioPorEmail() {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@email.com");

        when(usuarioRepository.findByEmail("teste@email.com")).thenReturn(Optional.of(usuario));

        Optional<Usuario> encontrado = usuarioService.buscarPorEmail("teste@email.com");

        assertTrue(encontrado.isPresent());
        assertEquals("teste@email.com", encontrado.get().getEmail());
    }
}
