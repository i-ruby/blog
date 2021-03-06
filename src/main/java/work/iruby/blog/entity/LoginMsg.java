package work.iruby.blog.entity;

/**
 * @author Ruby
 * @date 2021/1/8 15:27
 */
public class LoginMsg<T> extends BaseMsg<T> {
    private Boolean isLogin;

    public LoginMsg() {
    }

    public LoginMsg(String status, String msg, T data, Boolean isLogin) {
        super(status, msg, data);
        this.isLogin = isLogin;
    }

    public static <T> LoginMsg<T> success(String msg, T t, Boolean isLogin) {
        return new LoginMsg<>("ok", msg, t, isLogin);
    }

    public Boolean getLogin() {
        return isLogin;
    }

    public void setLogin(Boolean login) {
        isLogin = login;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LoginMsg{");
        sb.append("isLogin=").append(isLogin);
        sb.append('}');
        return sb.toString();
    }
}
