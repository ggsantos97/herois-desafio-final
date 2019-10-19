package com.stefanini.hackaton.parsers;

import com.stefanini.hackaton.dto.HeroiDto;
import com.stefanini.hackaton.dto.JogadorDTO;
import com.stefanini.hackaton.entities.Jogador;

public class JogadorParserDTO extends AbstractParser<JogadorDTO, Jogador>{

	@Override
	public JogadorDTO toDTO(Jogador entity) {
		JogadorDTO Jdto = new JogadorDTO();
		HeroiDto heroiDto = new HeroiDto();
		
		heroiDto.setAtaque(entity.getPersonagem().getAtaque());
		heroiDto.setDefesa(entity.getPersonagem().getDefesa());
		heroiDto.setForca(entity.getPersonagem().getForca());
		heroiDto.setId(entity.getPersonagem().getId());
		heroiDto.setInteligencia(entity.getPersonagem().getInteligencia());
		heroiDto.setNome(entity.getPersonagem().getNome());
		heroiDto.setPoder(entity.getPersonagem().getPoder());
		heroiDto.setVelocidade(entity.getPersonagem().getVelocidade());
		heroiDto.setVida(entity.getPersonagem().getVida());
		//HeroiParserDTO heroiParser = new HeroiParserDTO();	
		
		Jdto.setId(entity.getId());
		Jdto.setNickname(entity.getNickname());
		Jdto.setPersonagem(heroiDto);
		
		return Jdto;
	}

	@Override
	public Jogador toEntity(JogadorDTO dto) {
		Jogador entity = new Jogador();
		entity.setId(dto.getId());
		entity.setNickname(dto.getNickname());
		
		return entity;
	}

}
