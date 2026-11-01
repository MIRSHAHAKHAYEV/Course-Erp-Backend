package com.courseerpbackend.demo1.service.user;

import com.courseerpbackend.demo1.entity.user.User;

public interface IUserService {

    User getByEmail(String email);

    void insert(User user);

    boolean checkByEmail(String email);
}
