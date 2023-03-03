package com.example.springcommonmistakes.example4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author jianchengwang
 * @date 2023/3/3
 */
@Repository
@Slf4j
public class OracleDataService4 implements DataService4 {
    @Override
    public void logStudent(int id) {
        log.info("log student info maintained by oracle");
    }
}
