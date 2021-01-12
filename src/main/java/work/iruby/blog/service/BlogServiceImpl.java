package work.iruby.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import work.iruby.blog.entity.Blog;
import work.iruby.blog.entity.BlogUser;
import work.iruby.blog.entity.PageMsg;
import work.iruby.blog.mapper.BlogMapper;
import work.iruby.blog.vo.BlogUserVo;
import work.iruby.blog.vo.BlogVo;

import javax.inject.Inject;
import java.time.Instant;
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

    public PageMsg<List<BlogVo>> getBlogs(Integer page, Integer userId, Boolean atIndex) {
        if (page == null) {
            page = 1;
        }
        // 权益之计
        int pageSize = 2;
        Blog blog = new Blog();
        blog.setAtIndex(atIndex);
        blog.setBlogUserId(userId);
        int count = count(Wrappers.query(blog));
        List<BlogVo> blogPage = getBaseMapper().getBlogPage(atIndex, userId, pageSize, page);
        return PageMsg.success("获取成功", blogPage, count, page, (int) Math.ceil(count * 1.0 / pageSize));
    }

    public BlogVo getBlogById(Integer id) {
        Blog blog = getById(id);
        BlogVo blogVo = new BlogVo();
        BeanUtils.copyProperties(blog, blogVo);
        BlogUser blogUser = blogUserService.getById(blog.getBlogUserId());
        BlogUserVo blogUserVo = new BlogUserVo();
        BeanUtils.copyProperties(blogUser, blogUserVo);
        blogVo.setUser(blogUserVo);
        return blogVo;
    }

    public BlogVo creatBlog(Integer blogUserId, String title, String content, String description) {
        Blog blog = new Blog();
        if (description == null || description.isEmpty()) {
            description = content.substring(0, 1);
        }
        blog.setBlogUserId(blogUserId);
        blog.setTitle(title);
        blog.setContent(content);
        blog.setDescription(description);
        Instant now = Instant.now();
        blog.setCreatedAt(now);
        blog.setUpdatedAt(now);
        save(blog);
        return getBlogById(blog.getId());
    }

    public Object updateBlog(Integer id, String title, String content, String description, Boolean atIndex) {
        return null;
    }
}
