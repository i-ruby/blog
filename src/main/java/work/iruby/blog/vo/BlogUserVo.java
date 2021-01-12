package work.iruby.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ruby
 * @date 2021/1/12 14:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogUserVo {
    private Integer id;
    private String username;
    private String avatar;
}
