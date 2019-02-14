package com.lichunliang.huoyunwuliu.mapper;


import com.lichunliang.huoyunwuliu.pojo.Order;

public interface OrderMapper {
    int addOrder(Order order);

    Order getOrderByOrderNum(String orderNum);

    void updateOrder(Order order);
}
