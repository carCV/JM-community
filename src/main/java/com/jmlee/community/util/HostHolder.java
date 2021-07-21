package com.jmlee.community.util;

import com.jmlee.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * 持有用户信息，方便进行上下文共享User数据
 * @author jmLee
 */
@Component
public class HostHolder {

    ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user) {
        users.set(user);
    }

    public User getUser() {
       return users.get();
    }

    // 手动清除，防止内存泄漏（弱引用 WeakReference）
    public void clear() {
        users.remove();
    }
}
