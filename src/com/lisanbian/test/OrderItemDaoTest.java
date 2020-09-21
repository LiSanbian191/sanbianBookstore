package com.lisanbian.test;

import com.lisanbian.dao.OrderItemDao;
import com.lisanbian.dao.impl.OrderItemDaoImpl;
import com.lisanbian.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    @Test
    public void saveOderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOderItem(new OrderItem(null, "java编程思想",2,new BigDecimal(50),new BigDecimal(100),"1234567890"));
        orderItemDao.saveOderItem(new OrderItem(null, "计算机组成原理",8,new BigDecimal(25),new BigDecimal(200),"1234567891"));
        orderItemDao.saveOderItem(new OrderItem(null, "计算机网络",3,new BigDecimal(100),new BigDecimal(300),"1234567892"));
        orderItemDao.saveOderItem(new OrderItem(null, "数据结构与算法",5,new BigDecimal(80),new BigDecimal(400),"1234567893"));
    }

    @Test
    public void queryOrderItemByOrderId() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        List<OrderItem> orderItems = orderItemDao.queryOrderItemByOrderId("1234567890");
        for(OrderItem orderItem:orderItems){
            System.out.println(orderItem);
        }

    }
}