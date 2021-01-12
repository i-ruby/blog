package work.iruby.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String blogUserId;
    private String title;
    private Boolean atIndex;
    private String description;
    private String content;
    private Instant updatedAt;
    private Instant createdAt;
}
