package com.courseerpbackend.demo1.service.base;

public interface TokenGenerator <T>{
    String generate(T obj);
}
