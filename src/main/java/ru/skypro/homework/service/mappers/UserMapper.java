package ru.skypro.homework.service.mappers;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.user.*;
import ru.skypro.homework.model.UserData;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    GetUserDto userDataToGetUserDto(UserData user);
    LoginDto userDataToLoginDto(UserData user);
    RegisterDto userDataToRegisterDto(UserData user);
    //TODO SetPasswordDto?
    UpdateUserDto userDataToUpdateUserDto(UserData user);
    UserDto userDataToUserDto(UserData user);



}
