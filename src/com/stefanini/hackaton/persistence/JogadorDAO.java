package com.stefanini.hackaton.persistence;

import com.stefanini.hackaton.entities.Jogador;

public class JogadorDAO extends GenericDAO<Integer, Jogador>{

		public Jogador findByNameSenha(String nickname, String senha) {
		
				return (Jogador) getEntityManager().createNamedQuery("Jogador.findByNameSenha")
						.setParameter("nickname", nickname).setParameter("senha", senha).getSingleResult() ;
}

		public Jogador findByName(String nickname) {
			
			return (Jogador) getEntityManager().createNamedQuery("Jogador.findByName")
					.setParameter("nickname", nickname).getSingleResult() ;
}
		
	/*	public Jogador getByIdHeroi(Integer idHeroi) {
			
			return (Jogador) getEntityManager().createNamedQuery("Jogador.getByIdHeroi")
					.setParameter("idHeroi", idHeroi).getSingleResult() ;
}*/

}