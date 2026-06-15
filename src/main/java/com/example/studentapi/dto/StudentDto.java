package com.example.studentapi.dto;
import lombok.*;


@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class StudentDto {
    private Long id;
    private String name;
    private String email;

}
