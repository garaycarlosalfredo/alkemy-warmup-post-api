package com.garay.backend.controller;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.annotation.JsonView;
import com.garay.backend.entity.Post;
import com.garay.backend.service.PostService;
import com.garay.backend.views.PostViews;




@RestController
@RequestMapping("/posts")
public class PostController {
	@Autowired
	PostService postService;
			
	
	@GetMapping()
	@JsonView(value = PostViews.UserView.ShortView.class) 
	public List<Post> seleccionarBusqueda(
			@RequestParam(required = false, defaultValue = "") String title,				
			@RequestParam(required = false, defaultValue = "") String category) {
		
		if((!title.isEmpty())&&(!category.isEmpty()))	{return getPostByTitleAndCategory(title,category);}
		if(!title.isEmpty()) 							{return getPostByTitle(title);}				
		if(!category.isEmpty()) 						{return getPostByCategory(category);}	
		
		return getPosts();
	}

	@GetMapping( path = "/{id}") 
	@JsonView(value = PostViews.UserView.FullView.class) 
	public Object getPostById(@PathVariable("id") Integer id) {
		
		Optional<Post> result =  postService.getPostsById(id);	
		
		if (result.isPresent()) {
			return result;
		}else {

			String msg = " { \"msg\" : \"No se encontró nignún post con ese Id\" }";			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
		}

	}
	
	public List<Post> getPosts() {
		return postService.getPosts();		
	}
	
	public List<Post> getPostByTitle(String title) {
		return postService.getByTitle(title);
	}
	
	public List<Post> getPostByCategory(String category) {
		return postService.getByCategory(category);
	}
	
	public List<Post> getPostByTitleAndCategory(String title , String category) {
		return postService.getByTitleAndCategory(title, category);
	}
	
	
	//(POST)
	@PostMapping()
	@JsonView(value = PostViews.UserView.FullView.class) 
	public Object addPost(@RequestBody Post post) {
		if (post.getImagen().endsWith(".jpg") || post.getImagen().endsWith(".png")) {
			return postService.addPost(post);
		}else {
			String msg = " { \"msg\" : \"La imgagen debe ser un formato válido (jpg, png)\" }";			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
		}
	}
	
	//(PATCH);
	@PatchMapping( path = "/{id}") 
	@JsonView(value = PostViews.UserView.FullView.class) 
	public Object updatePatchPostById(@PathVariable("id") Integer id,
									  @RequestBody Post post) {		
		Optional<Post> result =  postService.getPostsById(id);	
		post.setId(id);		
		if (result.isPresent()) {
			return postService.updtePost(post);
		}else {		
			String msg = " { \"msg\" : \"No se encontró nignún post con ese Id\" }";			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
		}
	}
	
	/*
	//(PUT)
	@PutMapping()
	public Post updatePost(@RequestBody Post post) {
		return postService.updtePost(post);
	}*/
	
	//(DELETE)
	@DeleteMapping(path = "/{id}")
	@JsonView(value = PostViews.UserView.FullView.class) 
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		boolean ok = this.postService.softDeletePostById(id);
		String msg = "";
		
		if(ok) {
			msg = " { \"msg\" : \"Se eliminó correctamente el Post con id = " + id + " \" }";
			return ResponseEntity.status(HttpStatus.OK).body(msg);
		}else {
			msg = " { \"msg\" : \"No se pudo eliminar correctamente el Post con id = " + id + " \" }";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
		}			
	}
}