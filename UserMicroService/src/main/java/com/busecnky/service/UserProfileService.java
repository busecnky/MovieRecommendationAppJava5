package com.busecnky.service;

import com.busecnky.dto.request.NewCreateUserRequestDto;
import com.busecnky.dto.request.UpdateRequestDto;
import com.busecnky.exception.EErrorType;
import com.busecnky.exception.UserManagerException;
import com.busecnky.manager.AuthManager;
import com.busecnky.mapper.IUserMapper;
import com.busecnky.repository.IUserRepository;
import com.busecnky.repository.entity.UserProfile;
import com.busecnky.repository.enums.EStatus;
import com.busecnky.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserRepository repository;
    private final AuthManager authManager;

    public UserProfileService(IUserRepository repository, AuthManager authManager) {
        super(repository);
        this.repository = repository;
        this.authManager = authManager;
    }

    public Boolean createUser(NewCreateUserRequestDto dto){
        try{
            UserProfile userProfile = IUserMapper.INSTANCE.toUserProfile(dto);
            save(userProfile);
            return true;
        }catch (Exception e){
            throw new UserManagerException(EErrorType.USER_NOT_CREATED);
        }
    }


    public Boolean update(UpdateRequestDto dto) {
        Optional<UserProfile> userProfile=repository.findOptionalByAuthid(dto.getAuthid());

        if (userProfile.isEmpty()){
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        }

        if (!dto.getEmail().equals(userProfile.get().getEmail())||!dto.getUsername().equals(userProfile.get().getUsername())){
            userProfile.get().setUsername(dto.getUsername());
            userProfile.get().setEmail(dto.getEmail());
            authManager.updateByUsernameOrEmail(IUserMapper.INSTANCE.toUpdateByEmailOrUserNameRequestDto(dto));
        }
        userProfile.get().setAbout(dto.getAbout());
        userProfile.get().setAddress(dto.getAddress());
        userProfile.get().setPhone(dto.getPhone());
        userProfile.get().setAvatar(dto.getAvatar());
        update(userProfile.get());
        return true;

    }

    public Boolean activateStatus(Long authid) {
        Optional<UserProfile> userProfile = repository.findOptionalByAuthid(authid);
        if (userProfile.isEmpty()){
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        }
        userProfile.get().setStatus(EStatus.ACTIVE);
        update(userProfile.get());
        return true;
    }
}
