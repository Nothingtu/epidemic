package com.duing;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@MapperScan("com.duing.mapper")
@SpringBootTest
class EpidemicApplicationTests {

    @Test
    void contextLoads() {

    }

}
