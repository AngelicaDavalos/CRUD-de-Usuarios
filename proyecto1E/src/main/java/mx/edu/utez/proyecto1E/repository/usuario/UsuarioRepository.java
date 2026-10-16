package mx.edu.utez.proyecto1E.repository.usuario;

import mx.edu.utez.proyecto1E.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    //Optional nos sirve por si la consulta trae nulo
    //Consulta por query method(lo tranforma en sql)
    Optional<Usuario> findByUsername(String username);

    boolean existsByUsernameAndIdNot(String username, Long id);

    boolean existsByUsername(String username);
    
    

}
