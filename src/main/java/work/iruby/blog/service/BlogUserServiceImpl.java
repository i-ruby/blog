package work.iruby.blog.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import work.iruby.blog.entity.BlogUser;
import work.iruby.blog.entity.BlogUserDetail;
import work.iruby.blog.mapper.BlogUserMapper;

import javax.inject.Inject;
import java.time.Instant;
import java.util.Collections;

/**
 * @author Ruby
 * @date 2021/1/8 20:36
 */
@Service
public class BlogUserServiceImpl extends ServiceImpl<BlogUserMapper, BlogUser> implements UserDetailsService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Inject
    public BlogUserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BlogUser blogUser = getBlogUserDetail(username);
        if (blogUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new User(username, blogUser.getPassword(), Collections.emptyList());
    }

    public BlogUser getBlogUserDetail(String username) {
        return getOne(Wrappers.query(new BlogUser()).eq("username", username));
    }

    public boolean register(String username, String password) {
        BlogUser blogUser = new BlogUser();
        blogUser.setUsername(username);
        blogUser.setPassword(bCryptPasswordEncoder.encode(password));
        blogUser.setCreatedAt(Instant.now());
        blogUser.setUpdatedAt(Instant.now());
        return save(blogUser);
    }

    public BlogUserDetail getBlogUserDetail() {
        return getBaseMapper().getBlogUserDetail();
    }

}
