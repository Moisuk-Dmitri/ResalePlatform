package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.Ads;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ad.ExtendedAd;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.service.mappers.AdMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import javax.transaction.Transactional;
import java.io.IOException;

@Slf4j
@Transactional
@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdMapper adMapper;
    private final ImageService imageService;

    public AdServiceImpl(AdRepository adRepository, UserRepository userRepository, AdMapper adMapper, ImageService imageService) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.adMapper = adMapper;
        this.imageService = imageService;
    }

    /**
     * Find all
     *
     * @return Ads
     */
    @Override
    public Ads getAllAds() {
        return adMapper.adsToAdsDto(adRepository.findAll());
    }

    /**
     * Get ad by id from DB.
     *
     * @param adId ad ID.
     * @return ExtendedAd.
     */
    @Override
    public ExtendedAd getAd(Integer adId) {
        return adMapper.adToExtendedDtoOut(adRepository.findById(adId).orElseThrow(() -> new AdNotFoundException(adId)));
    }

    /**
     * Get ads from an authorized user.
     *
     * @param authentication auth object.
     * @return Ads.
     */
    @Override
    public Ads getAdsMe(Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UserNotFoundException(authentication.getName()));
        return adMapper.adsToAdsDto(adRepository.findAllByUserId(user.getId()).get());
    }

    /**
     * Add
     *
     * @param properties CreateOrUpdateAdDto.
     * @param image file of image.
     * @param userName username.
     * @return AdDTO Dto-объект объявления.
     */
    @Override
    public AdDto addAd(CreateOrUpdateAdDto properties, MultipartFile image, String userName) throws IOException {
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new UserNotFoundException(userName));
        Image image1 = imageService.uploadAdImage(properties, image);
        Ad ad = adMapper.createAd(properties, user, image1);
        adRepository.save(ad);
        log.info("Ad {} {} saved", ad.getPk(), ad.getTitle());
        return adMapper.adToAdDto(ad);
    }

    /**
     * Update
     *
     * @param adId Id.
     * @param properties CreateOrUpdateAdDto.
     * @return AdDTO Dto-объект объявления.
     */
    @Override
    public AdDto updateAd(Integer adId, CreateOrUpdateAdDto properties) {
        if (!adRepository.existsById(adId)) {
            throw new AdNotFoundException(adId);
        }
        Ad ad = adRepository.findById(adId).orElseThrow(()-> new AdNotFoundException(adId));
        ad.setTitle(properties.getTitle());
        ad.setPrice(properties.getPrice());
        ad.setDescription(properties.getDescription());
        adRepository.save(ad);
        return adMapper.adToAdDto(ad);
    }

    /**
     * Update image
     *
     * @param adId ad ID.
     * @param image file of image.
     * @return String path to file.
     */
    @Override
    public String updateImage(Integer adId, MultipartFile image) throws IOException {
        Ad ad = adRepository.findById(adId).orElseThrow(()-> new AdNotFoundException(adId));
        int imageId = imageService.findImageIdByImagePath(ad.getImage());

        if (ad.getImage() != null) {
            imageService.deleteImage(imageId);
        }

        return imageService.updateAdImage(imageId, image);
    }

    /**
     * Delete
     *
     * @param adId ad ID.
     * @throws AdNotFoundException if it is not found.
     */
    @Override
    public void delete(Integer adId) throws IOException {
        Ad ad = adRepository.findById(adId).orElseThrow(()-> new AdNotFoundException(adId));
        int imageId = imageService.findImageIdByImagePath(ad.getImage());

        if (ad.getImage() != null) {
            imageService.deleteImage(imageId);
        }
    }

    /**
     * Get
     *
     * @param adId Ad ID.
     * @throws AdNotFoundException if it is not found.
     * @return String.
     */
    public Ad getAdByUsername(Integer adId) {
        return adRepository.findById(adId).orElseThrow(() -> new AdNotFoundException(adId)); // need username
    }
}
