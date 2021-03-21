import com.yuan.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yuanlei on 2021/2/21.
 */
public class MyTest {
    public static void main(String [] args){
        // Spring容器
        // 创建并实例化beans
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 检索配置的实例
        //getBean 的时候已经取出了对象，用的无参构造
        //注意 ： 这一步会取出beans.xml的所有无参构造，输出实例化的构造
        User user = (User) context.getBean("beans2user3");
        user.show();
    }
}
