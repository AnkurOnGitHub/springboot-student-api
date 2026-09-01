package com.example.studentapi.service.implementation;

import com.example.studentapi.dto.StudentDto;
import com.example.studentapi.Entity.Student;
import com.example.studentapi.dto.AddStudentRequestDto;
import com.example.studentapi.repository.StudentRepository;
import com.example.studentapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final RedisService redisService;
    //using model Mapper convert studentDto to Student[Entity] vice versa

    @Override
    @Cacheable(value = "students")
    public List<StudentDto> getAllStudent() {
//        String key = "student-list";
//        List<StudentDto> cachedStudent = redisService.get(key, new TypeReference<List<StudentDto>>() {});
//        if(cachedStudent != null){
//            System.out.println("List Found in cache");
//            return cachedStudent;
//        }
        List<Student>list = studentRepository.findAll();
//        redisService.set(key,list,300L);
        return list.stream().map(s->modelMapper.map(s,StudentDto.class)).toList();
    }

    @Override
    @Cacheable(value = "students",key = "#id")
    public StudentDto getStudentById(Long id) {
//        StudentDto studentDto = redisService.get("student_id" + id, StudentDto.class);
//        if(studentDto != null){
//            System.out.println("found in cache");
//            return studentDto;
//        }
//        else{
            Student student = studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Student not found..."));
            final StudentDto studentDto1 = modelMapper.map(student, StudentDto.class);
//            redisService.set("student_id" + id,studentDto1,60L);
            return studentDto1;
//        }


    }

    @Override
    @CacheEvict(value = "students",allEntries = true)
    public StudentDto addStudent(AddStudentRequestDto student) {
        String key = "student-list";
        Student student1 = modelMapper.map(student,Student.class);
        Student s = studentRepository.save(student1);
//        redisService.remove(key);
        return modelMapper.map(s,StudentDto.class);
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
