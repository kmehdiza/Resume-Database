/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entity;

/**
 *
 * @author hp
 */
public class UserSkill {
    
    private User user; 
    private Skill skill; 
    private int power; 

    public UserSkill(User user, Skill skill, int power) {
        this.user = user;
        this.skill = skill;
        this.power = power;
    }
    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "UserSkill{" + "user=" + user + ", skill=" + skill + ", power=" + power + '}';
    }

}
