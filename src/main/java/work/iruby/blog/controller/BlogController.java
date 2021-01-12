package work.iruby.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import work.iruby.blog.entity.BaseMsg;
import work.iruby.blog.entity.BlogUser;
import work.iruby.blog.entity.LoginMsg;
import work.iruby.blog.service.BlogServiceImpl;
import work.iruby.blog.vo.BlogVo;

import javax.inject.Inject;

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
    public Object getBlogs(@RequestParam(value = "page", required = false) Integer page,
                           @RequestParam(value = "userId", required = false) Integer userId,
                           @RequestParam(value = "atIndex", required = false) Boolean atIndex) {
        if (page == null) {
            page = 1;
        }
        return blogService.getBlogs(page, userId, atIndex);
    }

    @GetMapping("/blog/{id}")
    public BlogVo getBlog(@PathVariable("id") Integer id) {
        return blogService.getBlogById(id);
    }

    @PostMapping("/blog")
    public Object creatBlog(@RequestParam(value = "title ") String title,
                            @RequestParam(value = "content") String content,
                            @RequestParam(value = "description", required = false) String description) {
        LoginMsg<BlogUser> blogUser = authController.getBlogUser();
        if (blogUser.getLogin()) {
            return blogService.creatBlog(blogUser.getData().getId(), title, content, description);
        } else {
            return BaseMsg.failure("登录后才能操作");
        }
    }

    @PostMapping("/blog/{id}")
    public Object updateBlog(
            @PathVariable("id") Integer id,
            @RequestParam(value = "title ", required = false) String title,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "atIndex", required = false) Boolean atIndex) {
        return blogService.updateBlog(id, title, content, description, atIndex);
    }
}
