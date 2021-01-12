package work.iruby.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @author Ruby
 * @date 2021/1/12 12:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogVo {
    private Integer id;
    private String title;
    private String description;
    private String content;
    private BlogUserVo user;
    private Instant updatedAt;
    private Instant createdAt;

}
