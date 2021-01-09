package work.iruby.blog.entity;

/**
 * @author Ruby
 * @date 2021/1/8 15:17
 */
public class BaseMsg<T> {
    private String status;
    private String msg;
    private T data;

    public static <T> BaseMsg<T> failure(String msg) {
        return new BaseMsg<>("fail", msg, null);
    }

    public static <T> BaseMsg<T> success(String msg, T t) {
        return new BaseMsg<>("ok", msg, t);
    }

    public BaseMsg(String status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
