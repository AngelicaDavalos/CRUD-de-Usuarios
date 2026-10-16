package mx.edu.utez.proyecto1E.controller.usuario;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1E.controller.dtos.RequestUpdateUsuarioDTO;
import mx.edu.utez.proyecto1E.controller.usuario.dto.RequestCreateUserDto;
import mx.edu.utez.proyecto1E.model.usuario.Usuario;
import mx.edu.utez.proyecto1E.service.usuario.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsers() {
        return ResponseEntity.ok(usuarioService.getAllUsers());
    }

    // Consultar usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
    }

    // Crear un usuario
    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody @Valid RequestCreateUserDto payload) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.createUser(payload));
    }

    // Activar / Desactivar usuario por ID
    @PatchMapping("/{id}/status")
    public ResponseEntity<Usuario> changeStatus(
            @PathVariable Long id,
            @RequestParam boolean enable) {
        return ResponseEntity.ok(usuarioService.changeStatus(id, enable));
    }

    // Actualizar username y descripción
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid RequestUpdateUsuarioDTO payload) {
        return ResponseEntity.ok(usuarioService.updateUser(id, payload));
    }
}

