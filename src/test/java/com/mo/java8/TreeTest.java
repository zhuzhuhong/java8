package com.mo.java8;

import com.mo.java8.entity.Tree;
import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Null;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.logging.Level;

/**
 * @author zhuzhuhong
 * @description 测试类
 * @create 2018-05-25 17:06
 **/
@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TreeTest {

    @Before
    public void init(){

    }

    @After
    public void after(){}

    @Test
    public void testFupdate(){
        Tree tree = new Tree("Marry", 22,
                new Tree("Emily", 20, new Tree("Alan", 50, null, null), new Tree("Georgie", 23, null, null)),
                new Tree("Tian", 29, null, new Tree("Raoul", 23, null, null)));
        log.info("before update:{}",tree);
        Tree fupdate = fupdate("Will", 26, tree);
        log.info("fupdate:{}",fupdate);
    }


    private Tree fupdate(String key,int newValue,Tree tree){
        return tree == null ?
                new Tree(key, newValue, null, null) :
                key.equals(tree.getKey()) ?
                        new Tree(key, newValue, tree.getLeft(), tree.getRight()) :
                        key.compareTo(tree.getKey()) < 0 ?
                                new Tree(key, newValue, fupdate(key, newValue, tree.getLeft()), tree.getRight()) :
                                new Tree(key, newValue, tree.getLeft(), fupdate(key, newValue, tree.getRight()));
    }



}
