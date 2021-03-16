import com.yuan.mapper.UserMapper;
import com.yuan.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest {
    @Test
    public void test() throws IOException {
        String resource = "mybatis-config.xml";
        //Resources 加载配置文件  赋给数据量读取
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        //获取session对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession(true);
        //执行sql
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.selectUser();

        for (User user:userList){
            System.out.println(user);
        }
        sqlSession.close();
    }
}
