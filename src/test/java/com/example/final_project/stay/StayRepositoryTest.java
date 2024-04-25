package com.example.final_project.stay;

import com.example.final_project._core.errors.exception.Exception404;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class StayRepositoryTest {

    @Autowired
    StayRepository stayRepository;

}
