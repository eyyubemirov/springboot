package com.example.springqruzdu.mapper;

import com.example.springqruzdu.dto.StudentDto;
import com.example.springqruzdu.dto.request.CreateStudentRequest;
import com.example.springqruzdu.dto.request.UpdateStudentRequest;
import com.example.springqruzdu.model.Student;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE
        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    Student mapCreateRequestToStudent(CreateStudentRequest request);

    StudentDto mapEntityToDto(Student student);


    Student mapUpdateResponseToEntitiy(@MappingTarget Student student, UpdateStudentRequest request);
}
