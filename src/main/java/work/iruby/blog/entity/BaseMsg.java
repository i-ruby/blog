package work.iruby.blog.entity;

/**
 * @author Ruby
 * @date 2021/1/8 15:17
 */
public class BaseMsg<T> {
    private String status;
    private T data;

    public BaseMsg(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
