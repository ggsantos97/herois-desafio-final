package com.stefanini.hackaton.batalha;

import java.util.Random;

import javax.inject.Inject;

import com.stefanini.hackaton.dto.HeroiDto;
import com.stefanini.hackaton.service.BatalhaService;

public class Fight {

	@Inject
	private HeroiDto heroiDto;
	
	

	
	public static void main(String[] args) {
		HeroiDto jogador = new HeroiDto();
		HeroiDto maquina = new HeroiDto();
		
		Integer rodada = 1;
		
		jogador.setAtaque(120);
		jogador.setDefesa(90);
		jogador.setForca(45);
		jogador.setPoder(30);
		jogador.setVida(500000);
		jogador.setNome("Gustavo");
		
		maquina.setAtaque(19);
		maquina.setDefesa(130);
		maquina.setForca(45);
		maquina.setPoder(30);
		maquina.setVida(500000);
		maquina.setNome("Maquina");

		StringBuilder concat = new StringBuilder();
		
		while (maquina.getVida() > 0 && jogador.getVida() > 0) {
				maquina.setVida((maquina.getVida() - (jogador.atacar() + 1000)));
				jogador.setVida((jogador.getVida() - (maquina.atacar() + 1000)));
				System.out.println( concat.append( ("Rodada "+ rodada++) + " Vida de "+jogador.getNome()+ " " +jogador.getVida() + " VS " + " Maquina " + maquina.getVida() + " || "));	
		
				if(jogador.getVida() < 0) {
					jogador.setVida(0);
				}
				if( maquina.getVida() < 0 ) {
					maquina.setVida(0);
				}
				
		}
		System.out.println( concat.append(jogador.getVida() + " <<<última rodada>>> " +maquina.getVida()));
		
		HeroiDto vencedor = null;
		if(jogador.getVida() > maquina.getVida() && jogador.getVida() > 0) {
			maquina.setVida(0);
			vencedor = jogador;
		}  else if (jogador.getVida() < maquina.getVida() &&  maquina.getVida() > 0) {
			vencedor = maquina;
		} else {
			System.out.println("Deu empate");
		}
		
		
	
	System.out.println("Vencedor :  " + vencedor.getNome());
	
	Random random = new Random();
		
	System.out.println("id Personagem " + random.nextInt(249));

	}

	
	public HeroiDto comparaAtt(HeroiDto jogador, HeroiDto maquina) {
		HeroiDto vencedor = null;
		
		if(jogador.getAtaque() >= maquina.getDefesa()) {
			maquina.setVida((maquina.getVida() - (jogador.atacar() + 50)));
		}else {
			jogador.setVida((jogador.getVida() - (maquina.atacar() + 50)));
		}
		
		if(jogador.getDefesa() > maquina.getDefesa()) {
			maquina.setVida(maquina.getVida() - (jogador.combo()+ 100));
		}else {
			jogador.setVida(jogador.getVida() - (maquina.combo()+100));
		}
		
		if(jogador.getVida() > maquina.getVida()) {
			vencedor = jogador;
		}else {
			vencedor = maquina;
		}
		return vencedor;
	}
}
