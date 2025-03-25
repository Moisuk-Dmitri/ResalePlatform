package ru.skypro.homework.service.mappers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.user.*;
import ru.skypro.homework.model.User;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface UserMapper {

    LoginDto userToLoginDto(User user);

    RegisterDto userToRegisterDto(User user);

    @Mapping(source = "username", target = "email")
    User RegisterDtoToUser(RegisterDto registerDto);

    @Mapping(source = "password",target = "currentPassword")
    SetPasswordDto userToSetPasswordDto(User user);

    UpdateUserDto userToUpdateUserDto(User user);

    UserDto userToUserDto(User user);




}
