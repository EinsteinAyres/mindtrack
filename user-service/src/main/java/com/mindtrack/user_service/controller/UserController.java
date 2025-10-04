package com.mindtrack.user_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController                     //Anotação que une @Controller e @ResponseBody. Diz ao Spring que essa classe lida com requisições web e que po retorno (string) deve ser enviado diretammente como resposta do corpo http
@RequestMapping("api/v1/users")     //Define a URL base para todos os métodos deste Controller. A URL final será /api/v1/users mais a URL de cada método. No POSTMAN, em GET,adicione: http://localhost:8080/api/v1/users/hello para teste
public class UserController {

    @GetMapping("/hello")           //Mapeia o método helloWorld() para responder a requisições GET na URL /hello. A URL completa será http://localhost:8080/api/v1/users/hello.
    public String helloWorld() {
        return "Teste de acesso. User-service no ar";
    }

}
