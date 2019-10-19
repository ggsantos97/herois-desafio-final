package com.stefanini.hackaton.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import com.stefanini.hackaton.dto.HeroiDto;
import com.stefanini.hackaton.dto.JogadorDTO;
import com.stefanini.hackaton.entities.Heroi;
import com.stefanini.hackaton.parsers.HeroiParserDTO;
import com.stefanini.hackaton.parsers.JogadorParserDTO;
import com.stefanini.hackaton.persistence.HeroiDAO;
import com.stefanini.hackaton.persistence.JogadorDAO;

public class BatalhaService {

	@Inject
	HeroiParserDTO parser;
	
	@Inject
	HeroiDAO heroiDao;
	
	@Inject
	JogadorDAO jDAO;
	
	
	List<String> log = new ArrayList<>();
	
	// pega heroi aleatório pra lutar na single play
	public Heroi findById(Integer id) {
		return heroiDao.findById(id);
	}
	
	public String batalhar(Integer id1, Integer id2) {
		//falta lógica pra embaralhar pra qual id vai primeiro no path param
		
		HeroiDto maquina = new HeroiDto();
		HeroiDto jogador = new HeroiDto();
		String vencedor = null;
		
		Random attAleatorio = new Random();
		if(id2 == 0) id2=attAleatorio.nextInt(249); 
		
		jogador= parser.toDTO(heroiDao.findById(id1));
		maquina = parser.toDTO(heroiDao.findById(id2)) ;
		 
		
		int rodada =0;
		log.add("Batalha entre "+ jogador.getNome() + " e "+ maquina.getNome());
		while (jogador.getVida() > 1500000 || maquina.getVida() > 1500000) {
			rodada++;
			log.add("===================="+"Rodada "+ rodada + " ========================");
			if(jogador.getAtaque() > maquina.getAtaque() || jogador.getDefesa() > maquina.getDefesa()) {
				jogador.setForca(attAleatorio.nextInt(150));
				jogador.setAtaque(attAleatorio.nextInt(150));
				
				maquina.setVida(maquina.getVida() - (jogador.combo()+100000));
				maquina.setVida((maquina.getVida() - (jogador.atacar() + 50000)));
				
				
				log.add( jogador.getNome() + " ATACOU " + maquina.getNome()+" com o COMBO do HADOOP" );
			}else {
				//fica mais forte ou mais fraco 
				
				maquina.setAtaque(attAleatorio.nextInt(150));
				maquina.setForca(attAleatorio.nextInt(150));
				
				//faz o ataque 
				jogador.setVida(jogador.getVida() - maquina.combo()+150000);
				jogador.setVida((jogador.getVida() - (maquina.atacar() + 150000)));
				
				log.add( maquina.getNome() + " ATACOU" + jogador.getNome() + " com o Ataque JS ");
				
			}
			
			// se heroi tiver mais inteligencia e menos força do que o outro, ganha vantagem
			if ((maquina.getInteligencia() >= jogador.getInteligencia() || maquina.getForca() >= jogador.getForca()) || jogador.getInteligencia() >= maquina.getInteligencia() || jogador.getForca() <= maquina.getForca() || jogador.getDefesa() >= maquina.getDefesa() ) {
				//log.add(maquina.getNome() + "Aumentou INTELIGÊNCIA e FORCA");
				maquina.setInteligencia(attAleatorio.nextInt(200));
				maquina.setForca(attAleatorio.nextInt(200));
				
				//log.add(jogador.getNome() + "Aumentou INTELIGÊNCIA e FORCA");
				jogador.setInteligencia(attAleatorio.nextInt(200));
				jogador.setForca(attAleatorio.nextInt(200));
				
				log.add(maquina.getNome()+"Usou o combo (Mil anos de Python) em  " + jogador.getNome());
				maquina.setVida(maquina.getVida() - (jogador.combo()+ 150000));
				log.add(jogador.getNome()+"Se Defendeu de " + maquina.getNome() + " Usando o Firewal");
				jogador.setVida(jogador.getVida() - (maquina.combo()+150000));
				jogador.setVida(jogador.getVida() + 10000);
				if(jogador.getInteligencia() > maquina.getInteligencia()) {maquina.setVida(maquina.getVida() - (jogador.combo()+ 150000));}
				
			}else {
				jogador.setAtaque(attAleatorio.nextInt(200));
				maquina.setAtaque(attAleatorio.nextInt(200));
				log.add(jogador.getNome()+ " e " + maquina.getNome() + " Aumentaram o PODER " + "| Agora a parada ficou Séria . . .");
				maquina.setVida(maquina.getVida() - (jogador.atacar()+ 250000));
				jogador.setVida(jogador.getVida() - (maquina.atacar()+250000));
			}
			//maquina.setVida((maquina.getVida() - (jogador.atacar() + 50000)));
			log.add(jogador.getNome()+ " e " + maquina.getNome() + " Estão se Batendo sem parar " + " e estão destruindo o campo de batalha");
			jogador.setVida((jogador.getVida() - (maquina.atacar() + 250000)));		
			maquina.setVida(maquina.getVida() - (jogador.combo()+ 250000));
			jogador.setVida(jogador.getVida() - (maquina.combo()+250000));
			
		}
		
		System.out.println("Batalha entre " + jogador.getNome()+ " E " + maquina.getNome());
		
		if (jogador.getVida() == maquina.getVida() || jogador.getAtaque() == maquina.getAtaque() || jogador.getInteligencia() == maquina.getInteligencia() || jogador.getNome().equals(maquina.getNome())) {
			return "Deu empate";}
		
		if(jogador.getVida() > maquina.getVida() || jogador.getAtaque() > maquina.getAtaque() || jogador.getInteligencia() > maquina.getInteligencia()) {
		vencedor = jogador.getNome();
		//System.out.println("O vencedor foi o Jogador "+ vencedor.getNome() ;
		//vencedor = (vencedor + " Venceu"); //(vencedor);
		return ("Héroi "+ vencedor + " Venceu !!!!");
		}
		if(maquina.getVida() > jogador.getVida() || maquina.getAtaque() > jogador.getAtaque() || maquina.getInteligencia() > jogador.getInteligencia()){
			vencedor = maquina.getNome();
			//System.out.println("O vencedor é maquina" + vencedor.getNome() + " | " +vencedor.getVida());
			//return ("O vencedor é " + vencedor.getNome());
			return "O adversário " + vencedor + " venceu";//vencedor;
		}
				
		return vencedor;
	
	}

	
	public List<String> getLog() {
		return log;
	}

	
	
	}

