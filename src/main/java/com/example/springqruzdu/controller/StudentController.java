package com.example.springqruzdu.controller;

import com.example.springqruzdu.dto.StudentDto;
import com.example.springqruzdu.dto.request.CreateStudentRequest;
import com.example.springqruzdu.dto.request.UpdateStudentRequest;
import com.example.springqruzdu.service.StudentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/api/v1")
@RequiredArgsConstructor
public class StudentController {
    private final StudentServiceImpl studentService;

    @GetMapping("getAll")
    public List<StudentDto> getAllStudent() {
        return studentService.getAllStudent();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public StudentDto createStudent(@Valid @RequestBody CreateStudentRequest request) {
        return studentService.createStudent(request);
    }

    @PutMapping("/update")
    public StudentDto updateStudent(@RequestParam Long id, @RequestBody UpdateStudentRequest request) {
        return studentService.updateStudent(id, request);
    }
    @DeleteMapping("/delete")
    public StudentDto deleteByIdStudent(@RequestParam Long id){
        return studentService.deleteByIdStudent(id);
    }
}
