package mx.edu.utez.proyecto1E.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RequestCalculadoraDTO {
    private int num1;
    private int num2;

    @NotBlank (message = "La operacion es obligatoria")
    private String operacion;



}
