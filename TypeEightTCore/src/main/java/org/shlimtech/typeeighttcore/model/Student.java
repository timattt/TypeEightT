package org.shlimtech.typeeighttcore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "type8student")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Column(name = "student_group")
    private String group;


}
