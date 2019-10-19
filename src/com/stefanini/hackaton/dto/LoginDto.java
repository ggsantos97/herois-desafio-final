package com.stefanini.hackaton.dto;

public class LoginDto {

	private Integer id;
	private String nickname;
	private String senha;
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public HeroiDto getPersonagem() {
		return personagem;
	}
	public void setPersonagem(HeroiDto personagem) {
		this.personagem = personagem;
	}
	
	
}
