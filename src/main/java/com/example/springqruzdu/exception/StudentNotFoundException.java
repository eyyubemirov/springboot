package com.example.springqruzdu.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException() {
        super("STUDENT_NOT_FOUND");
    }
}
