package com.revature.revspace.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="likes")
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id", updatable = false)
    private int likeId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post postId;

    public Likes() {
    }

    public Likes(User userId, Post postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public Likes(int likeId, User userId, Post postId) {
        this.likeId = likeId;
        this.userId = userId;
        this.postId = postId;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Post getPostId() {
        return postId;
    }

    public void setPostId(Post postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Likes)) return false;
        Likes likes = (Likes) o;
        return getLikeId() == likes.getLikeId() && Objects.equals(getUserId(), likes.getUserId()) && Objects.equals(getPostId(), likes.getPostId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLikeId(), getUserId(), getPostId());
    }

    @Override
    public String toString() {
        return "Likes{" +
                "likeId=" + likeId +
                ", userId=" + userId +
                ", postId=" + postId +
                '}';
    }
}
