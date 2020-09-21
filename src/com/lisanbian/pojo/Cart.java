package com.lisanbian.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//购物车
public class Cart {
   //private Integer totalCount;
   //private BigDecimal totalPrice;


    //Integer表示商品编号
    //CartItem表示商品项
    private Map<Integer,CartItem> items = new LinkedHashMap<>();

    /**
     * 添加商品
     */
    public void addItem(CartItem cartItem){
        //先查看购物车中是否已添加此商品，若已添加，则数量累加，金额累加
        CartItem item = items.get(cartItem.getId());
        if(item==null){
            items.put(cartItem.getId(),cartItem);
        }else {
            item.setCount(item.getCount()+1);
        }

    }

    /**
     * 删除商品项
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改商品数量
     */
    public void updateItem(Integer id,Integer num){
        //先查询是否有此商品，如果有，更新商品数量，更新商品总金额
        CartItem item = items.get(id);
        if(item!=null){
            item.setCount(num);
        }

    }




    public Cart() {
    }

    public Cart(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer,CartItem> entry :items.entrySet()){
            totalCount+=entry.getValue().getCount();
        }
        return totalCount;
    }



    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        Set<Integer> integers = items.keySet();
        for (Integer integer:integers){
            CartItem cartItem = items.get(integer);
            totalPrice=totalPrice.add(cartItem.getTotalPrice());
        }

        return totalPrice;
    }



    public Map<Integer,CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer,CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
