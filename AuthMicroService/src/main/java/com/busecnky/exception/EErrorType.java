package com.busecnky.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EErrorType {


    INTERNAL_ERROR(5100,"Sunucu Hatasi",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4100,"Parametre Hatasi",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4110,"Boyle Bir kullanici bulunamadi",HttpStatus.BAD_REQUEST),
    USER_NOT_CREATED(4111,"Kullanici olusturulamadi",HttpStatus.BAD_REQUEST),
    USERNAME_DUPLICATE(4112,"Böyle bir kullanıcı adı var",HttpStatus.BAD_REQUEST),
    ACTIVATE_CODE_ERROR(4113,"Aktivasyon kod hatası",HttpStatus.BAD_REQUEST),
    LOGIN_ERROR(4114,"Kullanici adi veya sifre hatali",HttpStatus.BAD_REQUEST),
    LOGIN_STATUS_ERROR(4114,"Yetkisiz kullanici girisi",HttpStatus.BAD_REQUEST);

    private   int code;
    private   String message;
    HttpStatus httpStatus;

}
