package com.company;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;
import spark.Session;
import spark.Spark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    //menu
    private static ArrayList<MenuItem> menuItems = new ArrayList<>();

    public static void main(String[] args) {
        Spark.init();
        //created hashmap purpose of table names, menu items that they have ordered a list
        HashMap<String, ArrayList<MenuItem>> orders = new HashMap<>();
        //make a table store it and it;s value int he hashmap above
        //tables.put("table1", new ArrayList<>());
        //diff items i can choose from. added them
        menuItems.add(new MenuItem(1, "appetizer", "cheese-sticks", 5.25, true));
        menuItems.add(new MenuItem(2, "dinner", "steak", 18.99, true));
        menuItems.add(new MenuItem(3, "lunch", "burger", 10.75, true));
        menuItems.add(new MenuItem(4, "brunch", "eggs", 7.99, true));
        menuItems.add(new MenuItem(5, "breakfast", "pancakes", 6.99, true));

        Spark.post("/order/:menu_item_id", (req, res) -> {
            //get table id from json
            int orderId = Integer.valueOf(req.params("menu_item_id"));
            MenuItem currentMenuItem = null;
            //run block of code for every item in the menu
            for(MenuItem a : menuItems) {
                if (a.getId() == orderId) {
                    currentMenuItem = a;
                    break;
                }
            }
            JsonParser parser = new JsonParser();
            Order a = parser.parse(req.body(), Order.class);
            orders.putIfAbsent(a.getOrderId(), new ArrayList<>());
            orders.get(a.getOrderId()).add(currentMenuItem);

            return "";
        });

        Spark.post("/menuItem", (req, res) -> {
            String json = req.body();
            MenuItem a = new JsonParser().parse(json, MenuItem.class);
            String menuItem = req.params("menuItem");
            menuItems.add(a);

            return "";
        });

        Spark.get("/menu", (req, res) -> {
            JsonSerializer serializer = new JsonSerializer();
            return serializer.serialize(menuItems);
        });

        Spark.get("/bill/:table_id", (req, res) -> {
            JsonSerializer serializer = new JsonSerializer();
            String tableId = req.params("table_id");
            ArrayList tableOrders = orders.get(tableId);
            //put the table orders arraylist in bill
            Bill h = new Bill(tableId, tableOrders);

            return serializer.deep(true).serialize(h);
        });

        Spark.get("/order", ((request, response) -> {
            List individualOrders = new ArrayList();

            for (String key : orders.keySet()) {
                ArrayList items = orders.get(key);

                Bill b = new Bill(key, items);

                individualOrders.addAll(b.getIndividualOrders());
            }

            return new JsonSerializer().deep(true).serialize(individualOrders);
        }));

        Spark.get("/order/:table_id", ((request, response) -> {
            String tableId = request.params("table_id");
            Bill b = new Bill(tableId, orders.get(tableId));
            return new JsonSerializer().deep(true).serialize(b.getIndividualOrders());
        }));
    }
}

