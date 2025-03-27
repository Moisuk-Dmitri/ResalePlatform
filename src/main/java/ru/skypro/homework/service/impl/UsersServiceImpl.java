package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.GetUserDto;
import ru.skypro.homework.dto.user.SetPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UsersService;
import ru.skypro.homework.service.mappers.UserMapper;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserMapper mapper;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public void setPassword(SetPasswordDto setPasswordDto) {
        User authorizedUser = getAuthorizedUser();
        log.info("Change password for user {}", authorizedUser.getEmail());

        if (!encoder.matches(setPasswordDto.getCurrentPassword(), authorizedUser.getPassword())) {
            log.error("Current password is incorrect for user: {}", authorizedUser.getEmail());
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Current password is incorrect for user");
        }
        authorizedUser.setPassword(encoder.encode(setPasswordDto.getNewPassword()));
        userRepository.save(authorizedUser);
        if (encoder.matches(setPasswordDto.getNewPassword(), authorizedUser.getPassword())) {
            log.info("Password updated successfully for user: {}", authorizedUser.getEmail());
        }

    }

    @Override
    public GetUserDto getAuthorizedUserInfo() {
        log.info("Request authorized user's info {}", getAuthorizedUser().getEmail());
        log.info(mapper.userToGetUserDto(getAuthorizedUser()).toString());
        return mapper.userToGetUserDto(getAuthorizedUser());

    }

    @Override
    public UserDto updateUserInfo(UpdateUserDto updateUserDto) {
        log.info("Request updating authorized user's info {}", getAuthorizedUser().getEmail());
        User authorizedUser = getAuthorizedUser();
        mapper.updateUserFromUpdateUserDto(updateUserDto, authorizedUser);
        User updatedUserInfo = userRepository.save(authorizedUser);
        if (updatedUserInfo.getFirstName().equals(updateUserDto.getFirstName())
                && updatedUserInfo.getLastName().equals(updateUserDto.getLastName())
                && updatedUserInfo.getPhone().equals(updateUserDto.getPhone())) {
            log.info("User updated successfully for user: {}", authorizedUser.getEmail());
        }
        return mapper.userToUserDto(authorizedUser);

    }

    @Override
    public void updateUserImage(MultipartFile file) {
        log.info("Request updating authorized user's image {}", getAuthorizedUser().getEmail());
        User authorizedUser = getAuthorizedUser();
        authorizedUser.setImage(file.toString());
    }

    private User getAuthorizedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            log.error("User not authorized");
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }
}



