package com.revature.revspace.repositories;

import com.revature.revspace.models.Credentials;
import com.revature.revspace.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends CrudRepository<Post, Integer> {

    List<Post> findByCommentOrderByDateDesc(boolean comment);
    List<Post> findByCommentOrderByDateAsc(boolean comment);

}
