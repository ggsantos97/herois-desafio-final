package com.stefanini.hackaton.service;

import java.util.Base64;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import com.stefanini.hackaton.dto.JogadorDTO;
import com.stefanini.hackaton.dto.LoginDto;
import com.stefanini.hackaton.entities.Heroi;
import com.stefanini.hackaton.entities.Jogador;
import com.stefanini.hackaton.parsers.HeroiParserDTO;
import com.stefanini.hackaton.parsers.JogadorParserDTO;
import com.stefanini.hackaton.parsers.LoginParserDTO;
import com.stefanini.hackaton.persistence.JogadorDAO;
import com.stefanini.hackaton.rest.exceptions.NegocioException;


public class JogadorService {
	
	@Inject 
	private LoginParserDTO lparser;
	@Inject
	private JogadorParserDTO parser;

	@Inject
	private HeroiParserDTO parserHeroi;
	
	@Inject
	private JogadorDAO jogadorDao;
	
			@Transactional
			public void save(LoginDto dto) throws NegocioException{
					
				try {
					if (jogadorDao.findByName(dto.getNickname()) != null) {						
						throw new NegocioException("Este Nickname já existe");										
					}
				} catch (NoResultException e) {
						jogadorDao.insert(lparser.toEntity(dto));
						throw new NegocioException("Cadastro realizado com sucesso!!");			
				}
				}

			
			
			public List<JogadorDTO> findAll() {
				JogadorDTO dto= new JogadorDTO();
				Heroi personagem = new Heroi();
				dto.setPersonagem(parserHeroi.toDTO(personagem));
				
				return parser.toDTO(jogadorDao.list());
				//return jogadorDao.list();
			}
			
			public Jogador findById(Integer id) {	
				return jogadorDao.findById(id);
			}
			
			public JogadorDTO login(LoginDto dto) throws NegocioException{
			Jogador jogador = new Jogador();
				
				String encodedString = Base64.getEncoder().encodeToString(dto.getSenha().getBytes());
				dto.setSenha(encodedString);
			//		Base64.getDecoder().decode();
				try {
					if(jogadorDao.findByNameSenha(dto.getNickname(), dto.getSenha()) != null);
					jogador = jogadorDao.findByNameSenha(dto.getNickname(), dto.getSenha());	
					
					byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
					String decodedString = new String(decodedBytes);
					jogador.setSenha(decodedString);
					return parser.toDTO(jogador);
				} catch (NoResultException e) {
					throw new NegocioException("\"Ops! Seu nickname ou senha estão incorretos!\"\r\n" + 
							"");	
				}
				
				
				
}
}
