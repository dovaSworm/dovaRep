var statisticApp = angular.module("statisticApp", [ 'ngRoute' ]);

statisticApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : '/app/html/players.html'
	}).when('/edit', {
		templateUrl : '/app/html/edit-players.html'
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
	
//	 $scope.izmeni = function(id){
//	        $location.path('/players/edit/' + id);
//	    }

	var getTeams = function() {
		$http.get(baseUrlTeams).then(function success(res) {
			$scope.teams = res.data;
		}, function error(res) {
			alert("Unsuccessful!");
		});
	};

	getTeams();

	$scope.point1Shot = function(id) {
		$http.put(baseUrlPlayers + "/" + id + "/onePointShot").then(
				function success(res) {
					getHostPlayers();
					getGuestPlayers();
					console.log($scope.teamAid);
					console.log($scope.teamBid);
				}, function error(res) {
					alert("Unsuccessful!");
				});

	}
	$scope.point1Score = function(id) {
		$http.put(baseUrlPlayers + "/" + id + "/onePointScore").then(
				function success(res) {
					getHostPlayers();
					getGuestPlayers();
					console.log($scope.teamAid);
					console.log($scope.teamBid);
				}, function error(res) {
					alert("Unsuccessful !");
				});

	}
	
	$scope.point3Shot = function(id) {
		$http.put(baseUrlPlayers + "/" + id + "/threePointShot").then(
				function success(res) {
					getHostPlayers();
					getGuestPlayers();
					console.log($scope.teamAid);
					console.log($scope.teamBid);
				}, function error(res) {
					alert("Unsuccessful !");
				});

	}
	$scope.point3Score = function(id) {
		$http.put(baseUrlPlayers + "/" + id + "/threePointScore").then(
				function success(res) {
					getHostPlayers();
					getGuestPlayers();
					console.log($scope.teamAid);
					console.log($scope.teamBid);
				}, function error(res) {
					alert("Unsuccessful !");
				});

	}
	
	$scope.point2Shot = function(id) {
		$http.put(baseUrlPlayers + "/" + id + "/twoPointShot").then(
				function success(res) {
					getHostPlayers();
					getGuestPlayers();
					console.log($scope.teamAid);
					console.log($scope.teamBid);
				}, function error(res) {
					alert("Unsuccessful!");
				});

	}
	$scope.point2Score = function(id) {
		$http.put(baseUrlPlayers + "/" + id + "/twoPointScore").then(
				function success(res) {
					getHostPlayers();
					getGuestPlayers();
					console.log($scope.teamAid);
					console.log($scope.teamBid);
				}, function error(res) {
					alert("Unsuccessful !");
				});

	}
	
	$scope.addAssists = function(id) {
		$http.put(baseUrlPlayers + "/" + id + "/assist").then(
				function success(res) {
					getHostPlayers();
					getGuestPlayers();
					console.log($scope.teamAid);
					console.log($scope.teamBid);
				}, function error(res) {
					alert("Unsuccessful !");
				});

	}
///////////////////////////////////////////////////////////////////////////////////////////////////////
	$scope.addOReb = function(id) {
		$http.put(baseUrlPlayers + "/" + id + "/off").then(
				function success(res) {
					getHostPlayers();
					getGuestPlayers();
					console.log($scope.teamAid);
					console.log($scope.teamBid);
				}, function error(res) {
					alert("Unsuccessful!");
				});

	}
	$scope.addDReb = function(id) {
		$http.put(baseUrlPlayers + "/" + id + "/def").then(
				function success(res) {
					getHostPlayers();
					getGuestPlayers();
					console.log($scope.teamAid);
					console.log($scope.teamBid);
				}, function error(res) {
					alert("Unsuccessful !");
				});

	}
	$scope.addFaul = function(id) {
		$http.put(baseUrlPlayers + "/" + id + "/faul").then(
				function success(res) {
					getHostPlayers();
					getGuestPlayers();
					console.log($scope.teamAid);
					console.log($scope.teamBid);
				}, function error(res) {
					alert("Unsuccessful!");
				});

	}
	$scope.addTO = function(id) {
		$http.put(baseUrlPlayers + "/" + id + "/to").then(
				function success(res) {
					getHostPlayers();
					getGuestPlayers();
					console.log($scope.teamAid);
					console.log($scope.teamBid);
				}, function error(res) {
					alert("Unsuccessful!");
				});

	}
	$scope.addSteal = function(id) {
		$http.put(baseUrlPlayers + "/" + id + "/steal").then(
				function success(res) {
					getHostPlayers();
					getGuestPlayers();
					console.log($scope.teamAid);
					console.log($scope.teamBid);
				}, function error(res) {
					alert("Unsuccessful!");
				});

	}
	$scope.addBlock = function(id) {
		$http.put(baseUrlPlayers + "/" + id + "/block").then(
				function success(res) {
					getHostPlayers();
					getGuestPlayers();
					console.log($scope.teamAid);
					console.log($scope.teamBid);
				}, function error(res) {
					alert("Unsuccessful!");
				});

	}
	
	$(function () {
		$("body").tooltip({
			selector: '[data-toggle="tooltip"]',
			container: 'body'
		});
	})

    
});
/////////////////////////dodavanje i editovanje igraca/////////////////////////////////////////////
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
	
	var getTeams = function() {
		$http.get(baseUrlTeams).then(
				function(res) {
					$scope.teams = res.data;
				}, function(res) {
					alert("wrong");
				})
	}
	getTeams();
	
	var getPositions = function() {
		$http.get(baseUrlPositions).then(
				function(res) {
					$scope.positions = res.data;
				}, function(res) {
					alert("wrong");
				})
	}
	getPositions();
	
	var getPlayers = function() {
		$http.get(baseUrlPlayers).then(
				function(res) {
					$scope.players = res.data;
				}, function(res) {
					alert("wrong");
				})
	}
	getPlayers();
	
	$scope.add = function() {
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
		$http.put(baseUrlPlayers + "/protectedByUserRole/" + $scope.oldPlayerId,
				$scope.newPlayer).then(function success(data) {
			alert("Successfull editing!");
			$location.path("/");
		}, function error(data) {
			alert("Unsuccessful editing.");
		});
	}
});
