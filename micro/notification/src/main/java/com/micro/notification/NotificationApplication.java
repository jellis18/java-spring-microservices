package com.micro.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.micro.notification",
        "com.micro.amqp"
})
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    // @Bean
    // CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer,
    // NotificationConfig notificationConfig) {
    // return args -> {
    // producer.publish(new Person("Justin", 36),
    // notificationConfig.getInternalExchange(),
    // notificationConfig.getInternalNotificationRoutingKey());
    // };
    // }

    // record Person(String name, int age) {
    // }

}
