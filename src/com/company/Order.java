package com.company;

/**
 * Created by jaradtouchberry on 4/21/17.
 */
public class Order {
    String tableId;
    int id;

    public Order(String tableId, int id) {
        this.tableId = tableId;
        this.id = id;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

