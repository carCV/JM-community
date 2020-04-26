package com.jmlee.community;

import com.jmlee.community.dao.DiscussPostMapper;
import com.jmlee.community.dao.UserMapper;
import com.jmlee.community.entity.DiscussPost;
import com.jmlee.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void testSelectUser() {
        User user = userMapper.selectById(101);
        System.out.println(user);

        User user2 = userMapper.selectByName("liubei");
        System.out.println(user2);

        User user3 = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user3);
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("test@163.com");
        user.setHeaderUrl("http://www.nowcoder.com/102.png");
        user.setCreateTime(new Date());
        int rows = userMapper.insertUser(user);

        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void testUpdateUser() {
        int rows = userMapper.updateStatus(150, 1);
        System.out.println(rows);

        rows = userMapper.updateHeader(150,"http://www.nowcoder.com/103.png");
        System.out.println(rows);

        rows = userMapper.updatePassword(150, "helloNew");
        System.out.println(rows);
    }

    @Test
    public void testSelectDiscussPost() {

        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPost(0, 0, 10);
        discussPosts.forEach(System.out::println);

        Integer rows = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(rows);


    }

}
