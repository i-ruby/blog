package work.iruby.blog.controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import work.iruby.blog.entity.BaseMsg;
import work.iruby.blog.entity.BlogUser;
import work.iruby.blog.entity.LoginMsg;
import work.iruby.blog.service.BlogUserServiceImpl;

import javax.inject.Inject;

/**
 * @author Ruby
 * @date 2021/1/7 19:27
 */
@Controller
public class AuthController {
    private final String ANONYMOUS_USER = "anonymousUser";
    private final BlogUserServiceImpl blogUserService;
    private final AuthenticationManager authenticationManager;

    @Inject
    public AuthController(BlogUserServiceImpl blogUserService, AuthenticationManager authenticationManager) {
        this.blogUserService = blogUserService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/auth")
    @ResponseBody
    public Object auth() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (ANONYMOUS_USER.equals(username)) {
            return LoginMsg.success(null, null, false);
        }
        return LoginMsg.success(null, blogUserService.getBlogUserDetail(username), true);
    }

    @PostMapping("/auth/register")
    @ResponseBody
    public Object register(@RequestBody ModelMap model) {
        String username = (String) model.get("username");
        String password = (String) model.get("password");
        if (username == null || username.isEmpty()) {
            return BaseMsg.failure("用户名不得为空");
        }
        if (username.length() > 15) {
            return BaseMsg.failure("用户名过长");
        }
        if (password.length() > 16 || password.length() < 6) {
            return BaseMsg.failure("密码长度只能为6到16个字符");
        }
        try {
            if (blogUserService.register(username, password)) {
                return BaseMsg.success("注册成功", blogUserService.getBlogUserDetail(username));
            }
        } catch (DuplicateKeyException e) {
            return BaseMsg.failure("用户名已存在");
        }
        return BaseMsg.failure("注册失败");
    }

    @PostMapping("/auth/login")
    @ResponseBody
    public Object login(@RequestBody ModelMap model) {
        String username = (String) model.get("username");
        String password = (String) model.get("password");
        BaseMsg<BlogUser> msg;
        try {
            UserDetails userDetails = blogUserService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
            authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(token);
            msg = BaseMsg.success("登录成功", blogUserService.getBlogUserDetail(username));
        } catch (UsernameNotFoundException e) {
            return BaseMsg.failure("用户不存在");
        } catch (BadCredentialsException e) {
            return BaseMsg.failure("密码不正确");
        }
        return msg;
    }

    @GetMapping("/auth/logout")
    @ResponseBody
    public Object logout() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (ANONYMOUS_USER.equals(username)) {
            return BaseMsg.failure("用户尚未登录");
        }
        SecurityContextHolder.clearContext();
        return BaseMsg.success("注销成功", null);
    }

}
