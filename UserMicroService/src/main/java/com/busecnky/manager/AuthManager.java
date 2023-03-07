package com.busecnky.manager;

import com.busecnky.dto.request.UpdateByEmailOrUserNameRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.busecnky.constants.RestEndPoints.*;

import static com.busecnky.constants.RestEndPoints.UPDATEBYUSERNAMEOREMAIL;

@FeignClient(name = "auth-user", decode404 = true,url = "http://localhost:8090/api/v1/auth")
public interface AuthManager {

    @PutMapping(UPDATEBYUSERNAMEOREMAIL)
    public ResponseEntity<Boolean> updateByUsernameOrEmail(@RequestBody UpdateByEmailOrUserNameRequestDto dto);
}
