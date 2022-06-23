package com.bytehonor.server.demo.spring.service;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bytehonor.sdk.lang.spring.util.UuidUtils;
import com.bytehonor.sdk.starter.jdbc.query.QueryCondition;
import com.bytehonor.server.demo.spring.TestWrapper;
import com.bytehonor.server.demo.spring.model.UserProfile;

@SpringBootTest
public class UserProfileServiceTest {

    @Autowired
    private UserProfileService userProfileService;

    private static UserProfile model;

    @BeforeAll
    public static void setUp() {
        long now = System.currentTimeMillis();
        int i = (int) (now % 100);
        model = new UserProfile();
        model.setAge(i);
        model.setName("" + i);
        model.setPhone("phone" + now);
        model.setIncome("");
        model.setGender(1);
        model.setOccupation("occupation");
    }

    @Test
    public void testInsert() {
        model.setUuid(UuidUtils.getSimple());
        UserProfile res = userProfileService.insert(model);
        TestWrapper.assertTrue("*testInsert*", res != null);
    }

    @Test
    public void testGet() {
        model.setUuid(UuidUtils.getSimple());
        UserProfile res = userProfileService.insert(model);
        UserProfile resGet = userProfileService.get(res.getId());
        TestWrapper.assertTrue("*testGet*", resGet != null);
    }

    @Test
    public void testCountByCondition() {
        int res = userProfileService.count(QueryCondition.create());
        TestWrapper.assertTrue("*testCountByCondition*", res > 0);
    }

    @Test
    public void testListByCondition() {
        List<UserProfile> list = userProfileService.list(QueryCondition.create());
        TestWrapper.assertTrue("*testListByCondition*", list.size() > 0);
    }

}
