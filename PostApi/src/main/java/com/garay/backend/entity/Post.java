package com.garay.backend.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonView;
import com.garay.backend.views.PostViews;

import net.bytebuddy.implementation.bind.annotation.Default;


@Entity
@Table(name = "post")
@SQLDelete(sql = "UPDATE post SET deleted=true WHERE id = ?")
@Where(clause = "deleted = false")
public class Post {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Basic(optional = false)
	    @Column(name = "id")
	    @JsonView({PostViews.UserView.FullView.class, PostViews.UserView.ShortView.class})
	    private Integer id;
	    
	    @Basic(optional = false)
	    @Column(name = "titulo")
	    @JsonView({PostViews.UserView.FullView.class, PostViews.UserView.ShortView.class})
	    private String titulo;
	    
	    @Basic(optional = false)
	    @Column(name = "contenido")
	    //@JsonIgnore
	    @JsonView(PostViews.UserView.FullView.class)
	    private String contenido;
	    
	    @Basic(optional = false)
	    @Column(name = "imagen")
	    @JsonView({PostViews.UserView.FullView.class, PostViews.UserView.ShortView.class})
	    private String imagen;
	    
	    @Basic(optional = false)
	    @Column(name = "categoria")
	    @JsonView({PostViews.UserView.FullView.class, PostViews.UserView.ShortView.class})
	    private String categoria;
	    
	    @Basic(optional = false)
	    @Column(name = "fecha")
	    @Temporal(TemporalType.DATE)
	    @JsonView({PostViews.UserView.FullView.class, PostViews.UserView.ShortView.class})
	    private Date fecha;
	    
	    @JoinColumn(name = "user", referencedColumnName = "id")
	    @ManyToOne
	    @JsonView(PostViews.UserView.FullView.class)
	    private Usuario user;
	    

	    @Column(name = "deleted")
	    private Boolean deleted = Boolean.FALSE;

		public Post() {
		}

		public Post(Integer id, String titulo, String contenido, String imagen, String categoria, Date fecha,
				Usuario user) {
			super();
			this.id = id;
			this.titulo = titulo;
			this.contenido = contenido;
			this.imagen = imagen;
			this.categoria = categoria;
			this.fecha = fecha;
			this.user = user;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public String getContenido() {
			return contenido;
		}

		public void setContenido(String contenido) {
			this.contenido = contenido;
		}

		public String getImagen() {
			return imagen;
		}

		public void setImagen(String imagen) {
			this.imagen = imagen;
		}

		public String getCategoria() {
			return categoria;
		}

		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}

		public Date getFecha() {
			return fecha;
		}

		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}

		public Usuario getUser() {
			return user;
		}

		public void setUser(Usuario user) {
			this.user = user;
		}
	    
	    
}