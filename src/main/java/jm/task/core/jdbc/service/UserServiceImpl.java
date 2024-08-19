package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.dao.UserDaoJDBCImpl.stmt;


public class UserServiceImpl implements UserService {
     UserDao userDao;
    {
        try {
            userDao = new UserDaoJDBCImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUsersTable() {
            userDao.createUsersTable();
    }

    public void dropUsersTable() {
      userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
       userDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
       userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
      return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
       userDao.cleanUsersTable();
    }
}
