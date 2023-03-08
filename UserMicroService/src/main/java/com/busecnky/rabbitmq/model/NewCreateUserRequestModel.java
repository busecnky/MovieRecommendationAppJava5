package com.busecnky.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewCreateUserRequestModel implements Serializable {

    private Long authid;
    private String username;
    private String email;
}
