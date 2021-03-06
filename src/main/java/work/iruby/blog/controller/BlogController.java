package work.iruby.blog.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import work.iruby.blog.entity.BaseMsg;
import work.iruby.blog.entity.BlogUser;
import work.iruby.blog.entity.LoginMsg;
import work.iruby.blog.entity.PageMsg;
import work.iruby.blog.service.BlogServiceImpl;
import work.iruby.blog.vo.BlogVo;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Ruby
 * @date 2021/1/12 12:30
 */
@RestController
public class BlogController {
    private final BlogServiceImpl blogService;
    private final AuthController authController;

    @Inject
    public BlogController(BlogServiceImpl blogService, AuthController authController) {
        this.blogService = blogService;
        this.authController = authController;
    }

    @GetMapping("/blog")
    public PageMsg<List<BlogVo>> getBlogs(@RequestParam(value = "page", required = false) Integer page,
                                          @RequestParam(value = "userId", required = false) Integer userId,
                                          @RequestParam(value = "atIndex", required = false) Boolean atIndex) {
        if (page == null) {
            page = 1;
        }
        return blogService.getBlogs(page, userId, atIndex);
    }

    @GetMapping("/blog/{id}")
    public BaseMsg<BlogVo> getBlog(@PathVariable("id") Integer id) {
        return blogService.getBlogById(id);
    }

    @PostMapping("/blog")
    public BaseMsg<BlogVo> creatBlog(@RequestParam(value = "title ") String title,
                                     @RequestParam(value = "content") String content,
                                     @RequestParam(value = "description", required = false) String description) {
        LoginMsg<BlogUser> blogUser = authController.getBlogUser();
        if (blogUser.getLogin()) {
            return blogService.creatBlog(blogUser.getData().getId(), title, content, description);
        } else {
            return BaseMsg.failure("登录后才能操作");
        }
    }

    @PatchMapping("/blog/{id}")
    public BaseMsg<BlogVo> updateBlog(
            @PathVariable("id") Integer id,
            @RequestParam(value = "title ", required = false) String title,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "atIndex", required = false) Boolean atIndex) {
        LoginMsg<BlogUser> blogUser = authController.getBlogUser();
        if (!blogUser.getLogin()) {
            return BaseMsg.failure("登录后才能操作");
        } else {
            return blogService.updateBlog(blogUser.getData().getId(), id, title, content, description, atIndex);
        }
    }

    @DeleteMapping("/blog/{id}")
    public BaseMsg<BlogVo> deleteBlog(
            @PathVariable("id") Integer id) {
        LoginMsg<BlogUser> blogUser = authController.getBlogUser();
        if (!blogUser.getLogin()) {
            return BaseMsg.failure("登录后才能操作");
        } else {
            return blogService.deleteBlog(blogUser.getData().getId(), id);
        }
    }
}
