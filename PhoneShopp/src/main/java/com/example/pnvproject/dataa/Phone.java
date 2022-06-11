package com.example.pnvproject.dataa;

public class Phone {
    public int quantity;
    public int id;
    public String phonename;
    public float phoneprice;
    public Phone(int id, String phonename, float phoneprice, int quantity) {
        this.id = id;
        this.phonename = phonename;
        this.phoneprice = phoneprice;
        this.quantity = quantity;
    }

    public Phone(String phonename, Float phoneprice, Integer quantity) {
        this.phonename = phonename;
        this.phoneprice = phoneprice;
        this.quantity = quantity;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return phonename;
    }

    public void setName(String phonename) {
        this.phonename = phonename;
    }




    public float getPrice() {
        return phoneprice;
    }

    public void setPrice(float phoneprice) {
        this.phoneprice = phoneprice;
    }
    public int getQuantity() {
        return quantity;
    }

    public void getQuantity(int quantity) {
        this.quantity = quantity;
    }
}
