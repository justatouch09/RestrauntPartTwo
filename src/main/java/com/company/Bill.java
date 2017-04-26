package com.company;

import jodd.json.meta.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jaradtouchberry on 4/24/17.
 */
public class Bill {
    @JSON(name = "table_id")
    String tableId;

    ArrayList<MenuItem> items;


    //paramaterless constructor
    public Bill() {

    }

    public Bill(String tableId, ArrayList<MenuItem> items) {
        this.tableId = tableId;
        this.items = items;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<MenuItem> items) {
        this.items = items;
    }

    public List getIndividualOrders() {
        return items.stream().map(mi ->
            new Object() {
                @JSON(name = "table_id")
                public String objTableId = tableId;
                public MenuItem item = mi;
            }
        ).collect(Collectors.toList());
    }
}
