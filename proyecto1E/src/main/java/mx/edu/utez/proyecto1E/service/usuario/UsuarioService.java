package mx.edu.utez.proyecto1E.service.usuario;

import jakarta.transaction.Transactional;
import mx.edu.utez.proyecto1E.controller.dtos.RequestUpdateUsuarioDTO;
import mx.edu.utez.proyecto1E.controller.usuario.dto.RequestCreateUserDto;
import mx.edu.utez.proyecto1E.exception.customExceptions.CustomBadException;
import mx.edu.utez.proyecto1E.model.persona.Persona;
import mx.edu.utez.proyecto1E.model.usuario.Roles;
import mx.edu.utez.proyecto1E.model.usuario.Usuario;
import mx.edu.utez.proyecto1E.repository.persona.PersonaRepository;
import mx.edu.utez.proyecto1E.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PersonaRepository personaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.personaRepository = personaRepository;
    }

    // Obtener todos los usuarios
    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    // Consultar usuario por ID con validación de existencia
    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new CustomBadException("El usuario con el ID " + id + " no existe."));
    }

    // Crear un usuario y registrar su persona asociada
    @Transactional
    public Usuario createUser(RequestCreateUserDto payload) {
        Optional<Usuario> usuarioExist = usuarioRepository.findByUsername(payload.getUsername());
        if (usuarioExist.isPresent()) {
            throw new CustomBadException("El username '" + payload.getUsername() + "' ya existe.");
        }

        Persona newPersona = new Persona();
        newPersona.setNombres(payload.getNombres());
        newPersona.setPrimerApellido(payload.getPrimerApellido());
        newPersona.setSegundoApellido(payload.getSegundoApellido());
        newPersona.setCurp(payload.getCurp());
        newPersona.setEmail(payload.getCorreo());
        newPersona.setFechaNacimiento(payload.getFechaNacimiento());
        newPersona = personaRepository.save(newPersona);

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(payload.getUsername());
        nuevoUsuario.setDescripcion(payload.getDescripcion());
        nuevoUsuario.setPassword(payload.getPassword());
        nuevoUsuario.setRol(Roles.valueOf(payload.getRol()));
        nuevoUsuario.set_enable(true);
        nuevoUsuario.setPersona(newPersona);

        return usuarioRepository.save(nuevoUsuario);
    }

    // Activar o Desactivar usuario por ID
    @Transactional
    public Usuario changeStatus(Long id, boolean enable) {
        Usuario usuario = getUsuarioById(id);
        usuario.set_enable(enable);
        return usuarioRepository.save(usuario);
    }

    // Actualizar username y descripción sin perder la relación con Persona ni otros datos
    @Transactional
    public Usuario updateUser(Long id, RequestUpdateUsuarioDTO payload) {
        // 1. Obtener la entidad gestionada por JPA (lanza excepción si no existe)
        Usuario usuarioExistente = getUsuarioById(id);

        // 2. Validar disponibilidad del username si cambia
        if (!usuarioExistente.getUsername().equalsIgnoreCase(payload.getUsername())) {
            if (usuarioRepository.existsByUsernameAndIdNot(payload.getUsername(), id)) {
                throw new CustomBadException("El username '" + payload.getUsername() + "' ya está registrado por otro usuario.");
            }
        }

        //  Modificar solo los campos permitidos
        usuarioExistente.setUsername(payload.getUsername());
        usuarioExistente.setDescripcion(payload.getDescripcion());

        // Al guardar la entidad gestionada existente, Spring Data JPA / Hibernate realiza un UPDATE
        // conservando id, password, rol, is_enable y la relación persona_id sin alterarlos.
        return usuarioRepository.save(usuarioExistente);
    }
}


