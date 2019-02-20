package com.base;

import java.util.HashSet;

/**
 * 为什么在Set集合中存储对象要重写equals和hashcode方法
 * 1 Set集合是无序且不可重复的，为了达到不可重复，是进行equals和hashcode方法进行判断，
 *  如果不重写对象的这两个方法，那就是继承的Object的这两个方法
 *      public native int hashCode();
 *      equals方法： return (this == obj);
 *  这时候把Set集合中装对象就会出现问题，对象存的是引用地址，如果两个对象的属性相同，equals方法判断还是会返回false
 *   因为地址不同，所以应该在对象中重写equals方法和hashcode方法
 */
public class Demo1 {
    public static void main(String[] args) {
        new Object();
        new HashSet<String>();
    }
}
