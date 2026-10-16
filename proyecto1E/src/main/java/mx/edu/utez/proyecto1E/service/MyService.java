package mx.edu.utez.proyecto1E.service;

import mx.edu.utez.proyecto1E.controller.dtos.RequestCalculadoraDTO;
import mx.edu.utez.proyecto1E.controller.dtos.ResponseCalculadoraDTO;
import mx.edu.utez.proyecto1E.exception.customExceptions.CustomBadException;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    public ResponseCalculadoraDTO calculadora (RequestCalculadoraDTO payload){
        ResponseCalculadoraDTO respuesta = new ResponseCalculadoraDTO();

        //Validar para que no se rompa el programa
        if(!payload.getOperacion().equals("SUMA")
                && !payload.getOperacion().equals("RESTA")
                && !payload.getOperacion().equals("MULTIPLICACION")

        ) {
           throw new CustomBadException("La operación solicitada no es valida" + "valida SUMA, RESTA Y MULTIPLICACIÓN");

        }
            switch (payload.getOperacion()) {
                case "SUMA":
                    respuesta.setRespuesta(
                            payload.getNum1() + payload.getNum2()
                    );
                    break;
                case "RESTA":
                    respuesta.setRespuesta(
                            payload.getNum1() - payload.getNum2()
                    );
                    break;
                case "MULTIPLICACION":
                    respuesta.setRespuesta(
                            payload.getNum1() * payload.getNum2()
                    );
                    break;
            }

            respuesta.setOperacionRealizada(payload.getOperacion());
            return respuesta;

    }
}
