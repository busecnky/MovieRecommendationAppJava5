package com.busecnky.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateByEmailOrUserNameRequestDto {

    private Long id;
    private String username;
    private String email;

}
