package com.lisanbian.dao.impl;

import com.lisanbian.dao.BaseDao;
import com.lisanbian.dao.OrderDao;
import com.lisanbian.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`total_money`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrder_id(),order.getCreate_time(),order.getTotal_money(),order.getStatus(),order.getUser_id());
    }

    @Override
    public List<Order> queryOders() {
        String sql = "select * from t_order";
        return queryForList(sql,Order.class);
    }

    @Override
    public int changeOrderStatus(String orderId, int status) {
        String sql = "update t_order set status = ? where order_id = ?";
        return update(sql,status,orderId);
    }

    @Override
    public  List<Order> queryOrdersByUserId(Integer userId) {
        String sql = "select * from t_order where user_id = ?";
        return queryForList(sql,Order.class,userId);
    }
}
