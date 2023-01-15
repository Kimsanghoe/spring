package hello.core.singleton;

import java.util.ArrayList;
import java.util.List;

public class StateFullService {

//    private int price; //상태를 유지하는 필드

    /*public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 여기가 문제!
    }*/

  /*  public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }*/

    public List order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        List order = new ArrayList();
        order.add(name);
        order.add(price);

        return order;
    }

/*
    public int getPrice() {
        return price;
    }*/
}
