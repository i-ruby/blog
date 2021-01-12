package work.iruby.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import work.iruby.blog.entity.BlogUser;

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
    private BlogUser user;
    private String title;
    private Boolean atIndex;
    private String description;
    private String content;
    private Instant updatedAt;
    private Instant createdAt;
}
