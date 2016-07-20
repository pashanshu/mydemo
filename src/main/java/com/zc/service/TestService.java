package com.zc.service;

import java.util.List;

import com.zc.model.User;

public interface TestService {
    public List<User> queryAll();
    public User queryByName(String name);
}
