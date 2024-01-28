package org.shlimtech.typeeighttcore.controller;

import lombok.RequiredArgsConstructor;
import org.shlimtech.typeeighttcore.dto.BasicDTO;
import org.shlimtech.typeeighttcore.dto.RegistrationDTO;
import org.shlimtech.typeeighttcore.dto.StudentDTO;
import org.shlimtech.typeeighttcore.service.StudentService;
import org.shlimtech.typesixdatabasecommon.dto.UserDTO;
import org.shlimtech.typesixdatabasecommon.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teaching")
public class StudentController {

    private final UserService userService;
    private final StudentService studentService;

    @GetMapping("/get_all")
    public ResponseEntity<BasicDTO> getAll(JwtAuthenticationToken token) {
        UserDTO userDTO = userService.loadUser(Math.toIntExact((Long) token.getTokenAttributes().get("id")));
        StudentDTO studentDTO = studentService.loadStudent(userDTO.getId());
        return ResponseEntity.ok(new BasicDTO(userDTO, studentDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<BasicDTO> register(JwtAuthenticationToken token, @RequestBody RegistrationDTO registrationDTO) {
        UserDTO userDTO = userService.loadUser(Math.toIntExact((Long) token.getTokenAttributes().get("id")));
        StudentDTO studentDTO = studentService.registerStudent(userDTO.getId(), registrationDTO.getGroup());
        return ResponseEntity.ok(new BasicDTO(userDTO, studentDTO));
    }

}
