package com.iiht.training.datingapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iiht.training.datingapp.entity.Like;
@Repository
public interface LikesRepository extends CrudRepository<Like, Long> {

	public List<Like> findByUserId(Long userId);

	public Like findByUserIdAndLikedUserId(Long userId, Long likedUserId);
}
