package ru.geekbrains.march.market.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.march.market.api.RegistrUserDto;
import ru.geekbrains.march.market.auth.converters.RegistrUserConverter;
import ru.geekbrains.march.market.auth.services.UserService;

@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
//    private final PasswordEncoder passwordEncoder;
    private final RegistrUserConverter registrUserConverter;


    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public void registrationNewUser(@RequestBody RegistrUserDto registrUserDto){
        if(userService.findByUsername(registrUserDto.getUsername()).isPresent()){
            throw new IllegalStateException("Пользователь с таким именем уже существует");
        }
        userService.saveNewUser(registrUserConverter.dtoToUserEntity(registrUserDto));
    }
}
