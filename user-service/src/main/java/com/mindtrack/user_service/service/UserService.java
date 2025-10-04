package com.mindtrack.user_service.service;

import com.mindtrack.user_service.dto.UserRequestDto;
import com.mindtrack.user_service.dto.UserResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    public UserResponseDto criaUsuario(UserRequestDto userDto) {
        Long novoId = 1L;

        log.info("Lógica de negócio do Serviço: Criando usuário {} ", userDto.getUsername());

         return new UserResponseDto(
                 novoId,
                 userDto.getUsername(),
                 userDto.getEmail()
         );
    }

    public UserResponseDto buscaUsuarioPorId(Long id) {

        log.info("Lógica de negócio do Serviço: Buscando usuário {} ", id );

        return new UserResponseDto(
                id,
                "joao.silva",
                "j.s@gmail.com"
        );

    }
}
