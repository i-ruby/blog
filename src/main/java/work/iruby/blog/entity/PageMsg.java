package work.iruby.blog.entity;

/**
 * @author Ruby
 * @date 2021/1/12 12:46
 */
public class PageMsg<T> extends BaseMsg<T> {
    Integer total;
    Integer page;
    Integer totalPage;

    public PageMsg() {
    }

    public PageMsg(String status, String msg, T data, Integer total, Integer page, Integer totalPage) {
        super(status, msg, data);
        this.total = total;
        this.page = page;
        this.totalPage = totalPage;
    }
}
