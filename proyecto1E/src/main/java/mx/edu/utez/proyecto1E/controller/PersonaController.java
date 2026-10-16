package mx.edu.utez.proyecto1E.controller;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1E.controller.dtos.RequestUpdatePersonaDTO;
import mx.edu.utez.proyecto1E.model.persona.Persona;
import mx.edu.utez.proyecto1E.service.usuario.PersonaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/persona")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    // Obtener todas las personas
    @GetMapping
    public ResponseEntity<List<Persona>> getAllPersonas() {
        return ResponseEntity.ok(personaService.getAllPersonas());
    }

    // Consultar una persona por ID
    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.getPersonaById(id));
    }

    // Actualizar la información de una persona
    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(
            @PathVariable Long id,
            @RequestBody @Valid RequestUpdatePersonaDTO payload) {
        return ResponseEntity.ok(personaService.updatePersona(id, payload));
    }
}
