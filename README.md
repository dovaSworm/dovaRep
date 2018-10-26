# Basketball statistic

# Statictic for basketball game. CRUD operations on players, teams and games. Changing player statistics trough javacsript UI. Javascript framework is AngularJS. Saving records for all games in database(MySQL) with hibernate. UI enrichment with JQuery and bootstrap.
# Single page application. Maven build tool, framework spring boot , mvc patern.

# Model:

	@Entity
	@Table
	public class Player {
		@Id
		@GeneratedValue
		@Column
		private Long id;
		@Column
		private String name;
		@JoinColumn(name = "team_id")
		@ManyToOne(fetch = FetchType.LAZY)
		private Team team;
		@Column
		private int jerseyNumber;
		@ManyToOne(fetch = FetchType.LAZY)
		private PlayingPosition playingPosition;
		@Column
		private boolean onRoster = true;
		//geters and setters
	}

	@Entity
	@Table
	public class PlayerInGame {

		@Id
		@GeneratedValue
		@Column
		private Long id;
		@JoinColumn(name = "player_id")
		@ManyToOne(fetch = FetchType.LAZY)
		private Player player;
		@JoinColumn(name = "game_id")
		@ManyToOne(fetch = FetchType.LAZY)
		private BallGame game;
		@ManyToOne(fetch = FetchType.LAZY)
		private Team team;
		@Column
		private int onePointShot;
		@Column
		private int onePointScore;
		@Column
		private int twoPointShot;
		@Column
		private int twoPointScore;
		@Column
		private int threePointShot;
		@Column
		private int threePointScore;
		@Column
		private int assist;
		@Column
		private int steal;
		@Column
		private int turnOver;
		@Column
		private int reboundOff;
		@Column
		private int reboundDef;
		@Column
		private int blockShot;
		@Column
		private int personalFaul;
		@Column
		private boolean fouledOut = false;
		@Column
		private int totalPoints;
		@Column
		private int totalRebounds;
		@Column
		private boolean onRoster = true;
	   //geters and setters
	}

# View:
	public class PlayerDTO {
		private Long id;
		private String name;
		private Long teamId;
		private String teamName;
		private int jerseyNmber;
		private Long positionId;
		private String positionName;

		// geters and setters
		}
# Controller:
	@RestController
	@RequestMapping("/api/players")
	public class PlayerController {

		@Autowired
		private PlayerService playerService;
		@Autowired
		private PlayerToDTO toDTO;
		@Autowired
		private PlayerDTOToPlayer toPlayer;

		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<List<PlayerDTO>> get(@RequestParam(defaultValue = "0") int pageNum) {

			List<Player> players;
			players = playerService.findAll();
			return new ResponseEntity<>(toDTO.convert(players), HttpStatus.OK);
		}

		@RequestMapping(method = RequestMethod.GET, value = "/{id}")
		public ResponseEntity<PlayerDTO> get(@PathVariable Long id) {
			Player p = playerService.findOne(id);

			if (p == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(toDTO.convert(p), HttpStatus.OK);
		}
	}

# Players staticstic is saved trough new entity PlayerInGame wich is created when new game is created. For every player from both teams new playerInGame is created and hold reference to player. With this patern we save staticstic from every game for every player.

	@Service
	@Transactional
	public class PlayerServiceImpl implements PlayerService{

		@Autowired
		PlayerRepository playerRepository;
		@Autowired
		TeamRepositiry teamRepository;
		@Autowired
		private PlayerInGameRepository palyerInGameRepository;
		@Override
		public void makePlayersInGame(BallGame bg) {
			List<Player> hostPlayers = playerRepository.findByTeamId(bg.getHost().getId());
			List<Player> guestPlayers = playerRepository.findByTeamId(bg.getGuest().getId());

			for(Player p : hostPlayers) {
				PlayerInGame playerInGame = new PlayerInGame(p);
				playerInGame.setGame(bg);
				palyerInGameRepository.save(playerInGame);
			}
			for(Player p : guestPlayers) {
				PlayerInGame playerInGame = new PlayerInGame(p);
				playerInGame.setGame(bg);
				palyerInGameRepository.save(playerInGame);
			}

		}
	  //other metods
	  }
  
# From front end side we sent playersInGame id and params to Controller:
 
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
						alert("Unsuccessful change")
					});
  
 # This is html code where we get value of param. It is a table with all players from team(guest or host):
  
	  <tr style="height: 10px" ng-repeat="p in guestPlayers">
					<td>{{p.jerseyNmber}}</td>
					<td data-toggle="tooltip" data-html="true"
						title="{{p.name}} <br /> 
	1p {{p.onePointShot}}/{{p.onePointScore}} 2p {{p.twoPointShot}}/{{p.twoPointScore}} 3p {{p.threePointShot}}/{{p.threePointScore}} <br /> 
	rebounds {{p.totalRebounds}} assists {{p.assist}} turn over {{p.turnOver}} steals {{p.steal}} rampe {{p.blockShot}}">{{p.name}}</td>
					<td>{{p.totalPoints}}</td>
					<td>{{p.positionName}}</td>
					<td><button id="my-btn" ng-click="change(p.id, 'onePointShot')"
							class="btn btn-danger" ng-disabled="p.out == true">1s</button></td>
					<td><button id="my-btn" ng-click="change(p.id, 'onePointScore')"
							class="btn btn-success" ng-disabled="p.out == true">1sc</button></td>
					<td><button id="my-btn" ng-click="change(p.id, 'twoPointShot')"
							class="btn btn-danger" ng-disabled="p.out == true">2s</button></td>
		    // rest of code

#   This tooltip apears on hover over players name and give full stat of player in real time
					<td data-toggle="tooltip" data-html="true"
						title="{{p.name}} <br /> 
	1p {{p.onePointShot}}/{{p.onePointScore}} 2p {{p.twoPointShot}}/{{p.twoPointScore}} 3p {{p.threePointShot}}/{{p.threePointScore}} <br /> 
	rebounds {{p.totalRebounds}} assists {{p.assist}} turn over {{p.turnOver}} steals {{p.steal}} rampe {{p.blockShot}}">{{p.name}}</td> 
            
 # String that we send trough params is determinat which attribute we change in service:
  
	@Service
	@Transactional
	public class PlayerInGameServiceImpl implements PlayerInGameService {

		@Autowired
		private BallGameRepository bgR;
		@Autowired
		private PlayerInGameRepository inGameR;
	  @Override
		public void changeStatistic(Long id, String action) {
			PlayerInGame p = inGameR.findOne(id);
			if (action.equals("onePointShot")) {
				p.setOnePointShot((p.getOnePointShot() + 1));
				inGameR.save(p);
			} else if (action.equals("onePointScore")) {
				p.setOnePointScore((p.getOnePointScore() + 1));
				p.setOnePointShot((p.getOnePointShot() + 1));
				p.setTotalPoints(p.getTotalPoints() + 1);
				inGameR.save(p);
			} else if (action.equals("twoPointShot")) {
				p.setTwoPointShot((p.getTwoPointShot() + 1));
				inGameR.save(p);
			} else if (action.equals("twoPointScore")) {
				p.setTwoPointScore((p.getTwoPointScore() + 1));
				p.setTwoPointShot((p.getTwoPointShot() + 1));
				p.setTotalPoints(p.getTotalPoints() + 2);
				inGameR.save(p);
			} else if (action.equals("threePointShot")) {
				p.setThreePointShot((p.getThreePointShot() + 1));
				inGameR.save(p);
			} else if (action.equals("threePointScore")) {
				p.setThreePointShot((p.getThreePointShot() + 1));
				p.setThreePointScore((p.getThreePointScore() + 1));
				p.setTotalPoints(p.getTotalPoints() + 3);
				inGameR.save(p);
			} else if (action.equals("steal")) {
				p.setSteal((p.getSteal() + 1));
				inGameR.save(p);
			} else if (action.equals("to")) {
				p.setTurnOver((p.getTurnOver() + 1));
				inGameR.save(p);
			} else if (action.equals("off")) {
				p.setReboundOff((p.getReboundOff() + 1));
				p.setTotalRebounds(p.getTotalRebounds() + 1);
				inGameR.save(p);
			} else if (action.equals("def")) {
				p.setReboundDef((p.getReboundDef() + 1));
				p.setTotalRebounds(p.getTotalRebounds() + 1);
				inGameR.save(p);
			} else if (action.equals("block")) {
				p.setBlockShot((p.getBlockShot() + 1));
				inGameR.save(p);
			} else if (action.equals("assist")) {
				p.setAssist((p.getAssist() + 1));
				inGameR.save(p);
			} else if (action.equals("faul")) {
				p.setPersonalFaul((short) (p.getPersonalFaul() + 1));
				if (p.getPersonalFaul() == 5) {
					p.setFouledOut(true);
				}
				inGameR.save(p);
			}

		}
	  }

# Result is automatically changed by changing individual players statistic
	
	var result = function() {
		$http.get(baseUrlBallGame + "/result/" + $scope.ballGame.id).then(
				function success(res) {
					$scope.ballGame = res.data;
				}, function error(data) {
					alert("Unsuccessful $scope.result geting.");
				});

	};
	
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
					alert("Unsuccessful change")
				});
		
	}
	
	@Service
	@Transactional
	public class BallGameServiceImpl implements BallGameService {

	@Autowired
	private BallGameRepository ballgameRepository;
	@Autowired
	private TeamRepositiry teamRepositry;
	@Autowired
	private PlayerInGameService playerInGameService;
	@Autowired
	private PlayerService playerService;
	
	// here we culculate result in game
	@Override
	public BallGame result(Long id) {

		BallGame bg = findOne(id);
		int hostPoints = bg.getHostPlayers().stream().mapToInt(PlayerInGame::getTotalPoints).sum();
		int guestPoints = bg.getGuestPlayers().stream().mapToInt(PlayerInGame::getTotalPoints).sum();

		bg.setGuestPoints(guestPoints);
		bg.setHostPoints(hostPoints);
		
		return bg;

	}
	}
	
	// here  method above is called
	
	@RestController
	@RequestMapping("/api/ballGame")
	public class BallGameController {

	@Autowired
	private BallGameService ballgameService;
	@Autowired
	private BallGameToDTO toDTO;
	@Autowired
	private BallGameDTOToBallGame toGame;
	
	@RequestMapping(method = RequestMethod.GET, value = "/result/{id}")
	public ResponseEntity<BallGameDTO> result(@PathVariable Long id) {
		BallGame bg = ballgameService.result(id);
		return new ResponseEntity<>(toDTO.convert(bg), HttpStatus.OK);
	}
 	}
