package com.revature.revspace.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", updatable = false)
    private int postId;

    @OneToOne
    @JoinColumn(name = "creator_id")
    private User creatorId;

    private String body;

    private String image;

    private long date;

    @Column(columnDefinition = "boolean")
    private boolean comment;

    @ManyToOne
    @JoinColumn(name = "parent_post")
    private Post parentPost;

    public Post() {
    }

    public Post(User creatorId, String body, String image, long date, boolean comment, Post parentPost) {
        this.creatorId = creatorId;
        this.body = body;
        this.image = image;
        this.date = date;
        this.comment = comment;
        this.parentPost = parentPost;
    }

    public Post(int postId, User creatorId, String body, String image, long date, boolean comment, Post parentPost) {
        this.postId = postId;
        this.creatorId = creatorId;
        this.body = body;
        this.image = image;
        this.date = date;
        this.comment = comment;
        this.parentPost = parentPost;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public User getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(User creatorId) {
        this.creatorId = creatorId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public Post getParentPost() {
        return parentPost;
    }

    public void setParentPost(Post parentPost) {
        this.parentPost = parentPost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return getPostId() == post.getPostId() && getDate() == post.getDate() && isComment() == post.isComment() && Objects.equals(getCreatorId(), post.getCreatorId()) && Objects.equals(getBody(), post.getBody()) && Objects.equals(getImage(), post.getImage()) && Objects.equals(getParentPost(), post.getParentPost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPostId(), getCreatorId(), getBody(), getImage(), getDate(), isComment(), getParentPost());
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", creatorId=" + creatorId +
                ", body='" + body + '\'' +
                ", image='" + image + '\'' +
                ", date=" + date +
                ", comment=" + comment +
                ", parentPost=" + parentPost +
                '}';
    }
}
