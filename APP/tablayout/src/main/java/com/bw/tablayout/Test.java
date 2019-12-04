package com.bw.tablayout;

/**
 * @name APP
 * @class name：com.bw.tablayout
 * @class describe
 * @anthor 24673
 * @time 2019/12/2 15:42
 * @change
 * @chang time
 * @class describe
 */
public class Test {
    public static void main(String[] args) {
        String a = new String("a");
        String b = new String("b");
        String aa = a;
        String bb = b;

        System.out.println("a==aa" + a.equals(aa));
        System.out.println("b==bb" + b.equals(bb));
        System.out.println("aa====a" + a == aa);
        System.out.println("bb====b" + b == bb);

        StringBuilder builder = new StringBuilder("abc");
        StringBuilder replace = builder.replace(0, 1, "---Android---------");
        StringBuffer buffer = new StringBuffer("abc");
        StringBuffer replace1 = buffer.replace(0, 1, "---JAVA-------------");
        System.out.println(replace);
        System.out.println(replace1);
    }
}
