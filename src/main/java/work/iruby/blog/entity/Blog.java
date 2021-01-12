package work.iruby.blog.entity;

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
public class Blog {
    private Integer id;
    private Integer blogUserId;
    private String title;
    private Boolean atIndex;
    private String description;
    private String content;
    private Instant updatedAt;
    private Instant createdAt;
}
