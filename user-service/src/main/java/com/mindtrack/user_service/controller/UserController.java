package com.mindtrack.user_service.controller;

import com.mindtrack.user_service.dto.UserRequestDto;
import com.mindtrack.user_service.dto.UserResponseDto;
import com.mindtrack.user_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController                     //Anotação que une @Controller e @ResponseBody. Diz ao Spring que essa classe lida com requisições web e que po retorno (string) deve ser enviado diretammente como resposta do corpo http
@RequestMapping("api/v1/users")     //Define a URL base para todos os métodos deste Controller. A URL final será /api/v1/users mais a URL de cada método. No POSTMAN, em GET,adicione: http://localhost:8080/api/v1/users/hello para teste
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public  ResponseEntity<UserResponseDto> criaUsuario(@RequestBody UserRequestDto userDto) {
        UserResponseDto userResponseDto = userService.criaUsuario(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> buscaUsuarioPorId(@PathVariable Long id){

        UserResponseDto userResponseDto = userService.buscaUsuarioPorId(id);
        return ResponseEntity.ok(userResponseDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> atualizaUsuario(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto){

        Long atualizaId = 1L; // no futuro, isso virá do banco de dados
        UserResponseDto userResponseDto = new UserResponseDto(
                atualizaId,
                userRequestDto.getUsername(),
                userRequestDto.getEmail()
        );
        return ResponseEntity.ok(userResponseDto);
    }


    @GetMapping("/search")
    public String pesquisaUsuarioNome(@RequestParam String nome) {       //O Spring vai procurar por um parâmetro chamado nome depois do ? na URL.

        //futuramente, pesquisar nome do usuario no banco de dados
        return "Buscando o usuário com o mone : " + nome;
    }


    @GetMapping("/{id}/perfil")
    public String buscaUsuarioPerfil(@PathVariable Long id, @RequestParam String nomeCompleto) {
        return "O perfil do usuário de ID " + id + " é de " + nomeCompleto;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDto> deletaUsuario(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
