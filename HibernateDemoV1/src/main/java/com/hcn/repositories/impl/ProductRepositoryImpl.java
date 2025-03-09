/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcn.repositories.impl;

import com.hcn.hibernatedemov1.HibernateUtils;
import com.hcn.pojo.Product;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;

/**
 *
 * @author nguyenho
 */
public class ProductRepositoryImpl {
    private static final int PAGE_SIZE = 6;
    
    public List<Product> getProducts(Map<String, String> params) {
        try (Session s = HibernateUtils.getFACTORY().openSession()) {
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<Product> q = b.createQuery(Product.class);
            Root root = q.from(Product.class);
            q.select(root);
            
            if (params != null) {
                List<Predicate> predicates = new ArrayList<>();
                
                String kw = params.get("kw");
                if (kw != null && !kw.isEmpty())
                    predicates.add(b.like(root.get("name"), String.format("%%%s%%", kw)));
                
                String fromPrice = params.get("fromPrice");
                if (fromPrice != null && !fromPrice.isEmpty())
                    predicates.add(b.greaterThanOrEqualTo(root.get("price"), fromPrice));
                
                String toPrice = params.get("toPrice");
                if (toPrice != null && !toPrice.isEmpty())
                    predicates.add(b.lessThanOrEqualTo(root.get("price"), toPrice));
                
                String cateId = params.get("categoryId");
                if (cateId != null && !cateId.isEmpty())
                    predicates.add(b.equal(root.get("category").as(Integer.class), cateId));
                
                q.where(predicates.toArray(Predicate[]::new));
                
                String orderBy = params.get("orderBy");
                if (orderBy != null && !orderBy.isEmpty())
                    q.orderBy(b.asc(root.get(orderBy)));
                
            }
            
            Query query = s.createQuery(q);
            
            if (params != null) {
                int page = Integer.parseInt(params.getOrDefault("page", "1"));
                int start = (page - 1)*PAGE_SIZE;
                
                query.setMaxResults(PAGE_SIZE);
                query.setFirstResult(start);
            }
            
            return query.getResultList();
        }
    }
}
