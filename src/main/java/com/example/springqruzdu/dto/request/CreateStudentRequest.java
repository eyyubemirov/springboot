package com.example.springqruzdu.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentRequest {
    @NotBlank(message = "error.emptyName")
    private String name;
    @NotBlank(message = "error.emptySurname")
    private String surname;
    private String groupName;
}
