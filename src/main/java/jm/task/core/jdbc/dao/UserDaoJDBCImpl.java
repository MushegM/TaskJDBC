package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
   public static Statement stmt;
    static {
        try {
            stmt = Util.getConnection().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public UserDaoJDBCImpl() throws SQLException {
    }

    public void createUsersTable() {
        String  create = """
                CREATE TABLE if not exists `user_test_db`.`User` (
                  `id` INT NOT NULL AUTO_INCREMENT,
                  `name` VARCHAR(45) NOT NULL,
                  `lastname` VARCHAR(45) NOT NULL,
                  `age` INT(3) NULL,
                  PRIMARY KEY (`id`))
                ENGINE = InnoDB
                DEFAULT CHARACTER SET = utf8;""";
        try {
           stmt.execute(create);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        String drop = "DROP TABLE if exists `user_test_db`.`User`";
        try {
            stmt.execute(drop);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String save =  "INSERT INTO `user_test_db`.`User` ( name, lastname, age) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = stmt.getConnection().prepareStatement(save);
            ps.setString(1,name);
            ps.setString(2,lastName);
            ps.setByte(3,age);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void removeUserById(long id) {
        String remove = "DELETE FROM `user_test_db`.`User` WHERE id = ?";
        try {
            PreparedStatement pstmt = Util.getConnection().prepareStatement(remove);
            pstmt.setLong(1,id);
            pstmt.executeUpdate();
            System.out.println("User " + id + " removed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String getAll = "SELECT * FROM user_test_db.User";
        try {
          ResultSet resultSet = stmt.executeQuery(getAll);
          while (resultSet.next()) {
              User user = new User();
              user.setId(resultSet.getLong("id"));
              user.setName(resultSet.getString("name"));
              user.setLastName(resultSet.getString("lastname"));
              user.setAge(resultSet.getByte("age"));
              users.add(user);
          }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
return users;
    }

    public void cleanUsersTable() {
        String clean ="TRUNCATE `user_test_db`.`User`;";
        try {
            stmt.execute(clean);
            System.out.println("Users table cleaned");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
