package com.example.springqruzdu.service;

import com.example.springqruzdu.dto.StudentDto;
import com.example.springqruzdu.dto.request.CreateStudentRequest;
import com.example.springqruzdu.dto.request.UpdateStudentRequest;
import com.example.springqruzdu.exception.StudentNotFoundException;
import com.example.springqruzdu.mapper.StudentMapper;
import com.example.springqruzdu.model.Student;
import com.example.springqruzdu.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public List<StudentDto> getAllStudent() {
        return studentRepository.findAll().stream()
                .map(studentMapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    ;

    public StudentDto createStudent(CreateStudentRequest request) {
        Student student = studentRepository.save(studentMapper.mapCreateRequestToStudent(request));
        return studentMapper.mapEntityToDto(student);
    }

    public StudentDto updateStudent(Long id, UpdateStudentRequest request) {
        var student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException());
        studentRepository.save(studentMapper.mapUpdateResponseToEntitiy(student, request));
        return studentMapper.mapEntityToDto(student);
    }

    public StudentDto deleteByIdStudent(Long id){
        Student student=studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException());
        studentRepository.deleteById(id);
        return studentMapper.mapEntityToDto(student);
    }
}


