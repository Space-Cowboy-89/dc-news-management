package com.spacecowboy89.dc.newsmanagement.utility.mapper;

import com.spacecowboy89.dc.newsmanagement.dto.NewsDto;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.NewsText;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper(componentModel = "spring")
@Component
public interface NewsTextMapper {

    @Mapping(source = "image.content", target = "imageContent")
    NewsDto.NewsTextDto toNewsTextDto(NewsText newsText);

    @Mapping(source = "image.content", target = "imageContent")
    List<NewsDto.NewsTextDto> toNewsTextDto(List<NewsText> newsTextList);
}
