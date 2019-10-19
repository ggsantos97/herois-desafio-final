angular.module("heroi").factory("heroisService", function ($http) {
	
	var getJogador = 'http://localhost:8080/curso-hackaton-cdi/jogador';
	var getAllHeroi = 'http://localhost:8080/curso-hackaton-cdi/heroi';
	
	
	var _getHerois = function () {
		return $http.get(getAllHeroi);
	};
	
	var _getJogador = function(){
		return $http.get(getJogador);
	}

	function _postLogin (user) {
		return $http.post('http://localhost:8080/curso-hackaton-cdi/jogador/autenticar', user);
	}

	function _cadastrarJogador(user){
		return $http.post('http://localhost:8080/curso-hackaton-cdi/jogador/',user);
	}

	function _batalhar(player1, player2){
			return $http.post(' http://localhost:8080/curso-hackaton-cdi/jogador/batalhar/'+player1+"/"+player2)			
	};
	return {
		getHerois: _getHerois,
		getJogador: _getJogador,
		postAltenticar:_postLogin,
		cadastrar:_cadastrarJogador,
		batalhar:_batalhar
	};
		
});