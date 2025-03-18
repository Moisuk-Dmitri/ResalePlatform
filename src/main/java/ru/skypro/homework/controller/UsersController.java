package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.GetUserDto;
import ru.skypro.homework.dto.user.SetPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;

import java.io.IOException;

@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Пользователи")
@RequestMapping("/users")
public class UsersController {


    @Operation(
            tags = "Пользователи",
            summary = "Обновление пароля",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SetPasswordDto.class)
                    )
            ),
            operationId = "setPassword",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "ok"),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden")

            }


    )
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody SetPasswordDto setPassword) {
        return ResponseEntity.ok(setPassword);
    }

    @Operation(
            tags = "Пользователи",
            summary = "Получение информации об авторизованном пользователе",
            operationId = "getUser",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = GetUserDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            }

    )
    @GetMapping("/me")
    public ResponseEntity<GetUserDto> getUser() {
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/me")
    @Operation(
            tags = "Пользователи",
            summary = "Обновление информации об авторизованном пользователе",
            operationId = "updateUser",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UpdateUserDto.class))
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UpdateUserDto.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized")
            }
    )
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserDto user) {
        return ResponseEntity.ok(null);
    }


    @Operation(
            tags = "Пользователи",
            summary = "Обновление аватара авторизованного пользователя",
            operationId = "updateUserImage",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema()     // TODO
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized")
            }
    )
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(@RequestParam("image") MultipartFile file) throws IOException {
        return ResponseEntity.ok(null);
    }


}