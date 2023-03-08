package com.busecnky.rabbitmq.consumer;

import com.busecnky.mapper.IUserMapper;
import com.busecnky.rabbitmq.model.NewCreateUserRequestModel;
import com.busecnky.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewUserConsumer {

    private final UserProfileService userProfileService;
    @RabbitListener(queues = ("${rabbitmq.queueRegister}"))
    public void newUserCreate(NewCreateUserRequestModel model){
        log.info("User {}", model.toString());

        userProfileService.createUser(IUserMapper.INSTANCE.toNewCreateUserRequestDto(model));
    }
}
