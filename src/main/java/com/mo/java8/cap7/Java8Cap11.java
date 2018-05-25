package com.mo.java8.cap7;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.concurrent.*;

import static java.util.concurrent.Executors.newCachedThreadPool;

/**
 * @author zhuzhuhong
 * @description 并发
 * @create 2018-05-24 15:52
 **/
@Log4j2
public class Java8Cap11 {

    public static void main(String[] args) {
       // commFuture();
        //日期
        LocalDate date = LocalDate.now();
        LocalDateTime dateTime = LocalDateTime.now();
        //年
        int year = date.getYear();
        Month month = date.getMonth();
        int dayOfMonth = date.getDayOfMonth();
        int i = date.get(ChronoField.YEAR);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        /*log.info("year:{}",year);
        log.info("month:{}",month.getValue());
        log.info("day:{}",dayOfMonth);
        log.info("YEAR:{}",i);*/
        log.info("datetime:{}",dateTime.format(dateTimeFormatter));
    }

    public static void commFuture() {
        ExecutorService executorService = newCachedThreadPool();
        Future<Double> doubleFuture = executorService.submit(() -> {
            log.info("另一个线程生成一个double类型的数");
            return 12.34;
        });
        log.info("主线程做其他的事");

        try {
            Double result = doubleFuture.get(1, TimeUnit.SECONDS);
            log.info("结果："+result);
        } catch (InterruptedException e) {
            log.info("线程被中断");
        } catch (ExecutionException e) {
            log.info("计算异常");
        } catch (TimeoutException e) {
            log.info("等待超时");
        }
    }

}
