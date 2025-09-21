package com.spacecowboy89.dc.newsmanagement.utility.mapper;

import com.spacecowboy89.dc.newsmanagement.dto.CommentDto;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);


    CommentDto toCommentDto (Comment comment);

    List<CommentDto> toCommentDtoList (List<Comment> commentList);
}
