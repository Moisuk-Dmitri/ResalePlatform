package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.Ads;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ad.ExtendedAd;

import java.io.IOException;

/**
 * This controller provides endpoints for advertisement operations.
 */
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("ads")
@Slf4j
@Tag(
        name = "Объявления",
        description = "Интерфейс для управления объявлениями о продаже"
)
@ApiResponses(value = {
                @ApiResponse(
                        responseCode = "401",
                        description = "UNAUTHORIZED: не авторизован"),
                @ApiResponse(
                        responseCode = "500",
                        description = "INTERNAL_SERVER_ERROR: ошибка сервера при обработке запроса"
                )
        })
public class AdsController {

    /**
     * Retrieves all advertisements.
     *
     * @return ResponseEntity containing a list of Ads objects.
     *
     * <pre>{@code
     *
     * HTTP 200 (OK) with the list of ads
     *
     * }</pre>
     *
     *
     */
    @Operation(summary = "Получение всех объявлений")
    @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = Ads.class))
            )
    )
    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Creates a new advertisement.
     *
     * @param properties The properties of the advertisement to create.
     * @param image      The image file associated with the advertisement.
     * @return ResponseEntity containing the created AdDto.
     *
     * <pre>{@code
     *
     * HTTP 201 (Created): with the created ad details.
     *
     * }</pre>
     *
     * @throws IOException If an I/O error occurs during image processing.
     */
    @Operation(summary = "Добавление объявления")
    @ApiResponse(responseCode = "201",
            description = "Created",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AdDto.class)
            )
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createAd(@RequestPart CreateOrUpdateAdDto properties, @RequestParam MultipartFile image) throws IOException {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Retrieves information about a specific advertisement.
     *
     * @param id The ID of the advertisement.
     * @return ResponseEntity containing the ExtendedAd object.
     *
     * <pre>{@code
     *
     * HTTP 200 (OK): with the ad details.
     * HTTP 401 (Unauthorized): if authentication fails.
     * HTTP 404 (Not Found): if the ad is not found.
     *
     * }</pre>
     */
    @Operation(summary = "Получение информации об объявлении")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExtendedAd.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getAdInfo(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Deletes an advertisement.
     *
     * @param id The ID of the advertisement to delete.
     * @return ResponseEntity indicating the success of the deletion.
     *
     * <pre>{@code
     *
     * HTTP 204 (No Content): on successful deletion.
     * HTTP 401 (Unauthorized): if authentication fails.
     * HTTP 403 (Forbidden): if the user does not have permission.
     * HTTP 404 (Not Found): if the ad is not found.
     *
     * }</pre>
     */
    @Operation(summary = "Удаление объявления")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No content"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAd(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Updates information about an advertisement.
     *
     * @param id The ID of the advertisement to update.
     * @param ad The updated advertisement properties.
     * @return ResponseEntity containing the updated AdDto.
     *
     * <pre>{@code
     *
     * HTTP 200 (OK): with the updated ad details.
     * HTTP 401 (Unauthorized): if authentication fails.
     * HTTP 403 (Forbidden): if the user does not have permission.
     * HTTP 404 (Not Found): if the ad is not found.
     *
     * }</pre>
     */
    @Operation(summary = "Обновление информации об объявлении")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found"
            )
    })
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAd(@PathVariable Integer id, @RequestBody CreateOrUpdateAdDto ad) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Retrieves advertisements belonging to the authenticated user.
     *
     * @return ResponseEntity containing a list of Ads objects.
     *
     * <pre>{@code
     *
     * HTTP 200 (OK): with the user's ads.
     * HTTP 401 (Unauthorized): if authentication fails.
     *
     * }</pre>
     */
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Ads.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            )
    })
    @GetMapping("/me")
    public ResponseEntity<?> getUsersAds() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Updates the image of an advertisement.
     *
     * @param id    The ID of the advertisement.
     * @param image The new image file.
     * @return ResponseEntity containing the URL of the updated image.
     *
     * <pre>{@code
     *
     * HTTP 200 (OK): with the image URL.
     * HTTP 401 (Unauthorized): if authentication fails.
     * HTTP 403 (Forbidden): if the user does not have permission.
     * HTTP 404 (Not Found): if the ad is not found.
     *
     * }</pre>
     *
     */
    @Operation(summary = "Обновление картинки объявления")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found"
            )
    })
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateAdImage(
            @PathVariable("id") Integer id,
            @RequestParam("image") MultipartFile image
    ) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
