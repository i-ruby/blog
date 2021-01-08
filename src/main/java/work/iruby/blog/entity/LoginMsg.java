package work.iruby.blog.entity;

/**
 * @author Ruby
 * @date 2021/1/8 15:27
 */
public class LoginMsg extends BaseMsg<BlogUser>{
    private Boolean isLogin;

    public LoginMsg(String status, BlogUser data, Boolean isLogin) {
        super(status, data);
        this.isLogin = isLogin;
    }

}
