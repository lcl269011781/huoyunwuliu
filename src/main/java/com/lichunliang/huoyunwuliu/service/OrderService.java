package com.lichunliang.huoyunwuliu.service;


import com.lichunliang.huoyunwuliu.pojo.Order;

public interface OrderService {
    int addOrder(Order order);

    Order getOrderByOrderNum(String orderNum);
    void updateOrder(Order order);

}
