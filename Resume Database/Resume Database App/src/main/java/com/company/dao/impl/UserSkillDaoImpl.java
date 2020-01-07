/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter{

     private UserSkill getUserSkill(ResultSet resultSet) throws Exception {
        int skillId = resultSet.getInt("skill_id");
        int userId = resultSet.getInt("user_id");
        String skillName = resultSet.getString("skill_name");
        int power = resultSet.getInt("power");

        return new UserSkill(new User(userId), new Skill(skillId, skillName), power);
    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> list = new ArrayList<>();
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select u.*, "
                    + "us.user_id, "
                    + "us.skill_id,"
                    + "s.name as skill_name, "
                    + "us.power "
                    + "from user_skill as us "
                    + "left join user as u on  us.user_id = u.id "
                    + "left join skill as s on us.skill_id = s.id where us.user_id=?");
            preparedStatement.setInt(1, userId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                UserSkill userSkill = getUserSkill(resultSet);
                list.add(userSkill);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
