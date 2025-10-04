package com.mindtrack.user_service.controller;

import com.mindtrack.user_service.dto.UserRequestDto;
import com.mindtrack.user_service.dto.UserResponseDto;
import com.mindtrack.user_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    // Injeção de Dependência via Construtor
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public  ResponseEntity<UserResponseDto> criaUsuario(@RequestBody UserRequestDto userDto) {
        // Delega a criação para o Serviço e usa o status 201 CREATED
        UserResponseDto userResponseDto = userService.criaUsuario(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> buscaUsuarioPorId(@PathVariable Long id){
        // Delega a busca por ID para o Serviço e usa o status 200 OK
        UserResponseDto userResponseDto = userService.buscaUsuarioPorId(id);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/email")
    public ResponseEntity<UserResponseDto> buscaUsuarioPorEmail(@RequestParam String email){
        // Delega a busca por E-mail para o Serviço e usa o status 200 OK
        UserResponseDto userResponseDto = userService.buscaUsuarioPorEmail(email);
        return ResponseEntity.ok(userResponseDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> atualizaUsuario(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto){
        // Delega a atualização para o Serviço. A lógica de atualização foi movida para lá.
        UserResponseDto userResponseDto = userService.atualizaUsuario(id, userRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaUsuario(@PathVariable Long id) {
        // Delega a exclusão para o Serviço
        userService.deletaUsuario(id);

        // Retorna o status 204 NO CONTENT e corpo vazio (Void)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
