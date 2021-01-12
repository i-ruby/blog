package work.iruby.blog.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import work.iruby.blog.entity.BaseMsg;
import work.iruby.blog.entity.Blog;
import work.iruby.blog.entity.BlogUser;
import work.iruby.blog.entity.PageMsg;
import work.iruby.blog.mapper.BlogMapper;
import work.iruby.blog.vo.BlogUserVo;
import work.iruby.blog.vo.BlogVo;

import javax.inject.Inject;
import java.time.Instant;
import java.util.List;

/**
 * @author Ruby
 * @date 2021/1/12 12:27
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> {
    private final BlogUserServiceImpl blogUserService;

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
        PageMsg<List<BlogVo>> pageMsg;
        try {
            int count = count(Wrappers.query(blog));
            List<BlogVo> blogPage = getBaseMapper().getBlogPage(atIndex, userId, pageSize, page);
            pageMsg = PageMsg.success("获取成功", blogPage, count, page, (int) Math.ceil(count * 1.0 / pageSize));
        } catch (Exception e) {
            return PageMsg.failure("系统异常");
        }

        return pageMsg;
    }

    public BaseMsg<BlogVo> getBlogById(Integer id) {
        Blog blog = getById(id);
        if (blog == null) {
            return BaseMsg.failure("博客不存在");
        }
        BlogVo blogVo = new BlogVo();
        BeanUtils.copyProperties(blog, blogVo);
        BlogUser blogUser = blogUserService.getById(blog.getBlogUserId());
        BlogUserVo blogUserVo = new BlogUserVo();
        BeanUtils.copyProperties(blogUser, blogUserVo);
        blogVo.setUser(blogUserVo);
        return BaseMsg.success("获取成功", blogVo);
    }

    public BaseMsg<BlogVo> creatBlog(Integer blogUserId, String title, String content, String description) {
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
        return BaseMsg.success("获取成功", getBlogById(blog.getId()).getData());
    }

    public BaseMsg<BlogVo> updateBlog(Integer blogUserId, Integer id, String title, String content, String description, Boolean atIndex) {
        Blog blog = getById(id);
        if (blog == null) {
            return BaseMsg.failure("博客不存在");
        }
        if (!blog.getBlogUserId().equals(blogUserId)) {
            return BaseMsg.failure("无法修改别人的博客");
        }

        blog = new Blog();
        blog.setId(id);
        blog.setTitle(title);
        blog.setContent(content);
        blog.setDescription(description);
        blog.setAtIndex(atIndex);
        blog.setUpdatedAt(Instant.now());
        updateById(blog);
        return BaseMsg.success("修改成功", getBlogById(id).getData());
    }

    public BaseMsg<BlogVo> deleteBlog(Integer blogUserId, Integer blogId) {
        Blog blog = getById(blogId);
        if (blog == null) {
            return BaseMsg.failure("博客不存在");
        }
        if (!blog.getBlogUserId().equals(blogUserId)) {
            return BaseMsg.failure("无法删除别人的博客");
        }
        boolean remove = removeById(blogId);
        if (remove){
            return BaseMsg.success("删除成功",null);
        }else {
            return BaseMsg.failure("没有需要删除的博客");
        }
    }
}
