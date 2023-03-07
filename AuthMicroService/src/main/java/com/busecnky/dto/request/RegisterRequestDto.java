package com.busecnky.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {
    @NotBlank
    @Size(min = 3, max = 20, message = "Kullanıcı adı en az 3 karakter en fazla 20 karakter olabilir.")
    private String username;
    @NotBlank
    @Size(min = 8, max = 32, message = "Kullanıcı adı en az 8 karakter en fazla 32 karakter olabilir.")
    private String password;
    @NotBlank
    @Email()
    private String email;
}
