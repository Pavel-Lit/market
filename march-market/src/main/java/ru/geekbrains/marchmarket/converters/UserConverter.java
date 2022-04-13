package ru.geekbrains.marchmarket.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.marchmarket.dtos.UserDto;
import ru.geekbrains.marchmarket.entities.User;

@Component
public class UserConverter {

    public UserDto entityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getUsername());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
