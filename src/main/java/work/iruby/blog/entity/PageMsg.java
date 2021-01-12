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

    public static <T> PageMsg<T> success(String msg, T data, Integer total, Integer page, Integer totalPage) {
        return new PageMsg<>("ok", msg, data, total, page, totalPage);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageMsg{");
        sb.append("total=").append(total);
        sb.append(", page=").append(page);
        sb.append(", totalPage=").append(totalPage);
        sb.append('}');
        return sb.toString();
    }
}
