import com.yuan.pojo.Student;
import com.yuan.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student.toString());
    }

    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("userbeans.xml");

        User user = context.getBean("user", User.class);
        User user2 = context.getBean("user2", User.class);
        System.out.println("P命名注入："+user.toString()+"  C命名："+user2.toString());
    }
    @Test
    public void test3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("userbeans.xml");
        User user2 = context.getBean("user2", User.class);
        User user2compare = context.getBean("user2", User.class);
        User user22 = context.getBean("user2-2", User.class);
        User user22compare = context.getBean("user2-2", User.class);

        //比较单例模式与原型模式 输出的hash值
        System.out.println(user2.hashCode());
        System.out.println(user2compare.hashCode());
        System.out.println(user22.hashCode());
        System.out.println(user22compare.hashCode());
        //单例模式共用同一个实例
        System.out.println(user2 == user2compare);
        //原型模式每次都创建新实例
        System.out.println(user22 == user22compare);
    }
}














