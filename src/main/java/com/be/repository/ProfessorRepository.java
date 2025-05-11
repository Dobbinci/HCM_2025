package com.be.repository;

import com.be.model.Professor;

import java.util.List;

public interface ProfessorRepository {

    void save(Professor professor);
    List<Professor> findAll();
    Professor findById(Long id);
    void update(Professor professor);
    void delete(Professor professor);
}
