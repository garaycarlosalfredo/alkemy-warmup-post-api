package com.garay.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garay.backend.entity.Post;
import com.garay.backend.repository.PostRepository;

@Service
public class PostService{
	
	//(GET)
	@Autowired
	PostRepository postRepository;
	
	public Optional<Post> getPostsById(Integer id) {
		return postRepository.findById(id);
	}
	
	public List<Post> getPosts() {
		return postRepository.findAllByOrderByFechaDesc();
	}
	
	public List<Post> getByTitle(String title) {
		return postRepository.findByTituloOrderByFechaDesc(title);
	}
	
	public List<Post> getByCategory(String category) {
		return postRepository.findByCategoriaOrderByFechaDesc(category);
	}
	
	public List<Post> getByTitleAndCategory(String title , String category) {
		return postRepository.findByTituloAndCategoriaOrderByFechaDesc(title, category);
	}

	//(POST)
	public Post addPost(Post post) {
		return postRepository.save(post);
	}
	
	//(PUT)	
	public Post updtePost(Post post) {
		return postRepository.save(post);
	}
	
	//(SOFTDELETE)
	public boolean softDeletePostById(Integer id) {
		try {
			postRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	//(DELETE)
	public boolean deletePostById(Integer id) {
		try {
			postRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
