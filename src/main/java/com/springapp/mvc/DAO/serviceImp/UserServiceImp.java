package com.springapp.mvc.DAO.serviceImp;

import com.springapp.mvc.DAO.service.UserService;
import com.springapp.mvc.RowMapper.UserRowMapper;
import com.springapp.mvc.models.UserBean;
import org.springframework.jdbc.core.JdbcTemplate;


public class UserServiceImp extends JdbcTemplate implements UserService {

    private JdbcTemplate getJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return jdbcTemplate;
    }

    @Override
    public boolean insertIntoUserBean(UserBean userBean) {
        String sql = "INSERT INTO `userbean`(`firstName`,`lastName`) VALUES(?,?)";

        String firstName = userBean.getFirstName();
        String lastName = userBean.getLastName();

        Object[] args = {firstName, lastName};

        return getJdbcTemplate().update(sql, args) > 0;
    }

    @Override
    public boolean insertIntoUserTable(int lastInsertedID, UserBean userBean) {
        String sql = "INSERT INTO `user`(`ID`,`userId`,`password`) VALUES (?,?,?)";
        String userID = userBean.getUserId();
        String password = userBean.getPassword();

        Object[] args = {lastInsertedID, userID, password};
        return getJdbcTemplate().update(sql, args) > 0;
    }

    @Override
    public int getLastInsertedID(String firstName, String lastName) {
        String select_query = "SELECT `ID` from `userbean` WHERE `firstName`=? AND `lastName`=?";

        Object[] args = {firstName, lastName};
        UserBean userBean = getJdbcTemplate().queryForObject(select_query, args, new UserRowMapper(true));
        return userBean.getId();
    }


    public UserBean getById(int uId) {
    String sql1="INSERT INTO `user`(`userId`,`password`,`id`) VALUES(?,?,?)";

    Object[]query={uId};
    UserBean user = getJdbcTemplate().queryForObject(sql1, new UserRowMapper(false), query);
    return user;

    }

    @Override
    public boolean user(UserBean user) {
        boolean userExist=false;
        String sql="SELECT count(*) FROM `user` WHERE `userId`=? And `password`=?";
        String userId=user.getUserId();
        String password=user.getPassword();
        Object[]args={userId,password};
       int rowCount= getJdbcTemplate().queryForObject(sql,args,Integer.class);
        if(rowCount == 1)
        {
            userExist=true;
        }
        return userExist;
    }

}

