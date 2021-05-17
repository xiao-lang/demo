package com.jwq.service;

import com.jwq.model.TestUser;

import java.util.List;

public interface TestUserService {
    TestUser getTestUser(TestUser testUser);
    List<TestUser> selectTestUserList();
    public  String querycategoryName(Long testId,String name);
}
