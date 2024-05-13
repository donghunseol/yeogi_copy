package com.example.final_project.faq;

import com.example.final_project.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FaqRepository extends JpaRepository<Faq, Integer> {


}
