package com.spacecowboy89.dc.newsmanagement.utility.mapper;

import com.spacecowboy89.dc.newsmanagement.dto.NewsDto;
import com.spacecowboy89.dc.newsmanagement.dto.NewsInfoDto;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {
                EmployeeMapper.class,
                CommentMapper.class,
                NewsTextMapper.class})
@Component
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);


    @Mapping(source = "image.content", target = "contentImage")
    public NewsInfoDto toNewsInfoDto(News news);

    @Mapping(source = "image.content", target = "contentImage")
    public List<NewsInfoDto> toNewsInfoDtoList(List<News> news);


    @Mapping(source = "image.content", target = "contentImage")
    @Mapping(source = "category.name", target = "categoryName")
    public NewsDto toNewsDto(News news);

    @Mapping(source = "image.content", target = "contentImage")
    @Mapping(source = "category.name", target = "categoryName")
    public List<NewsDto> toNewsDtoList(List<News> news);
}
