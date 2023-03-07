package com.busecnky.service;

import com.busecnky.dto.request.ActivateRequestDto;
import com.busecnky.dto.request.LoginRequestDto;
import com.busecnky.dto.request.RegisterRequestDto;
import com.busecnky.dto.request.UpdateByEmailOrUserNameRequestDto;
import com.busecnky.dto.response.RegisterResponseDto;
import com.busecnky.exception.AuthManagerException;
import com.busecnky.exception.EErrorType;
import com.busecnky.manager.IUserManager;
import com.busecnky.mapper.IAuthMapper;
import com.busecnky.repository.IAuthRepository;
import com.busecnky.repository.entity.Auth;
import com.busecnky.repository.enums.EStatus;
import com.busecnky.utility.CodeGenerator;
import com.busecnky.utility.JwtTokenManager;
import com.busecnky.utility.ServiceManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository authRepository;
    private final IUserManager userManager;
    private final JwtTokenManager jwtTokenManager;
    public AuthService(IAuthRepository authRepository, IUserManager userManager, JwtTokenManager jwtTokenManager) {
        super(authRepository);
        this.authRepository = authRepository;
        this.userManager = userManager;
        this.jwtTokenManager = jwtTokenManager;
    }

    @Transactional
    public RegisterResponseDto register(RegisterRequestDto dto) {
        try {
            Auth auth= IAuthMapper.INSTANCE.toAuth(dto);
            auth.setActivationCode(CodeGenerator.generateCode());
            save(auth);
            userManager.createUser(IAuthMapper.INSTANCE.toNewCreateUserRequestDto(auth));
            return IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
        }catch (Exception e){

            throw new AuthManagerException(EErrorType.USER_NOT_CREATED);
        }
    }

    public Boolean activateStatus(ActivateRequestDto dto) {
        Optional<Auth> auth=findById(dto.getId());
        if (auth.isEmpty()){
            throw  new AuthManagerException(EErrorType.USER_NOT_FOUND);
        }
        if (dto.getActivationCode().equals(auth.get().getActivationCode())){
            auth.get().setStatus(EStatus.ACTIVE);
            update(auth.get());
            userManager.activateStatus(dto.getId());
            return  true;
        }else{
            throw new AuthManagerException(EErrorType.ACTIVATE_CODE_ERROR);
        }
    }

    public Boolean activateStatus2(ActivateRequestDto dto) {
        Optional<Auth> auth=findById(dto.getId());
        if (auth.isEmpty()){
            throw new AuthManagerException(EErrorType.USER_NOT_FOUND);
        }
        if (dto.getActivationCode().equals(auth.get().getActivationCode())){
            auth.get().setStatus(EStatus.ACTIVE);
            save(auth.get());
            userManager.activateStatus2(dto.getId());
            return  true;
        }else{
            throw new AuthManagerException(EErrorType.ACTIVATE_CODE_ERROR);
        }
    }

    public String login(LoginRequestDto dto) {
        Optional<Auth> auth=authRepository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (auth.isEmpty()){
            throw  new AuthManagerException(EErrorType.LOGIN_ERROR);
        }
        if(!auth.get().getStatus().equals(EStatus.ACTIVE)){
            throw  new AuthManagerException(EErrorType.LOGIN_STATUS_ERROR);
        }

        Optional<String >token = jwtTokenManager.createToken(auth.get().getId(),auth.get().getRole());
        if (token.isEmpty()){
            throw new AuthManagerException(EErrorType.TOKEN_NOT_CREATED);
        }
        return token.get();
    }

    public Boolean updateByUsernaemOrEmail(UpdateByEmailOrUserNameRequestDto dto) {
        Optional<Auth> auth=authRepository.findById(dto.getId());
        if (auth.isEmpty()){
            throw new AuthManagerException(EErrorType.USER_NOT_FOUND);
        }
        auth.get().setEmail(dto.getEmail());
        auth.get().setUsername(dto.getUsername());
        update(auth.get());
        return true;
    }
}
