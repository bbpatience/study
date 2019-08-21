package com.walle.asm;

/**
 * @author: bbpatience
 * @date: 2019/8/7
 * @description: ByteCodeDemo
 **/
public class ByteCodeDemo {
    private static final String name = "hello";

    private int age;

    public ByteCodeDemo(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
    public void setAge(){
        this.age = age;
    }
    public static void main(String[] args) {
        ByteCodeDemo byteCodeDeomo = new ByteCodeDemo(12);
        System.out.println("name:" + name + " age:" + byteCodeDeomo.getAge());
    }
}
