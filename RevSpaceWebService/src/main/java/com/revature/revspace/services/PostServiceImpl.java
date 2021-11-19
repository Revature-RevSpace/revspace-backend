package com.revature.revspace.services;

import com.revature.revspace.models.Post;
import com.revature.revspace.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepo postRepo;

    //The larger the unix time, the newer the post.
//    static List<Post> sortedCurrentPostsList = new ArrayList<>();       // Posts are in descending order
//    static List<Post> sortedCurrentCommentsList = new ArrayList<>();   // Comments are in ascending order


    @Override
    public PostRepo getRepo(){
        return postRepo;
    }

    @Override
    public Integer getIDFor(Post value) {
        return null;
    }

    public void pullPostsList(int lastPostIdOnThePage, int firstPostIdOnThePage){

        List<Post> sortedCurrentPostsList = postRepo.findByCommentFalseOrderByDateDesc();
        List<Post> sortedCurrentCommentsList = postRepo.findByCommentTrueOrderByDateAsc();
        List<Post> resposePostsList = new ArrayList<>();
        List<Post> resposeCommentsList = new ArrayList<>();

        if(lastPostIdOnThePage == 0) {
            resposePostsList = sortedCurrentPostsList.subList(0, 9);
            for (Post post : resposePostsList) {
                resposeCommentsList.addAll(
                        selectedRelatedComments(post, sortedCurrentCommentsList));
            }
        }


    }

//    public void updatePostsList(boolean comment){
//        if(comment){
//            int biggestCommentIdJava = sortedCurrentCommentsList.get(sortedCurrentCommentsList.size() - 1).getPostId();
//            int biggestCommentIdDatabase = postRepo.findFirstByCommentTrueOrderByPostIdAsc();
//            if (biggestCommentIdJava != biggestCommentIdDatabase){
//                sortedCurrentCommentsList.addAll(postRepo.findByCommentTrueAndPostIdGreaterThanOrderByDateAsc(biggestCommentIdJava));
//            }
//        }else {
//            int biggestPostIdJava = sortedCurrentPostsList.get(sortedCurrentPostsList.size() - 1).getPostId();
//
//            if (biggestPostIdJava != biggestCommentIdDatabase){
//                sortedCurrentPostsList.addAll(0, postRepo.findByCommentFalseAndPostIdGreaterThanOrderByDateDesc(biggestPostIdJava));
//            }
//        }
//    }

//    public List<Post> selectedRelatedComments (int parentsPostId, List<Post> allComments){
//    public List<Post> selectedRelatedComments (Post parentsPost, List<Post> allComments){
//        List<Post> childrenComments = new ArrayList<>();
//        for (Post comment : allComments) {
//            if (parentsPost == comment.getParentPost()) {
//                childrenComments.add(comment);
//            }
//        }
//        if (childrenComments.size() == 0) { return null; }
//        else {
//            for (Post comment : childrenComments) {
//                 selectedRelatedComments(comment, allComments);
//            }
//        }
//        return parentsList.addAll(childrenComments);
//    }





    public List<Post> selectedRelatedComments (Post parentsPost, List<Post> allComments){
        List<Post> childrenComments = new ArrayList<>();
        for (Post comment : allComments) {
            if (parentsPost == comment.getParentPost()) {
                childrenComments.add(comment);
            }
        }
        List<Post> childrenOfChildren = new ArrayList<>();
        for(Post parentsComment : childrenComments){
            childrenOfChildren.addAll(selectedRelatedComments(parentsComment, allComments));
        }
        childrenComments.addAll(childrenOfChildren);
        return childrenComments;
    }
}
