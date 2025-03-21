/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcn.repositories.impl;

import com.hcn.hibernatedemov1.HibernateUtils;
import com.hcn.pojo.User;
import jakarta.persistence.Query;
import org.hibernate.Hibernate;
import org.hibernate.Session;

/**
 *
 * @author nguyenho
 */
public class UserRepositoryImpl {
    public User getUserByUsername (String username) {
        try (Session s = HibernateUtils.getFACTORY().openSession()) {
            Query q = s.createNamedQuery ("User.findByUsername", User.class);
            q.setParameter("username", username);
            
            return (User) q.getSingleResult();
        }
        
    }
}
