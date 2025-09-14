package com.spacecowboy89.dc.newsmanagement.utility.mapper;

import com.spacecowboy89.dc.newsmanagement.dto.NewsInfoDto;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface NewsMapper {

    @Mapping(source = "image.content", target = "contentImage")
    public NewsInfoDto toNewsInfoDto(News news);

    @Mapping(source = "image.content", target = "contentImage")
    public List<NewsInfoDto> toNewsInfoDtoList(List<News> news);

}
