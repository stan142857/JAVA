import com.yuan.pojo.Hello;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yuanlei on 2021/2/21.
 */
public class MyTest {
    public static void main(String [] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Hello hello = (Hello) context.getBean("Hello");
        System.out.println(hello.toString());
    }
}
