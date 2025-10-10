package com.mindtrack.user_service.service;

import com.mindtrack.user_service.domain.User;
import com.mindtrack.user_service.dto.UserRequestDto;
import com.mindtrack.user_service.dto.UserResponseDto;
import com.mindtrack.user_service.mapper.UserMapper;
import com.mindtrack.user_service.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // Dependências injetadas
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // Injeção via Construtor
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDto criaUsuario(UserRequestDto userDto) {
        // Usa o Mapper para converter DTO para Entidade
        User user = userMapper.toEntity(userDto);

        // Salva no banco de dados (Repository)
        User userSalvo = userRepository.save(user);

        // Retorna o DTO de Resposta (Mapeado da Entidade salva)
        return userMapper.toDto(userSalvo);
    }

    public Page<UserResponseDto> buscaTodosUsuarios(Pageable pageable) {
        // Busca a Entidade e lança exceção se não encontrar
        Page<User> userPage = userRepository.findAll(pageable);
        // Mapeia Entidade para DTO
        return userPage.map(userMapper::toDto);
    }

    public UserResponseDto buscaUsuarioPorId(Long id) {
        // Busca a Entidade e lança exceção se não encontrar
        User userEncontrado = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + id + " não encontrado!"));

        // Mapeia Entidade para DTO
        return userMapper.toDto(userEncontrado);
    }

    public UserResponseDto buscaUsuarioPorEmail(String email) {
        // Usa a consulta derivada findByEmail do Repositório
        User userEncontrado = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário com email " + email + " não encontrado!"));

        // Mapeia Entidade para DTO
        return userMapper.toDto(userEncontrado);
    }

    public UserResponseDto atualizaUsuario(Long id, UserRequestDto userDto) {
        // 1. Busca o usuário existente
        User userExistente = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + id + " não pode ser atualizado: não encontrado!"));

        // 2. Mapeia/atualiza os campos do DTO para a Entidade existente
        // Mantém o ID original do usuário
        userExistente.setUsername(userDto.getUsername());
        userExistente.setEmail(userDto.getEmail());
        userExistente.setPassword(userDto.getPassword()); // Futuramente, faremos hash antes de salvar!

        // 3. Salva a Entidade atualizada
        User userAtualizado = userRepository.save(userExistente);

        // 4. Mapeia e retorna o DTO de Resposta
        return userMapper.toDto(userAtualizado);
    }

    /**
     * Deleta um usuário pelo ID.
     * Inclui a lógica de checagem de existência antes de deletar.
     */
    public void deletaUsuario(Long id) {
        // 1. Verifica se o usuário existe. Se não existir, lança exceção para o Controller
        // retornar uma resposta de erro (Ex: 404 Not Found, futuramente)
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuário com ID " + id + " não pode ser deletado: não encontrado!");
        }

        // 2. Deleta o usuário.
        userRepository.deleteById(id);
    }
}
