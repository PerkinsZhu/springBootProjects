package com.perkins.springcloudgateway;

import com.perkins.config.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringCloudGatewayApplicationTests {
    @Autowired
    PersonService personService;

    @Test
    void contextLoads() {
        personService.sayHello();
    }

}
