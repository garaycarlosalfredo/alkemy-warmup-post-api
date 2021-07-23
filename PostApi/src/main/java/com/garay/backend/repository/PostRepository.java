package com.garay.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.garay.backend.entity.Post;


@Repository
public interface PostRepository extends CrudRepository<Post, Integer>{
	public abstract Optional<Post> findById(Integer id);
	public abstract List<Post> findAllByOrderByFechaDesc();
	public abstract List<Post> findByTituloOrderByFechaDesc(String title);
	public abstract List<Post> findByCategoriaOrderByFechaDesc(String category);
	public abstract List<Post> findByTituloAndCategoriaOrderByFechaDesc(String title, String category);	
	//public abstract List<Post> findByTituloAndCategoriaOrderByFechaDesc(String title, Integer category);
}

