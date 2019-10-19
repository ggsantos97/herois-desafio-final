angular.module("heroi").controller("jogadorCtrl", function ($scope, jogadorService) {
	var vm = this;
	vm.app = "Jogador";
	vm.service = jogadorService;
	vm.jogadores = [];

	vm.init = function(){
		vm.login = true;
		vm.buscaJogadores();
	}

	vm.buscaJogadores = function () {
		vm.service.getJogador().success(function (data) {
			vm.jogadores = data;
			console.log(vm.jogadores);
		}).error(function (data, status) {
			vm.message = "Aconteceu um problema: " + data;
		});
		
	};

	vm.irCadastrar = function () {
		vm.cadastrar = true;
		vm.login = false;
	}

	vm.logar = function () {
		vm.login = true;
		vm.cadastrar = false;

		console.log(vm.login);
	}
});