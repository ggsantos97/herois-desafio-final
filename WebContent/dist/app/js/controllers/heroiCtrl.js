
angular.module("heroi").controller("heroiCtrl", function ($scope, heroisService) {

	// View Model 
	const vm = this;
	vm.app = "Heróis";
	vm.service = heroisService;
	vm.herois = [];
	vm.jogadores = [];
	
	vm.init = function(){
		vm.login = true;
		vm.carregarHerois();
		vm.user = {};
	}
	// Trás todos os Hérois da Base
	vm.carregarHerois = function () {
		vm.service.getHerois().success(function (data) {
			vm.herois = data;
			console.log(vm.herois);
		}).error(function (data, status) {
			vm.message = "Aconteceu um problema: " + data;
	
		});
		
	};
		// Trás todos jogadores da base
		vm.carregaJogadores = function (){
			vm.service.getJogador().success(function (data){
				vm.jogadores = data;
					
			}).error(function (data, status){
				vm.message = "deu bosta " + data + status;
			});
		};
		// Cadastrar Jogador
	vm.cadastrarJogador = function (){
		//senha = vm.user.senha;
			vm.h ={};
			 vm.h = vm.user.heroi
			vm.user.personagem = vm.h;	
		vm.service.cadastrar(vm.user).success(function (data){	
		}).error(function(data, status){
			vm.message = data;
		})
		setTimeout(function(){ vm.irLogin(); }, 3000);
	};

	vm.altenticar = function(){
		//Verifica se Jogador está cadastrado na base
		vm.service.postAltenticar(vm.user).success(function (data){
			vm.user = data;
			if((data.nickname == vm.user.nickname)){
				vm.modoJogo();
			}
		}).error(function (data, status){
				vm.message = data;
				console.log("dados",data, "status", status);
		});
	};

	vm.batalhar = function(){
		vm.player1 = vm.user.jogador.personagem.id;
		vm.player2 = vm.user.personagem.id;
		vm.service.batalhar(vm.player1, vm.player2).success(function (data){
			vm.resultado = data;
			vm.irResultadoMult();
		}).error(function (data, status){
			vm.message = data;
			console.log(data, status);
		})
	}

	vm.batalhaSingle = function(){
		vm.player1 = vm.user.personagem.id;
		vm.player2 = 0;
		vm.service.batalhar(vm.player1, vm.player2).success(function (data){
			vm.resultado = data;
			vm.irResultadoSingle();
		}).error(function (data, status){
			vm.message = data;
		})

	}

					// Navegação 
	// Tela de cadastro
	vm.irCadastrar = function () {
		// vm.user.nickname = "";
		// vm.user.senha="";
		vm.user.heroi="";
		vm.cadastrar = true;
		vm.login = false;
	}
	// Tela de Login 
	vm.irLogin = function () {
		vm.user.nickname = "";
		vm.user.senha="";
		vm.login = true;
		vm.cadastrar = false;
		vm.escolherModo= false;
	}
	//Tela escolher modo de jogo 
	vm.modoJogo = function(){
		vm.login = false;
		vm.cadastrar = false;
		vm.escolherModo = true;
		vm.jogarSingle= false;
		vm.jogarMult = false;
		vm.resultSingle = false;
	}
	//abrir tela de jogo no modo single player
	vm.irJogarSingle = function(){
		vm.login = false;
		vm.cadastrar = false;
		vm.escolherModo = false;
		vm.jogarSingle= true;
		jogarMult = false;
		vm.resultSingle = false;
	}

	vm.irResultadoSingle = function(){
		vm.login = false;
		vm.cadastrar = false;
		vm.escolherModo = false;
		vm.jogarSingle= true;
		vm.jogarMult = false;	
		vm.resultSingle = true;
	}
	// abrir tela de jogo multi player
	vm.irJogarMult = function(){
		vm.user;
		vm.user.jogador="";
		vm.login = false;
		vm.cadastrar = false;
		vm.escolherModo = false;
		vm.jogarSingle = false;
		vm.jogarMult = true;
		vm.resultMult = false;
		vm.carregaJogadores(); 
	}
	vm.irResultadoMult = function(){
		vm.login = false;
		vm.cadastrar = false;
		vm.escolherModo = false;
		vm.jogarSingle= false;
		vm.jogarMult = true;	
		vm.resultMult = true;
	}

	
});