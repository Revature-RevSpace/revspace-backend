package com.revature.revspace.models;

import javax.persistence.*;

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




}
