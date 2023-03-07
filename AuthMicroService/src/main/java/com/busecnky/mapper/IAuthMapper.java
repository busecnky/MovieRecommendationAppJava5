package com.busecnky.mapper;

import com.busecnky.dto.request.NewCreateUserRequestDto;
import com.busecnky.dto.request.RegisterRequestDto;
import com.busecnky.dto.request.UpdateByEmailOrUserNameRequestDto;
import com.busecnky.dto.response.LoginResponseDto;
import com.busecnky.dto.response.RegisterResponseDto;
import com.busecnky.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {

    IAuthMapper INSTANCE= Mappers.getMapper(IAuthMapper.class);

    Auth  toAuth(final RegisterRequestDto dto);

    RegisterResponseDto toRegisterResponseDto(final  Auth auth);

    LoginResponseDto toLoginResponseDto(final Auth auth);

    @Mapping(source = "id",target = "authid")
    NewCreateUserRequestDto toNewCreateUserRequestDto(final Auth auth);

    Auth  toAuth(final UpdateByEmailOrUserNameRequestDto dto);

}
