package ru.skypro.homework.service.mappers;

import org.mapstruct.Mapper;
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








}
