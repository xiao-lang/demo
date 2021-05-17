package com.jwq.model;

import java.util.Date;

public class TestUser {
    private Long testId;

    private String testName;

    private Short testAge;

    private Short testSex;

    private Date testBirthday;

    private Long parentId;

    private  String  testDate;

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName == null ? null : testName.trim();
    }

    public Short getTestAge() {
        return testAge;
    }

    public void setTestAge(Short testAge) {
        this.testAge = testAge;
    }

    public Short getTestSex() {
        return testSex;
    }

    public void setTestSex(Short testSex) {
        this.testSex = testSex;
    }

    public Date getTestBirthday() {
        return testBirthday;
    }

    public void setTestBirthday(Date testBirthday) {
        this.testBirthday = testBirthday;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }
}