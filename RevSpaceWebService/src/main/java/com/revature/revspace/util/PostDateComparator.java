package com.revature.revspace.util;

import com.revature.revspace.models.Post;

import java.util.Comparator;

public class PostDateComparator implements Comparator<Post>  {
    @Override
    public int compare(Post o1, Post o2) {
        return Long.compare(o1.getDate(), o2.getDate());
    }
}