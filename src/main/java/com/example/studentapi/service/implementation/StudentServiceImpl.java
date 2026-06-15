package com.example.studentapi.service.implementation;

import com.example.studentapi.dto.StudentDto;
import com.example.studentapi.Entity.Student;
import com.example.studentapi.dto.AddStudentRequestDto;
import com.example.studentapi.repository.StudentRepository;
import com.example.studentapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;                                          //using model Mapper convert studentDto to Student[Entity] vice versa
    @Override
    public List<StudentDto> getAllStudent() {

        List<Student>list = studentRepository.findAll();
        return list.stream().map(s->modelMapper.map(s,StudentDto.class)).toList();
    }
    @Override
    public StudentDto addStudent(AddStudentRequestDto student) {
        Student student1 = modelMapper.map(student,Student.class);
        Student s = studentRepository.save(student1);
        return modelMapper.map(s,StudentDto.class);
    }

    @Override
    public Optional<StudentDto> getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Student not found..."));
        StudentDto studentDto = modelMapper.map(student,StudentDto.class);
        return Optional.ofNullable(studentDto);
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto) {
        Student student = studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Student not found..."));
        modelMapper.map(addStudentRequestDto,student);
        Student student1 =  studentRepository.save(student);
        return modelMapper.map(student1,StudentDto.class);

    }

    @Override
    public boolean deleteStudent(Long id) {
        final Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found..."+id));
        studentRepository.deleteById(id);
        return true;

    }
}
