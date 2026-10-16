package mx.edu.utez.proyecto1E.controller.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RequestBodyDTO {
    @NotBlank (message = "El nombre es obligatorio")
    @Size (min = 3, message = "El nombrev debe tener almenos 3 caracteres")
    private String nombre;
    @Min (value = 18, message = "La edad minima es de 18")
    private int edad;
    @Email (message = "El formato del correo no es valido")
    @NotBlank(message = "El correo es obligatorio")
    private String correo;
    @NotBlank(message = "La curp es obligatoria")
    @Pattern(regexp = "^[A-Z][AEIOUX][A-Z]{2}\\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\\d|3[01])[HM](?:AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TL|TS|VZ|YN|ZS|NE)[BCDFGHJKLMNPQRSTVWXYZ]{3}[A-Z\\d]\\d$",
            message = "El formato de la CURP no es válido"
    )
    private String curp;
}
