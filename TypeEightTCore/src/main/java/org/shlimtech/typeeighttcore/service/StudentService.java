package org.shlimtech.typeeighttcore.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.shlimtech.typeeighttcore.dto.StudentDTO;
import org.shlimtech.typeeighttcore.model.Student;
import org.shlimtech.typeeighttcore.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public StudentDTO registerStudent(int user_id, String group) {
        if (studentRepository.existsById(user_id)) {
            throw new RuntimeException("Student is already registered");
        }

        Student student = new Student(user_id, group);
        studentRepository.save(student);
        return modelMapper.map(student, StudentDTO.class);
    }

    @Transactional
    public StudentDTO loadStudent(int user_id) {
        Optional<Student> studentOptional = studentRepository.findById(user_id);

        if (studentOptional.isEmpty()) {
            return null;
        }

        return modelMapper.map(studentOptional.get(), StudentDTO.class);
    }

}
