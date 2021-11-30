package com.revature.revspace.services;

import com.revature.revspace.models.Like;
import com.revature.revspace.repositories.LikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService{

    @Autowired
    LikeRepo likeRepo;

    @Override
    public LikeRepo getRepo() {
        return likeRepo;
    }

    @Override
    public Integer getIDFor(Like value) {
        return value.getLikeId();
    }


}
