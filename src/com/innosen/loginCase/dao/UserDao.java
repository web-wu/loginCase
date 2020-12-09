package com.innosen.loginCase.dao;

import com.innosen.loginCase.entity.User;
import com.innosen.loginCase.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class UserDao {
    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    public User login(User loginUser) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(), loginUser.getPassword());
            return user;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
