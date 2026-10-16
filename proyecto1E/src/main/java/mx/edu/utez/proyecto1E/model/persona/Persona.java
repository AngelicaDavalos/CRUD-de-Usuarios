package mx.edu.utez.proyecto1E.model.persona;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.proyecto1E.model.cursos.Curso;

import java.time.LocalDate;
import java.util.List;

@Entity //para que sea tabla en BD
@Table(name="personas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    @Id //llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombres;
    private String primerApellido;
    private String segundoApellido;
    private String curp;
    private  String email;
    //localdate solo guarda dia/mes/anio;
    private LocalDate fechaNacimiento;
    //Tabla interseccion
    //muchos a muchos ManyToMany crea una tercera tabla
   /* @ManyToMany
    @JoinTable (
            name="personas_cursos",
            joinColumns = @JoinColumn(name = "persona_id"),
          inverseJoinColumns = @JoinColumn(name="curso_id")
    )

    private List<Curso> curso;*/
}
