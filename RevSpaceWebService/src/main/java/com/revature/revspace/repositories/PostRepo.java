package com.revature.revspace.repositories;

import com.revature.revspace.models.Credentials;
import com.revature.revspace.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends CrudRepository<Post, Integer> {
    List<Post> findByCommentFalseOrderByDateDesc();
    List<Post> findByCommentTrueOrderByDateAsc();
    int findFirstByCommentFalseOrderByPostIdDesc();
    int findFirstByCommentTrueOrderByPostIdAsc();
    List<Post> findByCommentFalseAndPostIdGreaterThanOrderByDateDesc(int biggestCommentIdJava);
    List<Post> findByCommentTrueAndPostIdGreaterThanOrderByDateAsc(int biggestPostIdJava);
}
