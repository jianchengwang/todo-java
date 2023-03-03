package com.example.springcommonmistakes.example4;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jianchengwang
 * @date 2023/3/3
 */

@RestController
@Slf4j
@Validated
public class StudentController4 {
//    @Autowired
//    DataService4 dataService4;

//    @Autowired
//    DataService4 SQLiteDataService4;

//    @Autowired
//    @Qualifier("oracleDataService4")
//    DataService4 dataService4;

    @Autowired
    @Qualifier("SQLiteDataService4")
    DataService4 dataService4;

    @RequestMapping(path = "4/students/{id}", method = RequestMethod.GET)
    public void deleteStudent(@PathVariable("id") @Range(min = 1,max = 100) int id){
        dataService4.logStudent(id);
    }
}
