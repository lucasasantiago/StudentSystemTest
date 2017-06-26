package com.ses.dao;

import org.springframework.data.repository.CrudRepository;

import com.ses.domain.Student;

public interface StudentDAO extends CrudRepository<Student, Long> {

}
