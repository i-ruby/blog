package work.iruby.blog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import work.iruby.blog.entity.BlogUserDetail;
import work.iruby.blog.entity.PageMsg;
import work.iruby.blog.vo.BlogVo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BlogServiceImplTest {
    @Autowired
    private BlogServiceImpl blogService;
    @Autowired
    private BlogUserServiceImpl blogUserService;

    @Test
    void getBlogs(){
        PageMsg<List<BlogVo>> blogs = blogService.getBlogs(1, 1, null);
        System.out.println(blogs);
        blogs = blogService.getBlogs(2, null, null);
        System.out.println(blogs);
        blogs = blogService.getBlogs(1, 2, null);
        System.out.println(blogs);
        blogs = blogService.getBlogs(1, 1, true);
        System.out.println(blogs);
    }

    @Test
    void getBlogUserDetail(){
        BlogUserDetail blogUserDetail = blogUserService.getBlogUserDetail();
        System.out.println(blogUserDetail);
    }
}