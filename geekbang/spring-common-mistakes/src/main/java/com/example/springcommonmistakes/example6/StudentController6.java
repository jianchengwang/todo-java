package com.example.springcommonmistakes.example6;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jianchengwang
 * @date 2023/3/7
 */
@RestController
@Slf4j
public class StudentController6 {
    private List<Student6> students6;

    public StudentController6(List<Student6> students){
        this.students6 = students;
    }

    @RequestMapping(path = "6/students", method = RequestMethod.GET)
    public String listStudents(){
        return students6.toString();
    };
}
