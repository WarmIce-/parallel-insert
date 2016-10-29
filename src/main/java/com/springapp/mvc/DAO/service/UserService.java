package com.springapp.mvc.DAO.service;


import com.springapp.mvc.models.UserBean;

public interface UserService {
    boolean insertIntoUserBean(UserBean userBean);

    boolean insertIntoUserTable(int lastInsertedID, UserBean userBean);

    int getLastInsertedID(String firstName, String lastName);

    UserBean getById(int uId);

    boolean user(UserBean user);
}
