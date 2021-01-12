package work.iruby.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import work.iruby.blog.entity.BlogUser;
import work.iruby.blog.entity.BlogUserDetail;

/**
 * @author Ruby
 * @date 2021/1/8 15:37
 */
public interface BlogUserMapper extends BaseMapper<BlogUser> {
    BlogUserDetail getBlogUserDetail();
}
