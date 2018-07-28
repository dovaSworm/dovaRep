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
public class Player {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String name;
	@JoinColumn(name = "team_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private Team team;
	@Column
	private int jerseyNumber;
	@ManyToOne(fetch = FetchType.EAGER)
	private PlayingPosition playingPosition;
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

	public int getPoeniTotal() {
		return totalPoints;
	}

	public void setPoeniTotal(int poeniTotal) {
		this.totalPoints = poeniTotal;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
		if (team != null && !team.getPlayers().contains(this)) {
			team.getPlayers().add(this);
		}
	}

	public PlayingPosition getPlayingPosition() {
		return playingPosition;
	}

	public void setPlayingPosition(PlayingPosition position) {
		this.playingPosition = position;
		if (position != null && !position.getPlayers().contains(this)) {
			position.getPlayers().add(this);
		}
	}

	public int getOnePointShoot() {
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

	public int getTwoPointShoot() {
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

	public void setThreePointShoot(int threePointShot) {
		this.threePointShot = threePointShot;
	}

	public int getThreePointScore() {
		return threePointScore;
	}

	public void setThreePointScore(int threePointScore) {
		this.threePointScore = threePointScore;
	}

	public int getSteal() {
		return steal;
	}

	public void setSteal(int steal) {
		this.steal = steal;
	}

	public int getJerseyNumber() {
		return jerseyNumber;
	}

	public void setJerseyNumber(int jerseyNumber) {
		this.jerseyNumber = jerseyNumber;
	}

	public int getAssist() {
		return assist;
	}

	public void setAssist(int assist) {
		this.assist = assist;
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

	public void setFouledOut(boolean excpelled) {
		this.fouledOut = excpelled;
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

	public int getOnePointShot() {
		return onePointShot;
	}

	public int getTwoPointShot() {
		return twoPointShot;
	}

	public void setThreePointShot(int threePointShot) {
		this.threePointShot = threePointShot;
	}

}
