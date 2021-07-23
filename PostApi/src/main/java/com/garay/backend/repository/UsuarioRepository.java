package com.garay.backend.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.garay.backend.entity.Usuario;


@Repository
public interface UsuarioRepository extends CrudRepository <Usuario, Integer>{
	public abstract Optional<Usuario> findById(Integer id);
	public abstract Usuario findByUsuario(String usuario);
}
