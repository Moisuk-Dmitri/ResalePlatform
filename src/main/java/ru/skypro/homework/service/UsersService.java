package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.GetUserDto;
import ru.skypro.homework.dto.user.SetPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.model.User;

public interface UsersService {

    User findByUsername(String username);

    void setPassword(SetPasswordDto setPasswordDto);

    GetUserDto getCurrentUserInfo();

    UserDto updateUserInfo(UpdateUserDto updateUserDto);

    void updateUserImage(MultipartFile file);
}
