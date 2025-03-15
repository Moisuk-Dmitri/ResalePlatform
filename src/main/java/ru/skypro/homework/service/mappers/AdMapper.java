package ru.skypro.homework.service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.model.Ad;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface AdMapper {
    AdMapper INSTANCE = Mappers.getMapper( AdMapper.class );

    AdDto adToAdDto(Ad ad);
    Ad adDtoToAd(AdDto adDto);


}
