package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.GetUserDto;
import ru.skypro.homework.dto.user.SetPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

import static ru.skypro.homework.security.Permissions.USER;

/**
 * This controller provides endpoints for user's operations.
 */
@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Пользователи")
@RequestMapping("/users")

public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    /**
     * The endpoint for password updating
     *
     * @param setPassword User's DTO for the password updating
     * @return ResponseEntity containing the password updating status
     * HTTP 200 (OK): password updating successful
     * HTTP 401 (Unauthorized): if authentication fails.
     * HTTP 403 (Forbidden): password updating declined
     */
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
    @PreAuthorize(USER)
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody SetPasswordDto setPassword) {
        userService.setPassword(setPassword);
        return ResponseEntity.ok().build();
    }

    /**
     * Getting information about the authorized user
     *
     * @return ResponseEntity containing the created GetUserDto.
     * HTTP 200 (OK): with user's info
     * HTTP 400 (Unauthorized): if user is not authorized
     */
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
    @PreAuthorize(USER)
    @GetMapping("/me")
    public ResponseEntity<GetUserDto> getUser() {
        return ResponseEntity.ok(userService.getCurrentUserInfo());
    }

    /**
     * Updating information about the authorized user
     *
     * @param updateUserDto user's DTO for updating authorized user's info
     * @return ResponseEntity containing the updating status
     * HTTP 200 (OK): updating successful
     * HTTP 400 (Unauthorized): if user is not authorized
     */
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
    @PreAuthorize(USER)
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(userService.updateUserInfo(updateUserDto));
    }

    /**
     * Updating user's avatar
     *
     * @param file new avatar
     *             HTTP 200 (OK): updating successful
     *             HTTP 400 (Unauthorized): if user is not authorized
     * @return ResponseEntity containing the updating status
     */
    @Operation(
            tags = "Пользователи",
            summary = "Обновление аватара авторизованного пользователя",
            operationId = "updateUserImage",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE
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
    @PreAuthorize(USER)
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(@RequestParam("image") MultipartFile file) throws IOException {
        userService.updateUserImage(file);
        return ResponseEntity.ok().build();
    }
}