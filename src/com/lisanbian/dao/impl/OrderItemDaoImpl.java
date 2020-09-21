package com.lisanbian.dao.impl;

import com.lisanbian.dao.BaseDao;
import com.lisanbian.dao.OrderItemDao;
import com.lisanbian.pojo.Order;
import com.lisanbian.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item (`name`,`price`,`total_money`,`count`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getPrice(),orderItem.getTotal_money(),orderItem.getCount(),orderItem.getOrder_id());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        String sql = "select * from t_order_item where order_id = ?";
        return queryForList(sql,OrderItem.class,orderId);
    }
}
