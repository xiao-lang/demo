package com.jwq.dao;

import com.jwq.model.TestUser;

import java.util.List;

public interface TestUserDAO {
    int deleteByPrimaryKey(Long testId);

    int insert(TestUser record);

    int insertSelective(TestUser record);

    TestUser selectByPrimaryKey(Long testId);

    int updateByPrimaryKeySelective(TestUser record);

    int updateByPrimaryKey(TestUser record);

    List<TestUser> selectTestUserList();
}