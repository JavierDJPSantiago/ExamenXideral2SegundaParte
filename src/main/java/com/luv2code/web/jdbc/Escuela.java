package com.luv2code.web.jdbc;

public class Escuela {

	private int id;
	private String nombre;
	private String tescuela;
	private String correo;

	public Escuela(String nombre, String tescuela, String correo) {
		super();
		this.nombre = nombre;
		this.tescuela = tescuela;
		this.correo = correo;
	}

	public Escuela(int id, String nombre, String tescuela, String correo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tescuela = tescuela;
		this.correo = correo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTescuela() {
		return tescuela;
	}

	public void setTescuela(String tescuela) {
		this.tescuela = tescuela;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}



}
