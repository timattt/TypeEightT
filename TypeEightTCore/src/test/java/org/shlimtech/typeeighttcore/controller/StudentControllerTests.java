package org.shlimtech.typeeighttcore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.shlimtech.typeeighttcore.BaseTest;
import org.shlimtech.typeeighttcore.dto.BasicDTO;
import org.shlimtech.typeeighttcore.dto.RegistrationDTO;
import org.shlimtech.typeeighttcore.properties.Type8Properties;
import org.shlimtech.typeeighttcore.service.StudentService;
import org.shlimtech.typesixdatabasecommon.dto.UserDTO;
import org.shlimtech.typesixdatabasecommon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StudentControllerTests extends BaseTest {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private Type8Properties type8Properties;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    public void simpleGetAllTest() {
        long id = userService.createOrComplementUser(UserDTO.builder().email("hhh@gmail.com").firstName("hhh").build()).getId();
        Assert.isTrue(userService.loadUser("hhh@gmail.com") != null, "must contains user");

        BasicDTO basicDTO = objectMapper.readValue(mockMvc.perform(get("/teaching/get_all")
                        .with(SecurityMockMvcRequestPostProcessors.jwt().jwt(c -> c.claim("id", id)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), BasicDTO.class);

        Assert.isTrue(basicDTO.getUser().getId() == id, "invalid user id");
    }

    @Test
    @SneakyThrows
    public void registrationTest() {
        int id = userService.createOrComplementUser(UserDTO.builder().email("hhh@gmail.com").firstName("hhh").build()).getId();
        Assert.isTrue(userService.loadUser("hhh@gmail.com") != null, "must contains user");

        Assert.isTrue(studentService.loadStudent(id) == null, "strange");
        BasicDTO basicDTO = objectMapper.readValue(mockMvc.perform(post("/teaching/register")
                        .content(objectMapper.writeValueAsString(RegistrationDTO.builder().group(type8Properties.getGroups().get(0)).build()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.jwt().jwt(c -> c.claim("id", (long) id)))
                )
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8), BasicDTO.class);

        Assert.isTrue(basicDTO.getUser().getId() == id && basicDTO.getStudent().getGroup().equals(type8Properties.getGroups().get(0)), "invalid user id");
        Assert.isTrue(studentService.loadStudent(id).getGroup().equals(type8Properties.getGroups().get(0)), "bad group");
    }

}
