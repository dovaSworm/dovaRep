var statisticApp = angular.module("statisticApp", [ 'ngRoute' ]);

statisticApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/edit', {
		templateUrl : '/app/html/edit-players.html'
	}).when('/', {
		templateUrl : '/app/html/statistic.html'
	}).when('/game/:gid', {
		templateUrl : '/app/html/statistic.html'
	}).otherwise({
		redirectTo : '/'
	});
} ]);

statisticApp.controller("playersCtrl", function($scope, $http, $location) {

	var baseUrlPlayers = "/api/players";
	var baseUrlTeams = "/api/teams";
	var baseUrlPositions = "/api/positions";

	$scope.pageNum = 0;
	$scope.totalPages = 0;

	$scope.teamA = [];
	$scope.teamB = [];
	$scope.teams = [];
	$scope.teamAid;
	$scope.teamBid;

	var getHostPlayers = function() {
		$http.get(baseUrlTeams + "/" + $scope.teamAid + "/players").then(
				function(res) {
					$scope.teamA = res.data;
					console.log($scope.teamA);
				}, function(res) {
					alert("wrong");
				})
	}

	var getGuestPlayers = function() {
		$http.get(baseUrlTeams + "/" + $scope.teamBid + "/players").then(
				function(res) {
					$scope.teamB = res.data;
					console.log($scope.teamB);
				}, function(res) {
					alert("wrong");
				})
	}

	$scope.addHost = function() {
		getHostPlayers();
	}

	$scope.addGuest = function() {
		getGuestPlayers();
	}

	// $scope.izmeni = function(id){
	// $location.path('/players/edit/' + id);
	// }

	var getTeams = function() {
		$http.get(baseUrlTeams).then(function success(res) {
			$scope.teams = res.data;
		}, function error(res) {
			alert("Unsuccessful!");
		});
	};

	getTeams();

});
var getBallGame = function() {
	$http.get(baseUrlBallGame + "/" + $scope.ballGame.id).then(
			function success(res) {
				$scope.ballGame = res.data;
				console.log($scope.ballGame.id);
				console.log($scope.ballGameId);
			}, function error(data) {
				alert("Unsuccessful $scope.getBallGame geting.");
			});
	
};
// ///////////////////////dodavanje i editovanje
// igraca/////////////////////////////////////////////
statisticApp.controller("editAddPlayerCtrl", function($scope, $http,
		$routeParams, $location) {

	var baseUrlPlayers = "/api/players";
	var baseUrlPositions = "/api/positions"
	var baseUrlTeams = "/api/teams"

	$scope.newPlayer = {};
	$scope.newPlayer.name;
	$scope.newPlayer.jerseyNumber;
	$scope.newPlayer.positionId;
	$scope.newPlayer.teamId;
	$scope.newPlayer.onePointShot = 0;
	$scope.newPlayer.onePointScore = 0;
	$scope.newPlayer.twoPointShot = 0;
	$scope.newPlayer.twoPointScore = 0;
	$scope.newPlayer.threePointShot = 0;
	$scope.newPlayer.threePointScore = 0;
	$scope.newPlayer.assist = 0;
	$scope.newPlayer.steal = 0;
	$scope.newPlayer.turnOver = 0;
	$scope.newPlayer.reboundOff = 0;
	$scope.newPlayer.reboundDef = 0;
	$scope.newPlayer.blockShot = 0;
	$scope.newPlayer.personalFaul = 0;
	$scope.newPlayer.totalPoints = 0;
	$scope.newPlayer.totalRebounds = 0;

	$scope.teams = [];
	$scope.positions = [];
	$scope.players = [];
	
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	

	function linkOrController($scope) {
		
		$scope.go = function(direction){
			$scope.pageNum += direction;
			getPlayers();
		}
	}
	


	var getTeams = function() {
		$http.get(baseUrlTeams).then(function(res) {
			$scope.teams = res.data;
		}, function(res) {
			alert("wrong");
		})
	}
	getTeams();

	var getPositions = function() {
		$http.get(baseUrlPositions).then(function(res) {
			$scope.positions = res.data;
		}, function(res) {
			alert("wrong");
		})
	}
	getPositions();

	var getPlayers = function() {
		$http.get(baseUrlPlayers).then(function(res) {
			$scope.players = res.data;
		}, function(res) {
			alert("wrong");
		})
	}
	getPlayers();

	$scope.addPlayer = function() {
		$http.post(baseUrlPlayers, $scope.newPlayer).then(
				function success(res) {
					getPlayers();
					$scope.newPlayer = {};
					$scope.newPlayer.name;
					$scope.newPlayer.number;
					$scope.newPlayer.positionId;
					$scope.newPlayer.teamId;
					$scope.newPlayer.onePointShoot = 0;
					$scope.newPlayer.onePointScore = 0;
					$scope.newPlayer.twoPointShoot = 0;
					$scope.newPlayer.twoPointScore = 0;
					$scope.newPlayer.threePointShoot = 0;
					$scope.newPlayer.threePointScore = 0;
					$scope.newPlayer.assists = 0;
					$scope.newPlayer.steal = 0;
					$scope.newPlayer.turnOver = 0;
					$scope.newPlayer.offRebound = 0;
					$scope.newPlayer.defRebound = 0;
					$scope.newPlayer.block = 0;
					$scope.newPlayer.faul = 0;
					$scope.newPlayer.poeniTotal = 0;
					$scope.newPlayer.skokTotal = 0;
					$scope.totalPages = res.headers("totalPages");
				}, function error(res) {
					alert("Unsuccessful adding! Team is loaded");
				});
	};

	$scope.oldPlayerId;
	$scope.getOldPlayer = function() {
		$http.get(baseUrlPlayers + "/" + $scope.oldPlayerId).then(
				function success(res) {
					$scope.newPlayer = res.data;
					console.log(res.data);
				}, function error(data) {
					alert("Unsuccessful players geting.");
				});

	};
	
	$scope.edit = function() {
		$http.put(baseUrlPlayers + "/" + $scope.oldPlayerId, $scope.newPlayer).then(
					function success(data) {
					alert("Successfull editing!");
					$location.path("/");
					}, function error(data) {
						alert("Unsuccessful editing.");
					});
	}
	
	$scope.newTeam = {};
	
	$scope.addTeam = function(){
		$http.post(baseUrlTeams, $scope.newTeam).then(
				function success(res) {
					getTeams();
					$scope.newTeam = {};
				}, function error(){
					alert("Unsuccessful $scope.addTeam");
				})
	}
	
	$scope.oldTeamId;
	$scope.getOldTeam = function() {
		$http.get(baseUrlTeams + "/" + $scope.oldTeamId).then(
				function success(res) {
					$scope.newTeam = res.data;
					console.log(res.data);
				}, function error(data) {
					alert("Unsuccessful players geting.");
				});

	};
	
	$scope.edit = function() {
		$http.put(baseUrlPlayers + "/" + $scope.oldPlayerId, $scope.newPlayer).then(
					function success(data) {
					alert("Successfull editing!");
					$location.path("/");
					}, function error(data) {
						alert("Unsuccessful editing.");
					});
	}


});
// ///////////////////////////////STATISTIC/////////////////////////////////////////////////////////////////
statisticApp.controller("statisticCtrl", function($scope, $http, $routeParams,
		$location, $timeout) {

	var baseUrlPlayersInGame = "/api/playersInGame";
	var baseUrlPlayers = "/api/players";
	var baseUrlBallGame = "/api/ballGame"
	var baseUrlTeams = "/api/teams"

	$scope.newBallGame = {};
	$scope.newBallGame.hostId;
	$scope.newBallGame.guestId;
	$scope.newBallGame.hostPoints = 0;
	$scope.newBallGame.guestPoints = 0;
	$scope.newBallGame.hostTimeOut = 3;
	$scope.newBallGame.guestTimeOut = 3;

	$scope.hostPlayers = [];
	$scope.guestPlayers = [];

	$scope.teams = [];
	$scope.playersInGame = [];
	$scope.guestTeamId;
	$scope.hostTeamId;
	$scope.ballGame = {};

	var getGuestPlayers = function() {
		var config = {
			params : {}
		};
		if ($scope.ballGame.id != "") {
			config.params.gameId = $scope.ballGame.id;
		}
		$http.get(
				baseUrlPlayersInGame + "/" + $scope.guestTeamId + "/byTeamId",
				config).then(function(res) {
			console.log(res.data);
			$scope.guestFlag = true;
			$scope.guestPlayers = res.data;
		}, function(res) {
			alert("wrong geting guestplayersInGame");
		})
	}
	var getHostPlayers = function() {
		var config = {
			params : {}
		};
		if ($scope.ballGame.id != "") {
			config.params.gameId = $scope.ballGame.id;
		}
		$http.get(baseUrlPlayersInGame + "/" + $scope.hostTeamId + "/byTeamId",
				config).then(function(res) {
			console.log(res.data);
			$scope.hostPlayers = res.data;
			$scope.hostFlag = true;
		}, function(res) {
			alert("wrong geting guestplayersInGame");
		})
	}

	$scope.hostFlag;
	$scope.guestFlag;
	
	$scope.goToGame = function() {
		getHostPlayers();
		getGuestPlayers();
	}

	var getTeams = function() {
		$http.get(baseUrlTeams).then(function(res) {
			$scope.teams = res.data;
		}, function(res) {
			alert("wrong getTeams");
		})
	}
	getTeams();

	$scope.flagCreatedGame = false;

	$scope.addGame = function() {
		$scope.newBallGame.hostId = $scope.hostTeamId;
		$scope.newBallGame.guestId = $scope.guestTeamId;
		$http.post(baseUrlBallGame, $scope.newBallGame).then(
				function success(res) {
					$scope.ballGame = res.data;
					$scope.newBallGame = {};
					$scope.newBallGame.hostId;
					$scope.newBallGame.guest;
					$scope.newBallGame.hostPoints;
					$scope.newBallGame.guestPoints;
					$scope.newBallGame.hostTimeOut;
					$scope.newBallGame.guestTimeOut;
					$scope.newBallGame.date;
					$scope.flagCreatedGame = true;
				}, function error(res) {
					alert("Unsuccessful $scope.addGame!");
				});
	};

	var result = function() {
		$http.get(baseUrlBallGame + "/result/" + $scope.ballGame.id).then(
				function success(res) {
					$scope.ballGame = res.data;
				}, function error(data) {
					alert("Unsuccessful $scope.result geting.");
				});

	};

	$scope.playerForUndo = [];
	$scope.forAlert = false;

	$scope.reset = function() {
		$http.put(baseUrlPlayersInGame + "/" + $scope.playerForUndo.id,
				$scope.playerForUndo).then(function success(data) {
					result();
					getHostPlayers();
					getGuestPlayers();
					$scope.forAlert = true;
				}, function error(data) {
					alert("Unsuccessful editing.");
				});
	}
	
	$scope.change = function(id,string) {
		var config = {
				params : {}
		};
		if (string != "") {
			config.params.action = string;
		}
		$http.get(baseUrlPlayersInGame + "/" + id+ "/change", config).then(
				function success(res) {
					$scope.playerForUndo = res.data;
					console.log(string);
					console.log(config.params.action);
					result();
					getHostPlayers();
					getGuestPlayers();
				}, function error(res) {
					alert("Unsuccessful!");
				});
		
	}
	
//===================================================================================================
	
	$(document).ready(
			function() {
				$('#btnReset').click(
						function() {
							$('#myAlert').show('fade');
							$('#btnReset').html('success!!').attr('class',
									'btn btn-success');
							setTimeout(function() {
								$('#myAlert').hide('fade');
							}, 2000)
							setInterval(function() {
								$('#btnReset').html('Undo Last').attr('class',
										'btn btn-danger');
							}, 2000);
						});

			});

	$(function(){
		$("#addGameButton").click(function(){
			$scope.hideGoToGame = true;
			$("#forCreateButton").click(function(){
				$scope.hideGoToGame = false;
			})
		}
		)
	})
	$(function() {
		$("body").tooltip({
			selector : '[data-toggle="tooltip"]',
			container : 'body'
		});
	})
});
