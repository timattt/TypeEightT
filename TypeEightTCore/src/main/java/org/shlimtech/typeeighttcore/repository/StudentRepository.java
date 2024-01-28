package org.shlimtech.typeeighttcore.repository;

import org.shlimtech.typeeighttcore.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
