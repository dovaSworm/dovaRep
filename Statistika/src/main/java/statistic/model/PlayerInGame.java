package statistic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
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
	
	public boolean isOnRoster() {
		return onRoster;
	}
	public void setOnRoster(boolean onRoster) {
		this.onRoster = onRoster;
	}
	public void fouledOut() {
		if (this.personalFaul == 5) {
			this.fouledOut = true;
		}
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public BallGame getGame() {
		return game;
	}
	public void setGame(BallGame game) {
		this.game = game;
		if (game != null && !game.getHostPlayers().contains(this) && !game.getGuestPlayers().contains(this)) {
			if(this.getPlayer().getTeam().getId() == game.getHost().getId()) {
			game.getHostPlayers().add(this);
		}else {
			game.getGuestPlayers().add(this);
		}
	}
	}
	public int getOnePointShot() {
		return onePointShot;
	}
	public void setOnePointShot(int onePointShot) {
		this.onePointShot = onePointShot;
	}
	public int getOnePointScore() {
		return onePointScore;
	}
	public void setOnePointScore(int onePointScore) {
		this.onePointScore = onePointScore;
	}
	public int getTwoPointShot() {
		return twoPointShot;
	}
	public void setTwoPointShot(int twoPointShot) {
		this.twoPointShot = twoPointShot;
	}
	public int getTwoPointScore() {
		return twoPointScore;
	}
	public void setTwoPointScore(int twoPointScore) {
		this.twoPointScore = twoPointScore;
	}
	public int getThreePointShot() {
		return threePointShot;
	}
	public void setThreePointShot(int threePointShot) {
		this.threePointShot = threePointShot;
	}
	public int getThreePointScore() {
		return threePointScore;
	}
	public void setThreePointScore(int threePointScore) {
		this.threePointScore = threePointScore;
	}
	public int getAssist() {
		return assist;
	}
	public void setAssist(int assist) {
		this.assist = assist;
	}
	public int getSteal() {
		return steal;
	}
	public void setSteal(int steal) {
		this.steal = steal;
	}
	public int getTurnOver() {
		return turnOver;
	}
	public void setTurnOver(int turnOver) {
		this.turnOver = turnOver;
	}
	public int getReboundOff() {
		return reboundOff;
	}
	public void setReboundOff(int reboundOff) {
		this.reboundOff = reboundOff;
	}
	public int getReboundDef() {
		return reboundDef;
	}
	public void setReboundDef(int reboundDef) {
		this.reboundDef = reboundDef;
	}
	public int getBlockShot() {
		return blockShot;
	}
	public void setBlockShot(int blockShot) {
		this.blockShot = blockShot;
	}
	public int getPersonalFaul() {
		return personalFaul;
	}
	public void setPersonalFaul(int personalFaul) {
		this.personalFaul = personalFaul;
	}
	public boolean isFouledOut() {
		return fouledOut;
	}
	public void setFouledOut(boolean fouledOut) {
		this.fouledOut = fouledOut;
	}
	public int getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
	public int getTotalRebounds() {
		return totalRebounds;
	}
	public void setTotalRebounds(int totalRebounds) {
		this.totalRebounds = totalRebounds;
	}
	public PlayerInGame() {
		super();
	}
	public PlayerInGame(Player player) {
		super();
		this.player = player;
		this.team = player.getTeam();
		this.onePointShot = 0;
		this.onePointScore = 0;
		this.twoPointShot = 0;
		this.twoPointScore = 0;
		this.threePointShot = 0;
		this.threePointScore = 0;
		this.assist = 0;
		this.steal = 0;
		this.turnOver = 0;
		this.reboundOff = 0;
		this.reboundDef = 0;
		this.blockShot = 0;
		this.personalFaul = 0;
		this.fouledOut = false;
		this.totalPoints = 0;
		this.totalRebounds = 0;
		this.onRoster = player.isOnRoster();
	}
	

}
