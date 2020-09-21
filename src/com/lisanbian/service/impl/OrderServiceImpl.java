package com.lisanbian.service.impl;

import com.lisanbian.dao.BookDao;
import com.lisanbian.dao.OrderDao;
import com.lisanbian.dao.OrderItemDao;
import com.lisanbian.dao.impl.BookDaoImpl;
import com.lisanbian.dao.impl.OrderDaoImpl;
import com.lisanbian.dao.impl.OrderItemDaoImpl;
import com.lisanbian.pojo.*;
import com.lisanbian.service.OrderService;
import org.apache.catalina.tribes.tipis.AbstractReplicatedMap;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号要唯一，时间戳加用户即可保证唯一
        String orderId = System.currentTimeMillis()+""+userId;

        //创建订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);

        //保存订单
        orderDao.saveOrder(order);

        //保存订单项，遍历购物车中的商品项，转化成订单项保存
        for(Map.Entry<Integer, CartItem> entry :cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            Book book = bookDao.querryBookByID(cartItem.getId());
            if(book.getStock()<cartItem.getCount()){
                orderId = "订单结算失败："+"《"+book.getName()+"》"+"的库存为："+book.getStock()+",库存不足，订单无法结算。";
                return orderId;
            }

            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            orderItemDao.saveOderItem(orderItem);
            //没生成一个订单项，那么数据库中的相应图书的销量与库存都应该有相应的变化
            //根据商品id查找返回对象
            //销量增加
            book.setSales(book.getSales()+cartItem.getCount());
            //库存减少
            book.setStock(book.getStock()-cartItem.getCount());
            //保存修改
            bookDao.updateBook(book);
        }
        //创建订单成功，购物车清空
        cart.clear();
        //返回订单号
        return "订单结算成功，您的订单号为："+orderId+"，我们会尽快为您处理！";
    }

    @Override
    public List<Order> showAllOrders() {
        List<Order> orders = orderDao.queryOders();
        return orders;
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,1);
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        List<OrderItem> orderItems = orderItemDao.queryOrderItemByOrderId(orderId);
        return orderItems;
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        List<Order> orders = orderDao.queryOrdersByUserId(userId);
        return orders;
    }

    @Override
    public void receiveOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,2);

    }
}