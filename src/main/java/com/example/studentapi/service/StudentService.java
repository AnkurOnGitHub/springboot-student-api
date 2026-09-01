package com.example.studentapi.service;

import com.example.studentapi.Entity.Student;
import com.example.studentapi.dto.StudentDto;
import com.example.studentapi.dto.AddStudentRequestDto;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentDto> getAllStudent();
    StudentDto addStudent(AddStudentRequestDto studentDto);
    StudentDto getStudentById(Long id);
    StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto);

    boolean deleteStudent(Long id);
}
