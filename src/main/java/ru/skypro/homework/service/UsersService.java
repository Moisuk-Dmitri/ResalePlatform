package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.SetPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.model.User;

public interface UsersService {

    User findByEmail(String email);

    void setPassword(SetPasswordDto setPasswordDto);

    UserDto getCurrentUserInfo();

    UserDto updateUserInfo(UpdateUserDto updateUserDto);

    void updateUserImage(MultipartFile file);
}
