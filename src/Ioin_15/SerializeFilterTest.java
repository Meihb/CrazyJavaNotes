package Ioin_15;

import java.io.*;


class Person implements Serializable {
    private String name;
    private String gender;
    public transient int age;

    Person(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public String getGender() {
        return gender;
    }
}

class Teacher implements Serializable {
    private Person p;
    public String className;

    public void setP(Person p) {
        this.p = p;
    }

    public Person getP() {
        return p;
    }
}

public class SerializeFilterTest {
    public static void main(String[] args) {
        File txt = new File("filter.txt");
//        txt.deleteOnExit();
        try (

                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(txt));
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(txt))
        ) {
            Teacher t = new Teacher();
            t.className = "语文";
            t.setP(new Person("mhb", "male", 25));
            //序列化
            oos.writeObject(t);
            //设置反序列化筛选规则
            ois.setObjectInputFilter(
                    (info) -> {
                        System.out.println("===执行数据过滤===");
                        ObjectInputFilter serialFilter = ObjectInputFilter.Config.getSerialFilter();
                        System.out.println(serialFilter);
                        if (serialFilter != null) {
                            //执行默认检查
                            ObjectInputFilter.Status status = serialFilter.checkInput(info);
                            System.out.println("status is :" + status);
                            if (status != ObjectInputFilter.Status.UNDECIDED) {
                                return status;
                            }
                        }
                        //自定义规则
                        System.out.println(info.references());
//                        if (info.references() != 1) {//反序列化对象数目不为1
//                            return ObjectInputFilter.Status.REJECTED;
//                        }
                        System.out.println(info.serialClass());
                        if (info.serialClass() != null && (
                                info.serialClass() != Teacher.class &&
                                        info.serialClass() != Person.class
                        )) {//待检测内容class
                            return ObjectInputFilter.Status.REJECTED;
                        }
                        return ObjectInputFilter.Status.UNDECIDED;

                    }
            );


            //执行反序列化
            Teacher t2 = (Teacher) ois.readObject();
            System.out.println(t2.className);
            Person p2 = t2.getP();
            System.out.println(p2.getName());
            System.out.println(p2.age);//transient 0
            System.out.print("sss"+"\r\n");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
