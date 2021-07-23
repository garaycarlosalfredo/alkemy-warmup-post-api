package com.garay.backend.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.garay.backend.entity.Usuario;
import com.garay.backend.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
	@Autowired
	UsuarioRepository usuarioRepository;

	//Buscar todos los usuarios (GET)
	public List<Usuario> obtenerUsuarios(){
		return (List<Usuario>) usuarioRepository.findAll();
	}

	//Guardar un usuario (POST)
	public Usuario guardarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	//Actualizar usuario por Id (PUT)
	public Usuario actualizarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	//Eliminar usuario por Id (DELETE)
	public boolean eliminarUsuarioPorId(Integer id) {
		try {
			usuarioRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
/*
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
*/
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {	
		

		Usuario u = usuarioRepository.findByUsuario(userName);
		return new User(u.getUsuario(), u.getPassword(), new ArrayList<>());
	}
}
