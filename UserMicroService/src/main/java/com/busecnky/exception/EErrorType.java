package com.busecnky.exception;

import com.busecnky.dto.request.UpdateByEmailOrUserNameRequestDto;
import com.busecnky.dto.request.UpdateRequestDto;
import com.busecnky.repository.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapping;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EErrorType {


    INTERNAL_ERROR(5200,"Sunucu Hatasi",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4200,"Parametre Hatasi",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4210,"Boyle Bir kullanici bulunamadi",HttpStatus.BAD_REQUEST),
    USER_NOT_CREATED(4211,"Kullanici olusturulamadi",HttpStatus.BAD_REQUEST),
    USERNAME_DUPLICATE(4212,"Böyle bir kullanıcı adı var",HttpStatus.BAD_REQUEST);


    private   int code;
    private   String message;
    HttpStatus httpStatus;


}
