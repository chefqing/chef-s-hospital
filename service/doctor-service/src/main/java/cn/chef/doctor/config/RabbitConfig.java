package cn.chef.doctor.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public DirectExchange directExchange(){
        return ExchangeBuilder.directExchange("user-exchange").build();
    }
    @Bean
    public Queue addQueue(){
        return  new Queue("user-queue");
    }
    @Bean
    public Binding binding(DirectExchange directExchange ,Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with("add");
    }
}
