package com.revature.revspace.repositories;

import com.revature.revspace.models.Like;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepo extends CrudRepository<Like, Integer> {

}
