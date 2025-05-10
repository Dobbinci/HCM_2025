package com.be.view.professor;

import com.be.service.Professor;
import com.be.view.professor.applicationViewStrategy.ApplicationViewStrategy;

public class ProfessorMenuContext {
    private ApplicationViewStrategy strategy;

    public void setStrategy(ApplicationViewStrategy strategy) {
        this.strategy = strategy;
    }

    public void show(Professor professor) {
        strategy.show(professor);
    }
}
