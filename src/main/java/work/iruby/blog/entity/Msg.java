package work.iruby.blog.entity;

/**
 * @author Ruby
 * @date 2021/1/8 15:26
 */
public class Msg extends BaseMsg{
    private String msg;

    public Msg(String status, Object data, String msg) {
        super(status, data);
        this.msg = msg;
    }
}
