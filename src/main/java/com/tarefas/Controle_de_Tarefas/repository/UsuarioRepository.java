// Essa interface já traz os métodos padrão do JPA e cria um método de busca por email

package com.tarefas.Controle_de_Tarefas.repository;

import com.tarefas.Controle_de_Tarefas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}