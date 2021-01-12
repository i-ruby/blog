package work.iruby.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import work.iruby.blog.entity.Blog;
import work.iruby.blog.vo.BlogVo;

import java.util.List;

/**
 * @author by Ruby
 * @date 2021/1/12 12:26
 */
public interface BlogMapper extends BaseMapper<Blog> {
    List<BlogVo> getBlogPage(Boolean atIndex, Integer blogUserId, Integer limit, Integer offset);
}
