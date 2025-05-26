package com.be.controller;

public interface Command {
    void execute();
    void undo();
}
