package org.shlimtech.typeeighttcore.service;

import org.junit.jupiter.api.Test;
import org.shlimtech.typeeighttcore.BaseTest;
import org.shlimtech.typeeighttcore.dto.StudentDTO;
import org.shlimtech.typeeighttcore.properties.Type8Properties;
import org.shlimtech.typesixdatabasecommon.dto.UserDTO;
import org.shlimtech.typesixdatabasecommon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class StudentServiceTests extends BaseTest {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private Type8Properties type8Properties;

    @Test
    public void simpleSaveLoadTest() {
        int id = userService.createOrComplementUser(UserDTO.builder().email("aaa@gmail.com").firstName("hhh").build()).getId();
        Assert.isTrue(userService.loadUser("aaa@gmail.com") != null, "must contains user");
        studentService.registerStudent(id, type8Properties.getGroups().get(0));
        StudentDTO studentDTO = studentService.loadStudent(id);
        Assert.isTrue(studentDTO.getGroup().equals(type8Properties.getGroups().get(0)), "student not saved");
    }

    @Test
    public void notRegisteredTest() {
        int id = userService.createOrComplementUser(UserDTO.builder().email("jjj@gmail.com").firstName("hhh").build()).getId();
        Assert.isTrue(userService.loadUser("jjj@gmail.com") != null, "must contains user");

        Assert.isTrue(studentService.loadStudent(id) == null, "strange");
    }

}
