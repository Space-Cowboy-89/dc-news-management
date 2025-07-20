package com.spacecowboy89.dc.newsmanagement.utility.mapper;

import com.spacecowboy89.dc.newsmanagement.dto.MainInfoNewsDto;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    @Mapping(source = "image.content", target = "contentImage")
    public MainInfoNewsDto toMainInfoNewsDto(News news);

    @Mapping(source = "image.content", target = "contentImage")
    public MainInfoNewsDto toMainInfoNewsDtoList(List<News> news);

}
