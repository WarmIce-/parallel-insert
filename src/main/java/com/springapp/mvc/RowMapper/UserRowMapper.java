package com.springapp.mvc.RowMapper;

import com.springapp.mvc.models.UserBean;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by MyCall on 10/27/2016.
 */
public class UserRowMapper implements RowMapper<UserBean> {

    private boolean fetchRowID;

    private Logger logger = Logger.getLogger(UserRowMapper.class.getName());

    public UserRowMapper(boolean fetchRowID) {
        this.fetchRowID = fetchRowID;
    }
    @Override
    public UserBean mapRow(ResultSet resultSet, int thisRow) throws SQLException {

        UserBean user = null;
        try {
            user = new UserBean();
            user.setId(resultSet.getInt("ID"));

            if (!fetchRowID) {
                user.setUserId(resultSet.getString("userId"));
                user.setPassword(resultSet.getString("password"));
            }

        } catch (Exception e) {

            logger.info("Error in row mapper class :" + e.getMessage());

        }
        return user;
    }
}

