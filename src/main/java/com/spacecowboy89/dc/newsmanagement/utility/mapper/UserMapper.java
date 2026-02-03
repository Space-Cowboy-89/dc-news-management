package com.spacecowboy89.dc.newsmanagement.utility.mapper;


import com.spacecowboy89.dc.newsmanagement.dto.UserDto;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public UserDto toUserDto(User user);
    public User toUser(UserDto userDto);
    public List<UserDto> toUserDtoList(List<User> users);

}
