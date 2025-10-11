package br.com.ayrs.mindtrack.user.mapper;

import br.com.ayrs.mindtrack.user.domain.User;
import br.com.ayrs.mindtrack.user.dto.UserRequestDto;
import br.com.ayrs.mindtrack.user.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequestDto userDto){
        if (userDto == null){
            return null;
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserResponseDto toDto(User user){

        if (user ==null){
            return null;
        }
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }

}
