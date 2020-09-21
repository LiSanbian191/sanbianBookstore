package com.lisanbian.test;

import com.lisanbian.dao.OrderDao;
import com.lisanbian.dao.impl.OrderDaoImpl;
import com.lisanbian.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("1234567890",new Date(),new BigDecimal(100),0 ,1));
        orderDao.saveOrder(new Order("1234567891",new Date(),new BigDecimal(200),0,2));
        orderDao.saveOrder(new Order("1234567892",new Date(),new BigDecimal(300),0,1));
        orderDao.saveOrder(new Order("1234567893",new Date(),new BigDecimal(400),0,2));
    }

    @Test
    public void queryOders() {
        OrderDao orderDao = new OrderDaoImpl();
        List<Order> orders = orderDao.queryOders();
        for(Order order:orders){
            System.out.println(order);
        }
    }

    @Test
    public void changeOrderStatus() {
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.changeOrderStatus("1234567890",1);
        List<Order> orders = orderDao.queryOders();
        for(Order order:orders){
            System.out.println(order);
        }
    }

    @Test
    public void queryOrdersByUserId() {
        OrderDao orderDao = new OrderDaoImpl();
        List<Order> orders = orderDao.queryOrdersByUserId(1);
        for(Order order:orders){
            System.out.println(order);
        }

    }
}