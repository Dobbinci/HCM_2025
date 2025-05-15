package com.be.view.professor;

import com.be.model.Professor;
import com.be.view.professor.applicationViewStrategy.ApplicationViewStrategy;

public class ProfessorMenuContext {
    private ApplicationViewStrategy strategy;

    public void setStrategy(ApplicationViewStrategy strategy) {
        this.strategy = strategy;
    }

    public void show() {
        strategy.show();
    }
}
