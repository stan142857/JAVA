import com.yuan.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yuanlei on 2021/2/21.
 */
public class MyTest_bean {
    public static void main(String[] args){
        //测试 bean
        //获取ApplicationContext : 拿到Spring的容器
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //用容器get需要的服务层的类，相当于在 set 方法上又加了一层配置，以后的代码只需要配置xml，其他文件无需修改
        UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean("UserServiceImpl");

        userServiceImpl.getUser();
    }
}
