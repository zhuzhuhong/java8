package com.mo.java8.cap7;

import com.sun.scenario.effect.impl.prism.PrDrawable;
import com.sun.xml.internal.ws.util.CompletedFuture;
import lombok.extern.java.Log;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author zhuzhuhong
 * @description 商店
 * @create 2018-05-24 16:08
 **/
@Log
public class Shop {

    public double getPrice(String product){
       return calculatePrice(product);
    }

    public static void deplay(){
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
        }
    }

    private double calculatePrice(String product){
        deplay();
        Random random = new Random();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public Future<Double> getPriceAsync(String product){
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(()->{
            double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }

}
