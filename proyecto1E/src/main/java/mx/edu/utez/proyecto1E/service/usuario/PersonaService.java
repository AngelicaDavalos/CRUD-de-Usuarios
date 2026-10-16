package mx.edu.utez.proyecto1E.service.usuario;

import jakarta.transaction.Transactional;
import mx.edu.utez.proyecto1E.controller.dtos.RequestUpdatePersonaDTO;
import mx.edu.utez.proyecto1E.exception.customExceptions.CustomBadException;
import mx.edu.utez.proyecto1E.model.persona.Persona;
import mx.edu.utez.proyecto1E.repository.persona.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    public Persona getPersonaById(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new CustomBadException("La persona con el ID " + id + " no existe."));
    }

    @Transactional
    public Persona updatePersona(Long id, RequestUpdatePersonaDTO payload) {
        Persona personaExistente = getPersonaById(id);

        personaExistente.setNombres(payload.getNombres());
        personaExistente.setPrimerApellido(payload.getPrimerApellido());
        personaExistente.setSegundoApellido(payload.getSegundoApellido());
        personaExistente.setCurp(payload.getCurp());
        personaExistente.setEmail(payload.getEmail());
        personaExistente.setFechaNacimiento(payload.getFechaNacimiento());

        return personaRepository.save(personaExistente);
    }
}

