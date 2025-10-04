package com.mindtrack.user_service.mapper;

import com.mindtrack.user_service.domain.User;
import com.mindtrack.user_service.dto.UserRequestDto;
import com.mindtrack.user_service.dto.UserResponseDto;
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
