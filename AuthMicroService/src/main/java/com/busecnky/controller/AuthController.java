package com.busecnky.controller;

import com.busecnky.dto.request.ActivateRequestDto;
import com.busecnky.dto.request.LoginRequestDto;
import com.busecnky.dto.request.RegisterRequestDto;
import com.busecnky.dto.request.UpdateByEmailOrUserNameRequestDto;
import com.busecnky.dto.response.LoginResponseDto;
import com.busecnky.dto.response.RegisterResponseDto;
import com.busecnky.service.AuthService;
import lombok.RequiredArgsConstructor;

import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import static com.busecnky.constants.RestEndPoints.*;
@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){

        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping(ACTIVATESTATUS)
    public ResponseEntity<Boolean> activateStatus(@RequestBody ActivateRequestDto dto){
        return ResponseEntity.ok(authService.activateStatus(dto));
    }

    @PostMapping(ACTIVATESTATUS+"2")
    public ResponseEntity<Boolean> activateStatus2(@RequestBody ActivateRequestDto dto){
        return ResponseEntity.ok(authService.activateStatus2(dto));
    }


    @PostMapping(LOGIN)
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(authService.login(dto));
    }

    @PutMapping(UPDATEBYUSERNAMEOREMAIL)
    public ResponseEntity<Boolean> updateByUsernameOrEmail(@RequestBody UpdateByEmailOrUserNameRequestDto dto){

        return ResponseEntity.ok(authService.updateByUsernaemOrEmail(dto));

    }
}
