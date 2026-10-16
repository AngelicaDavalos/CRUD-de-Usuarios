package mx.edu.utez.proyecto1E.model.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.proyecto1E.model.persona.Persona;

@Entity
@Table(name="usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//los atributos se onvierten en columnas
    private Long id;

    @Column(name="username1", nullable = false, unique = true)
    private String username;

    private String password;
    private boolean is_enable; //ver si user es activo

    @Column(columnDefinition = "Text")
    private String descripcion;

    @Transient //Indicamos que el atributo No debe ser una columna en la tabla
    private String pruebaColumna;

    @Enumerated(EnumType.STRING)
    private Roles rol;

    //llave foranea con persona 1 a 1
    @OneToOne
    @JoinColumn(name = "persona_id") //indicar campo de fk y config
    private Persona persona; //declarar entidad con la que se relaciona

}
