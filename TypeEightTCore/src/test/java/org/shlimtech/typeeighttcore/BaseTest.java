package org.shlimtech.typeeighttcore;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.shlimtech.typesixdatabasecommon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = {UserService.class})
@TestPropertySource(properties = {
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1",
        "spring.datasource.username=sa",
        "spring.datasource.password=sa",
        "spring.profiles.active=debug",
        "spring.security.oauth2.resourceserver.jwt.jwk-set-uri=https://google.com",
        "type-8.client-cors-allowed-origin=localhost"
})
@RequiredArgsConstructor
@ComponentScan(basePackages = {"org.shlimtech"})
@EntityScan(basePackages = "org.shlimtech")
@EnableAutoConfiguration
@AutoConfigureMockMvc
public class BaseTest {

    @Autowired
    protected ModelMapper modelMapper;

}
