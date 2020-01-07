/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.Country;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDaoInter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    private User getUser(ResultSet resultSet) throws Exception {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String phone = resultSet.getString("phone");
        String email = resultSet.getString("email");
        int nationalityId = resultSet.getInt("nationality_id");
        int birthplaceId = resultSet.getInt("birthplace_id");
        Date birthDate = resultSet.getDate("birthdate");
        String nationalityStr = resultSet.getString("nationality");
        String birthplaceStr = resultSet.getString("birthplace");

        Country nationality = new Country(nationalityId, nationalityStr, birthplaceStr);
        Country birthplace = new Country(birthplaceId, nationalityStr, birthplaceStr);

        return new User(id, name, surname, phone, email, birthDate, nationality, birthplace);
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection connection = connect();) {

            System.out.println(connection.getClass().getName());
            Statement statement = connection.createStatement();
            statement.execute("select "
                    + "u.*"
                    + ",n.name as birthplace "
                    + ",c.nationality  from user as u  "
                    + "left join country as n on u.id = n.id "
                    + "left join country as c on u.id = c.id");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                User user = getUser(resultSet);
                result.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUser(User user) {
        try (Connection connection = connect()) {
//createStatement update de encoding ede bilmediyi ucun, preparedStatement istifade elemek lazimdir. 
            PreparedStatement preparedStatement = connection.prepareStatement("update user set name = ?, surname =?, phone=?,email=? where id =?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, user.getId());
            return preparedStatement.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            return statement.execute("delete from user where id = " + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User getById(int userId) {
        User user = null;
        try (Connection connection = connect()) {

            Statement statement = connection.createStatement();
            statement.execute("select "
                    + "u.* "
                    + ",n.nationality  "
                    + ",c.name as birthplace from user as u  "
                    + "left join country as n on u.id = n.id "
                    + "left join country as c on u.id = c.id where u.id = " + userId);

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                user = getUser(resultSet);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean addUser(User user) {

        try (Connection connection = connect()) {
            PreparedStatement prepareStatement = connection.prepareStatement("insert into user (name, surname, phone,email) values (?,?,?,?)");
            prepareStatement.setString(1, user.getName());
            prepareStatement.setString(2, user.getSurname());
            prepareStatement.setString(3, user.getPhone());
            prepareStatement.setString(4, user.getEmail());

            return prepareStatement.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

  
}
