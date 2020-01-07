/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.entity.EmploymentHistory;
import com.company.entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {

    private EmploymentHistory getEmploymentHistory(ResultSet resultSet) throws Exception {
        String header = resultSet.getString("header"); 
        Date beginDate = resultSet.getDate("begin_date"); 
        Date end_date = resultSet.getDate("end_date");
        String jobDescription = resultSet.getString("job_description");
        int userId = resultSet.getInt("id_user"); 
        
        return new EmploymentHistory(header, beginDate, end_date, jobDescription, new User(userId)); 
    }

    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) {

        List<EmploymentHistory> list = new ArrayList<>();
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from employment_history where id_user = ?");
            preparedStatement.setInt(1, userId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                EmploymentHistory employmentHistory = getEmploymentHistory(resultSet);
                list.add(employmentHistory);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }
}
