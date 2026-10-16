package mx.edu.utez.proyecto1E.model.direccion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.proyecto1E.model.persona.Persona;

@Entity
@Table(name = "direcciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String calle;
    private int noExterior;
    private String colonia;
    private String municipio;
    private String estado;
    private String codigoPostal;

    //Una direccion puede tener muchas personas ( muchos a 1 ManyToOne)  No 1 a muchos
    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;
}
