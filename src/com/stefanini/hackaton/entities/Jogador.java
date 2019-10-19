package com.stefanini.hackaton.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NamedQueries(value = {
		@NamedQuery(name="Jogador.findByNameSenha", query = "SELECT j FROM Jogador j WHERE j.nickname= :nickname AND j.senha =:senha"),
		@NamedQuery(name="Jogador.findByName", query = "SELECT j FROM Jogador j WHERE j.nickname= :nickname"),
		@NamedQuery(name="Jogador.getByIdHeroi", query = "SELECT j FROM Jogador j WHERE j.personagem.id= :idHeroi"),
		@NamedQuery(name="Jogador.deleteById", query = "DELETE FROM Jogador j WHERE j.id = :id")
})


@Entity
@Table(name = "jogador")
public class Jogador implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id	
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private String nickname;
	@NotNull
	private String senha;
	
	@ManyToOne
	@JoinColumn(name="idHeroi")
	private Heroi personagem;
	
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
	public Heroi getPersonagem() {
		return personagem;
	}
	public void setPersonagem(Heroi personagem) {
		this.personagem = personagem;
	}
	
	
	
}
