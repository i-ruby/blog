package work.iruby.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import work.iruby.blog.entity.BlogUser;
import work.iruby.blog.mapper.BlogUserMapper;

import java.util.List;

/**
 * @author Ruby
 * @date 2021/1/7 19:27
 */
@Controller
public class AuthController {
    @Autowired
    private BlogUserMapper blogUserMapper;

//    @PostMapping("/auth/register")
//    public String register() {
//        return "";
//    }

    @GetMapping("/")
    @ResponseBody
    public List<BlogUser> listBlogUser() {
        List<BlogUser> blogUsers = blogUserMapper.selectList(null);
        return blogUsers;
    }
}
