public class StringCompareTest {
    /*
    常量池(constant_pool)专门用于管理在编译时就可以确定下来并保存在已编译的.class文件中的一些数据,
    它包括了类、方法、接口常量以及字符串常量
     */
    public static void main(String[] args) {
        //s1直接使用常量池
        String s1 = "abcd";
        String s2 = "ab";
        String s3 = "cd";
        //s4及以后的的字符串值在编译时可以确定，故直接使用常量池
        String s4 = "ab" + "cd";
        String s5 = "a" + "b" + "cd";
        //s6不能再编译时确定下来
        String s6 = s2 + s3;
        //使用new 创建String时,会先将当前内容保存到常量池(若不存在),再创建String对象
        String s7 = new String("abcd");

        System.out.println(s1==s4);//true
        System.out.println(s1==s5);//true

        System.out.println(s1==s6);//false

        System.out.println(s1==s7);//false
        System.out.println(s7.intern()==s1);//true


        //以下在jdk1.7以后,常量池不仅可以存储对象,还可以存储对象的引用
        String ab = "ab";
        String h = "a";
        String i = "b";
        String j = h + i;
        //j.interrn()是ab
        System.out.println(j.intern() == j);//false


//        String cd = "cd";
        String c  = "c";
        String d = "d";
        String cd2 = c + d;
        //cd2.intern()时无常量池对象，故存储的是他自己的引用
        System.out.println(cd2.intern() == cd2);//true
    }
}
