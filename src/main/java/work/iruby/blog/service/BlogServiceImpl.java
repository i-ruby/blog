package work.iruby.blog.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import work.iruby.blog.entity.Blog;
import work.iruby.blog.mapper.BlogMapper;
import work.iruby.blog.vo.BlogVo;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruby
 * @date 2021/1/12 12:27
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> {
    private BlogUserServiceImpl blogUserService;

    @Inject
    public BlogServiceImpl(BlogUserServiceImpl blogUserService) {
        this.blogUserService = blogUserService;
    }

    public List<BlogVo> getBlogs(Integer page, Integer userId, Boolean atIndex) {
        List<BlogVo> blogVoList = new ArrayList<>();
        BeanUtils.copyProperties(list(), blogVoList);
        return blogVoList;
    }

    public BlogVo getBlogById(Integer id) {
        Blog blog = getById(id);
        BlogVo blogVo = new BlogVo();
        BeanUtils.copyProperties(blog, blogVo);
        blogVo.setUser(blogUserService.getById(blog.getBlogUserId()));
        return blogVo;
    }

    public Object creatBlog(String title, String content, String description) {
        return null;
    }

    public Object updateBlog(Integer id, String title, String content, String description, Boolean atIndex) {
        return null;
    }
}
