package com.bytehonor.server.demo.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bytehonor.sdk.starter.jdbc.constant.SqlValueTypes;
import com.bytehonor.server.demo.spring.model.UserProfile;

@SpringBootTest
public class JdbcPerformanceTest {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcPerformanceTest.class);

    private static final RowMapper<UserProfile> MAPPER = new RowMapper<UserProfile>() {

        @Override
        public UserProfile mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserProfile model = new UserProfile();
            model.setId(rs.getLong("id"));
            model.setAge(rs.getInt("age"));

            model.setUpdateAt(rs.getLong("update_at"));
            model.setCreateAt(rs.getLong("create_at"));
            return model;
        }

    };

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserProfileDao userProfileDao;

    @Test
    public void test() {

        long id = 9L;

        int size = 10000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            userProfileDao.get(id);
        }
        LOG.info("dao cost:{}", System.currentTimeMillis() - start);

        String sql = "select * from tbl_user_profile where id = ?";
        Object[] args = new Object[] { id };
        int[] types = new int[] { SqlValueTypes.LONG };
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            jdbcTemplate.query(sql, args, types, MAPPER);
        }
        LOG.info("raw cost:{}", System.currentTimeMillis() - start);
    }
}
