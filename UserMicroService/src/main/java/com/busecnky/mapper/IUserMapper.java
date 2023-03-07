package com.busecnky.mapper;

import com.busecnky.dto.request.NewCreateUserRequestDto;
import com.busecnky.dto.request.UpdateByEmailOrUserNameRequestDto;
import com.busecnky.dto.request.UpdateRequestDto;
import com.busecnky.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    UserProfile toUserProfile(final NewCreateUserRequestDto dto);

    @Mapping(source = "authid", target = "id")
    UpdateByEmailOrUserNameRequestDto toUpdateByEmailOrUserNameRequestDto(final UpdateRequestDto dto);

    UserProfile toUserProfile(final UpdateRequestDto dto);
}
