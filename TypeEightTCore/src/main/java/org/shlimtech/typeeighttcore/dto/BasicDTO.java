package org.shlimtech.typeeighttcore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.shlimtech.typesixdatabasecommon.dto.UserDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicDTO {

    private UserDTO user;
    private StudentDTO student;

}
