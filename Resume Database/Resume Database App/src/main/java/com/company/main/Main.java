/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.main;

import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.entity.User;
import com.company.entity.UserSkill;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.EmploymentHistory;
import java.util.List;

/**
 *
 * @author hp
 */
public class Main {

    public static void main(String[] args) throws Exception {
        EmploymentHistoryDaoInter employmentHistoryDaoInter = Context.instanceEmploymentHistoryDao();
        List<EmploymentHistory> list = employmentHistoryDaoInter.getAllEmploymentHistoryByUserId(1);
        System.out.println("list " + list);
    }
}
