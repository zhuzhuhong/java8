package com.mo.java8.entity;

/**
 * @author zhuzhuhong
 * @description 树
 * @create 2018-05-25 17:04
 **/

public class Tree {

    private String key;
    private int value;
    private Tree left;
    private Tree right;

    public Tree(String key, int value, Tree left,Tree right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public String getKey() {

        return key;
    }

    public int getValue() {
        return value;
    }

    public Tree getLeft() {
        return left;
    }

    public Tree getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "key='" + key + '\'' +
                ", value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
