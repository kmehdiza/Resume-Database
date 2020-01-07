/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.User;
import com.company.entity.UserSkill;
import java.util.List;

/**
 *
 * @author hp
 */
public interface UserDaoInter {
    
    public List<User> getAll();
    public User getById(int id);
    
    public boolean updateUser(User user);
    public boolean addUser(User user);
    
    public boolean removeUser(int id);
    
}
