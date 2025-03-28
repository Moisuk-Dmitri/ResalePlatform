package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.Ads;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ad.ExtendedAd;

import java.io.IOException;

public interface AdService {
    public Ads getAllAds();
    public ExtendedAd getAd(Integer adId);
    public Ads getAdsMe(Authentication authentication);
    public AdDto addAd(CreateOrUpdateAdDto properties, MultipartFile image, String userName) throws IOException;
    public AdDto updateAd(Integer adId, CreateOrUpdateAdDto properties);
    public String updateImage(Integer adId, MultipartFile image) throws IOException;
    public void delete(Integer adId) throws IOException;
}
