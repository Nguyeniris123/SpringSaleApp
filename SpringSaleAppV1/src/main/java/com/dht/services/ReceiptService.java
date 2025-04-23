/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dht.services;

import com.dht.pojo.Cart;
import java.util.List;

/**
 *
 * @author admin
 */
public interface ReceiptService {
    void addReceipt(List<Cart> carts);
}
