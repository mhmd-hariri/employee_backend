package com.example.springbootemployees.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeRepositoryTest {
@Autowired
private EmployeeRepository underTest;
    @Test
    void selectExistsEmail() {
        //given
        String email = "s.hareere500@gmail.com";
        //where
        Boolean result = underTest.selectExistsEmail(email);
        //Then
        assertThat(result).isTrue();
    }
}