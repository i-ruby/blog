package work.iruby.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author Ruby
 * @date 2021/1/12 18:36
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BlogUserDetail {

    // 没有id属性会查询失败

    private Integer id;
    private BlogUser user;
    private List<Blog> blogs;
}
