package com.company;

import jodd.json.meta.JSON;

import java.util.ArrayList;

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
}
