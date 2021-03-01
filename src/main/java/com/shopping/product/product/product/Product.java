package com.shopping.product.product.product;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Product {

    private List<String> id, name, price;
    private Map<Integer, String> menus;

    Product(){
        id = new ArrayList<>();
        name = new ArrayList<>();
        price = new ArrayList<>();
        menus = new HashMap<>();
    }

    public List<String> getName() {
        return name;
    }
    public String getNametoid(int id) {
        return name.get(id-1);
    }

    public void setName(List<String> names) {
        this.name = names;
    }

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }

    public List<String> getPrice() {
        return price;
    }

    public String getPricetoid(int id) {
        return price.get(id-1);
    }

    public void setPrice(List<String> price) {
        this.price = price;
    }

    public Map<Integer, String> getMenus() {
        return menus;
    }

    public void setMenus(Map<Integer, String> menus) {
        this.menus = menus;
    }
}