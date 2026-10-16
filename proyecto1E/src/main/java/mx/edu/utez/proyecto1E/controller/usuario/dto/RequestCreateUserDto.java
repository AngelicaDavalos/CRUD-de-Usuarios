package mx.edu.utez.proyecto1E.controller.usuario.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class RequestCreateUserDto {
    @NotBlank(message="El usuario es obligatrorio")
    @Size(min = 4, max = 20)
    private String username;

    @NotBlank(message = "Lacontraseña es obligatoria")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, incluir al menos una letra y un número"
    )
    private String password;
    private String descripcion;

    @NotBlank(message = "El rol es obligatorio")
    @Pattern(
            regexp = "^(ADMIN|PROFESOR|ESTUDIANTE)$",
            message = "El rol debe ser ADMIN, PROFESOR o ESTUDIANTE"
    )
    private String rol;

    @NotBlank(message = "El nombre es obligatorio")
    private  String nombres;

    private String primerApellido;
    private String segundoApellido;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no es valido")
    private String correo;

    @NotBlank(message = "El curp es obligatorio")
    private String curp;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
}
