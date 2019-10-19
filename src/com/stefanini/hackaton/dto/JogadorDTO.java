package com.stefanini.hackaton.dto;

import java.io.Serializable;

public class JogadorDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nickname;
	private HeroiDto personagem;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public HeroiDto getPersonagem() {
		return personagem;
	}
	public void setPersonagem(HeroiDto personagem) {
		this.personagem = personagem;
	}
	
}
