import com.yuan.dao.UserDaoMysqlImpl;
import com.yuan.service.UserService;
import com.yuan.service.UserServiceImpl;

/**
 * Created by yuanlei on 2021/2/21.
 */
public class MyTest_set {
    public static void main(String[] args){
        //测试 set

        //用户实际调用的是业务层，dao层他们不需要接触
        UserService userService = new UserServiceImpl();

        //用户 新增需求，写在了dao层，此需要可能会影响原来的业务层代码，
        // 修改业务层service Userserviceimpl 代码为 set注入 来动态获取值
        //  之前，程序是主动创建对象，控制权在程序员手上，没新增一个就要新增dao层接口实现
        //  现在，使用了set注入，程序不再具有主动性，变成了被动的接受对象
        //从本质上解决问题，程序猿不需要去管理对象的创建
        //原本套路：扩展业务后，修改service层，但是现在只需要专注业务层

        //即控制反转
        ((UserServiceImpl)userService).setUserDao(new UserDaoMysqlImpl());
        userService.getUser();
    }
}
