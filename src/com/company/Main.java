package com.company;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;
import spark.Spark;

import java.util.ArrayList;

public class Main {

    public static ArrayList<MenuItem> menu = new ArrayList<>();

    public static void main(String[] args) {
        // write your code here

        Spark.init();
        menu.add(new MenuItem(1, "appetizer", "cheese-sticks", 5.25, true));
        menu.add(new MenuItem(2, "dinner", "steak", 18.99, true));
        menu.add(new MenuItem(3, "lunch", "burger", 10.75, true));
        menu.add(new MenuItem(4, "brunch", "eggs", 7.99, true));
        menu.add(new MenuItem(5, "breakfast", "pancakes", 6.99, true));
        // order an item from the menu
        Spark.get("/order/:menu_item_id", (req, res) -> {
            JsonSerializer serializer = new JsonSerializer();
            return serializer.serialize(menu);
        });

        // get the menu
        Spark.post("/menu", (req, res) -> {
            String json = req.body();
            MenuItem list = new JsonParser().parse(json, MenuItem.class);
            menu.add(list);

            return "";
        });

        Spark.get("/menu", (req, res) -> {
            

            );

        });
    }
}

