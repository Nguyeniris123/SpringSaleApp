/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.repositories.impl;


import com.dht.pojo.Cart;
import com.dht.pojo.OrderDetail;
import com.dht.pojo.SaleOrder;
import com.dht.repositories.ProductRepository;
import com.dht.repositories.ReceiptRepository;
import com.dht.repositories.UserRepository;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Repository
@Transactional
public class ReceiptRepositoryImpl implements ReceiptRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ProductRepository prodRepo;

    @Override
    public void addReceipt(List<Cart> carts) {
        Session s = this.factory.getObject().getCurrentSession();
        SaleOrder r = new SaleOrder();
        r.setCreatedDate(new Date());
        r.setUserId(this.userRepo.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName())); // update current user
        s.persist(r);

        for (var c : carts) {
            OrderDetail d = new OrderDetail();
            d.setQuantity(c.getQuantity());
            d.setUnitPrice(c.getPrice());
            d.setOrderId(r);
            d.setProductId(this.prodRepo.getProductById(c.getId()));

            s.persist(d);
        }
    }

}
