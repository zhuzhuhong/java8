package com.mo.java8;

import lombok.extern.java.Log;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Lenovo
 * @Date: 2018/5/13
 * @Time: 10:50
 * @Description: Java8新特性
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class Java8Tests {
    private List<Integer> numbers = new ArrayList<>(16);

    @Before
    public void init() {
        numbers = Arrays.asList(1, 2, 3, 4, 1, 2, 5, 6);
    }

    @After
    public void clean() {
        numbers = null;
    }

    /**
     * 选出偶数，再去重
     */
    @Test
    public void testStreamDistinct() {
        List<Integer> integerList = numbers.stream().filter(i -> i % 2 == 0)
                .distinct()
                .collect(Collectors.toList());
        log.info(integerList.toString());
    }



}
