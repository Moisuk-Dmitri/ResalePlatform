package ru.skypro.homework.service.mappers;

/*import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.ad.AdDto;

import ru.skypro.homework.dto.ad.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ad.ExtendedAd;
import ru.skypro.homework.model.Ad;

@Mapper
public interface AdMapper {

@Mapping(target = "author",expression ="java(ad.getAuthor().getId())" )
AdDto adToAdDto(Ad ad);

@Mapping(target = "authorFirstName",expression ="java(ad.getAuthor().getFirstName())" )
@Mapping(target = "authorLastName",expression ="java(ad.getAuthor().getLastName())" )
@Mapping(target = "email",expression ="java(ad.getAuthor().getEmail())" )
@Mapping(target = "phone",expression ="java(ad.getAuthor().getPhone())" )
ExtendedAd adToExtendedAd(Ad ad);

CreateOrUpdateAdDto adToCreateOrUpdateAd(Ad ad);
}*/

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.Ads;
import ru.skypro.homework.dto.ad.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ad.ExtendedAd;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AdMapper {

    /**
     * Transform "CreateOrUpdateAd" to "Ad".
     *
     * @param createOrUpdateAd CreateOrUpdateAdDto.
     * @param user User.
     * @param image Image.
     * @return Ad.
     */
    public Ad createAd(CreateOrUpdateAdDto createOrUpdateAd, User user, Image image) {
        if (createOrUpdateAd == null) {
            throw new IllegalArgumentException("createOrUpdateAd is null");
        }
        Ad object = new Ad();

        object.setTitle(createOrUpdateAd.getTitle());
        object.setPrice(createOrUpdateAd.getPrice());
        object.setDescription(createOrUpdateAd.getDescription());
        object.setAuthor(user);
        //object.setImage(newAd.getImage());

        return object;
    }

    /**
     * Transform "Ad" to Dto "AdDto".
     *
     * @param ad Ad.
     * @return Dto AdDTO.
     */
    public AdDto adToAdDto(Ad ad) {
        if (ad == null) {
            throw new IllegalArgumentException("ad is null");
        }
        AdDto object = new AdDto();

        object.setPk(ad.getPk());
        object.setTitle(ad.getTitle());
        object.setPrice(ad.getPrice());
        object.setImage("/images/" + ad.getImage());
        object.setAuthor(ad.getAuthor().getId());
        return object;
    }

    /**
     * Transform "Ad" to Dto ExtendedAd.
     *
     * @param ad Ad.
     * @return Dto ExtendedAd.
     */
    public ExtendedAd adToExtendedDtoOut(Ad ad) {
        if (ad == null) {
            throw new IllegalArgumentException("ad is null");
        }
        ExtendedAd object = new ExtendedAd();

        object.setPk(ad.getPk());
        object.setTitle(ad.getTitle());
        object.setPrice(ad.getPrice());
        object.setDescription(ad.getDescription());
        object.setImage("/images/" + ad.getImage());
        object.setAuthorFirstName(ad.getAuthor().getFirstName());
        object.setAuthorLastName(ad.getAuthor().getLastName());
        object.setEmail(ad.getAuthor().getEmail());
        object.setPhone(ad.getAuthor().getPhone());

        return object;
    }

    /**
     * Transform list "Ad" to Dto Ads.
     *
     * @param ads List<Ad>.
     * @return Dto ExtendedAd.
     */
    public Ads adsToAdsDto(List<Ad> ads) {
        Ads object = new Ads();
        object.setCount(ads.size());
        object.setResults(ads
                .stream()
                .map(this::adToAdDto)
                .collect(Collectors.toList()));
        return object;
    }
}