package com.garay.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.garay.backend.entity.Usuario;
import com.garay.backend.service.UsuarioService;
import com.garay.backend.views.PostViews;

@RestController

@RequestMapping()
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;
	
	
	@PostMapping("/auth/sign_up")
	@JsonView(value = PostViews.UserView.ConfirmUser.class) 
	public Object guardarUsuario(@RequestBody Usuario usuario) {
		try {
			return usuarioService.guardarUsuario(usuario);
			
		} catch (Exception e) {
			// TODO: handle exception
			return "Error, no se pudo crear el usuario " + usuario.getUsuario();
		}
	}	
}
