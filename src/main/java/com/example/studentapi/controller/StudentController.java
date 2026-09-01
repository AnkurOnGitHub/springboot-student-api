package com.example.studentapi.controller;
import com.example.studentapi.Entity.Student;
import com.example.studentapi.dto.StudentDto;
import com.example.studentapi.dto.AddStudentRequestDto;
import com.example.studentapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;


    @GetMapping("")
    public ResponseEntity<List<StudentDto>> getAllStudent(){
        return ResponseEntity.ok(service.getAllStudent());
    }

    @GetMapping("/{myid}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long myid){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getStudentById(myid));
    }


    @PostMapping("")
    public ResponseEntity<StudentDto> createNewStudent(@RequestBody AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addStudent(addStudentRequestDto));

    }

    @PutMapping("/{myid}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long myid, @RequestBody AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.ok(service.updateStudent(myid, addStudentRequestDto));
    }

    @DeleteMapping("/{id}")
    public boolean deleteStudent(@PathVariable Long id){
        return service.deleteStudent(id);
    }





//    @PatchMapping("/name/{myid}")
//    public ResponseEntity<>


    /*
    }@GetMapping(value = "/student", produces = "application/json")
    public Student getStudent1(){
        System.out.println("Json method executed...");
        return new Student(1,"Ankur","ankur@gmail.com");
    }
     */






}
