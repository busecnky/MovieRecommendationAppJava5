package com.busecnky.controller;

import com.busecnky.dto.request.NewCreateUserRequestDto;
import com.busecnky.dto.request.UpdateRequestDto;
import com.busecnky.repository.entity.UserProfile;
import com.busecnky.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.busecnky.constants.RestEndPoints.*;
@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> create(@RequestBody NewCreateUserRequestDto dto){
        return ResponseEntity.ok(userProfileService.createUser(dto));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> update(@RequestBody @Valid UpdateRequestDto dto){
        return ResponseEntity.ok(userProfileService.update(dto));
    }

    @PostMapping(ACTIVATESTATUS+"/{authid}")
    public ResponseEntity<Boolean> activateStatus(@PathVariable Long authid){
        return ResponseEntity.ok(userProfileService.activateStatus(authid));

        //....activateStatus?=authid=1   yazıyorduk bu RequestParam
        //....activateStatus/1   yazabiliyoruz PathVariable ile

    }

    @PostMapping(ACTIVATESTATUS)
    public ResponseEntity<Boolean> activateStatus2(@RequestParam Long authid){
        return ResponseEntity.ok(userProfileService.activateStatus(authid));

        //....activateStatus?=authid=1   yazıyorduk bu RequestParam
        //....activateStatus/1   yazabiliyoruz PathVariable ile

    }

    @GetMapping(FINDALL)
    public  ResponseEntity<List<UserProfile>> findAll(){
        return  ResponseEntity.ok(userProfileService.findAll());
    }
}
