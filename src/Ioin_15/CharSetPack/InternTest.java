package Ioin_15.CharSetPack;

public class InternTest {

    /*
    不一定正确
     */
    public static void main(String[] args) {
        /*
        String c = "ab";//直接赋值,存在常量池中
        String e = c.intern();//常量池地址

        System.out.println(e == c);//true


        String b = new String("ab");//两个地址,一个常量池一个对象地址,==使用的是对象地址
        String f = b.intern();//常量池地址

        System.out.println(b == f);//false
        //常量池地址的intern()是常量地址/对象地址不确定,但根据结果应该是常量池地址类型
        System.out.println(f.intern() == c.intern());//true
        System.out.println(f == c);//true
        */
//        String ab = "ab";
//        String ab2 = "ab";
//        System.out.println(ab.intern()==ab2);
//        System.out.println(ab2.intern()==ab);
        String ab = "ab";
        String h = "a";
        String i = "b";
        String j = h + i;
        System.out.println(j.intern() == j);//false


//        String cd = "cd";
        String c  = "c";
        String d = "d";
        String cd2 = c + d;
        System.out.println(cd2.intern() == cd2);//true
    }
}
