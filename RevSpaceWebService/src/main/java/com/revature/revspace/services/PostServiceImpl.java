package com.revature.revspace.services;

import com.revature.revspace.models.Like;
import com.revature.revspace.models.Post;
import com.revature.revspace.repositories.LikeRepo;
import com.revature.revspace.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.revspace.util.PostDateComparator;

import java.util.*;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    LikeRepo likeRepo;


    @Override
    public PostRepo getRepo() {
        return postRepo;
    }

    @Override
    public Integer getIDFor(Post value) {
        return null;
    }

    public List<List<Post>> pullPostsList(int lastPostIdOnThePage){

        List<Post> sortedCurrentPostsList = postRepo.findByCommentFalseOrderByDateDesc();
        List<Post> sortedCurrentCommentsList = postRepo.findByCommentTrueOrderByDateAsc();
        List<Like> currentLikesList = (List<Like>) likeRepo.findAll();
        List<Post> responsePostsList = new ArrayList<>();
        List<Post> responseCommentsList = new ArrayList<>();
        List<Post> responseLikesList = new ArrayList<>();

        if (lastPostIdOnThePage == 0) {

            if (10 < sortedCurrentPostsList.size()) {

                responsePostsList = sortedCurrentPostsList.subList(0, 10);

            } else {

                responsePostsList = sortedCurrentPostsList.subList(0, sortedCurrentPostsList.size());
            }
        } else {
            for (Post post : sortedCurrentPostsList) {
                if (post.getPostId() == lastPostIdOnThePage) {
                    int index = sortedCurrentPostsList.indexOf(post);

                    if((index + 11) < sortedCurrentPostsList.size()){
                        responsePostsList = sortedCurrentPostsList.subList(index + 1, index + 11);
                    }else {
                        responsePostsList = sortedCurrentPostsList.subList(index + 1, sortedCurrentPostsList.size());
                    }
                }
            }
        }
        for (Post post : responsePostsList) {
            responseCommentsList.addAll(
                    selectedRelatedComments(post, sortedCurrentCommentsList));
        }
        responseCommentsList.sort(new PostDateComparator());
        for (Like like : currentLikesList) {
            for (Post post : responsePostsList) {
                if (post.getPostId() == like.getPostId().getPostId()) {
                    boolean notInList = true;
                    for (Post likePost : responseLikesList) {
                        if (likePost.getPostId() == post.getPostId()) {
                            likePost.setDate(likePost.getDate() + 1);
                            notInList = false;
                            break;
                        }
                    }
                    if (notInList) {
                        Post p = new Post();
                        p.setDate(1);
                        p.setPostId(post.getPostId());
                        responseLikesList.add(p);
                    }
                }
            }
            for (Post comment : responseCommentsList) {
                if (comment.getPostId() == like.getPostId().getPostId()) {
                    boolean notInList = true;
                    for (Post likePost : responseLikesList) {
                        if (likePost.getPostId() == comment.getPostId()) {
                            likePost.setDate(likePost.getDate() + 1);
                            notInList = false;
                            break;
                        }
                    }
                    if (notInList) {
                        Post p = new Post();
                        p.setDate(1);
                        p.setPostId(comment.getPostId());
                        responseLikesList.add(p);
                    }
                }
            }
        }
        List<List<Post>> response = new ArrayList<>();

        response.add(responsePostsList);
        response.add(responseCommentsList);
        response.add(responseLikesList);
        return response;
    }
    
    public List<Post> selectedRelatedComments(Post parentsPost, List<Post> allComments) {
        List<Post> childrenComments = new ArrayList<>();
        for (Post comment : allComments) {
            if (parentsPost == comment.getParentPost()) {
                childrenComments.add(comment);
            }
        }
        allComments.removeAll(childrenComments);
        List<Post> childrenOfChildren = new ArrayList<>();
        for (Post parentsComment : childrenComments) {
            childrenOfChildren.addAll(selectedRelatedComments(parentsComment, allComments));
        }
        childrenComments.addAll(childrenOfChildren);
        return childrenComments;
    }

//    public List<Post> selectedRelatedCommentsFaster (Post parentsPost, List<Post> allComments){
//        List<Post> childrenComments = new ArrayList<>();
//        for (Post comment : allComments) {
//            if (parentsPost == comment.getParentPost()) {
//                childrenComments.add(comment);
//                allComments.remove(comment);
//            }
//        }
//        List<Post> childrenOfChildren = new ArrayList<>();
//        for(Post parentsComment : childrenComments){
//            childrenOfChildren.addAll(selectedRelatedComments(parentsComment, allComments));
//        }
//        childrenComments.addAll(childrenOfChildren);
//        return childrenComments;
//    }

}
