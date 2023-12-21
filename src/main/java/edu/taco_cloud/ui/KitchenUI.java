package edu.taco_cloud.ui;

import org.springframework.stereotype.Component;

@Component
public class KitchenUI {

    public void displayOrder(Object o) {
        System.out.println(o.toString());
    }
}
