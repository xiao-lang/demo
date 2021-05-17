package com.jwq.service.impl;


import com.jwq.dao.TestUserDAO;
import com.jwq.model.TestUser;
import com.jwq.service.TestUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("testUserService")
public class TestUserServiceImpl implements TestUserService {
    @Resource
    private TestUserDAO testUserDAO;

    private Log logger = LogFactory.getLog(TestUserServiceImpl.class);

    @Override
    public TestUser getTestUser(TestUser testUser) {
        logger.info("查询开始");
//        int i = testUserDAO.insertSelective(testUser);
        TestUser user = testUserDAO.selectByPrimaryKey(100009l);
        logger.info("查询结束");
        return user;
    }

    @Override
    public List<TestUser> selectTestUserList() {
        List<TestUser> testUserList = testUserDAO.selectTestUserList();
        return testUserList;
    }
    public  String querycategoryName(Long testId,String name){

         TestUser testUser = testUserDAO.selectByPrimaryKey(testId);
         if(StringUtils.isEmpty(name)){
             name = testUser.getTestName();
         }else{
             name = testUser.getTestName()+"/"+name;
        }
         return  querycategoryName(testUser.getParentId(),name);
    }

    public static void main(String[] args) {
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date2 = simpleDateFormat.parse("2020-06-24 22:38:10");
            System.out.println(date1.getTime());
            System.out.println(date2.getTime());
            long time = date1.getTime() - date2.getTime();
            System.out.println(time);
            if(time > 1*60*1000)
                System.out.println("不在一分钟内");
            else
                System.out.println("在一分钟内");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
