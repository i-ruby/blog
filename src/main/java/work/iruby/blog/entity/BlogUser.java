package work.iruby.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Instant;

/**
 * @author Ruby
 * @date 2021/1/8 15:20
 */
public class BlogUser {
    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    private String avatar;
    private Instant createdAt;
    private Instant updatedAt;

    public BlogUser() {
    }

    public BlogUser(Integer id, String username, String password, String avatar, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
