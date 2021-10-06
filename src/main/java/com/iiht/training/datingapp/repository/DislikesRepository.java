package com.iiht.training.datingapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iiht.training.datingapp.entity.Dislike;

@Repository
public interface DislikesRepository extends CrudRepository<Dislike, Long> {

	public List<Dislike> findByUserId(Long userId);
}