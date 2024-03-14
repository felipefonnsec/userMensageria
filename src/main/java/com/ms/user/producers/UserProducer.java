package com.ms.user.producers;

import com.ms.user.dtos.EmailDto;
import com.ms.user.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    //instancia(injetar) do rabbit tempalte
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${broker.queue.email.name}")
    private String routingKey;

    //metodo para converter e enviar a mensagem
    public void publishMessageEmail(UserModel userModel){
        var emailDto = new EmailDto();
        emailDto.setUserId(userModel.getId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject("Te amo!");
        emailDto.setText(userModel.getName() + ", To com saudades de você coisa linda: Ass: Feh");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}
