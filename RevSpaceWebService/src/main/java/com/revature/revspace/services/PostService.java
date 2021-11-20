package com.revature.revspace.services;


import com.revature.revspace.models.Post;
import com.revature.revspace.repositories.PostRepo;

import java.util.List;

public interface PostService extends CrudService<Post, Integer, PostRepo>{

    public List<Post> selectedRelatedComments (Post parentsPost, List<Post> allComments);
}