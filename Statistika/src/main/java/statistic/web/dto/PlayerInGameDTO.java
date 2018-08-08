package statistic.web.dto;

public class PlayerInGameDTO {
	
	private Long id;
	private Long playerId;
	private String name;
	private int jerseyNmber;
	private String positionName;
	private Long gameId;
	private Long teamId;
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	private int onePointShot;
	private int onePointScore;
	private int twoPointShot;
	private int twoPointScore;
	private int threePointShot;
	private int threePointScore;
	private int assist;
	private int steal;
	private int turnOver;
	private int reboundOff;
	private int reboundDef;
	private int blockShot;
	private int personalFaul;
	private int totalPoints;
	private int totalRebounds;
	private boolean fouledOut = false;
	private boolean onRoster = true;
	
	public boolean isOnRoster() {
		return onRoster;
	}
	public void setOnRoster(boolean onRoster) {
		this.onRoster = onRoster;
	}
	public Long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
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
	public int getJerseyNmber() {
		return jerseyNmber;
	}
	public void setJerseyNmber(int jerseyNmber) {
		this.jerseyNmber = jerseyNmber;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public Long getGameId() {
		return gameId;
	}
	public void setGameId(Long gameId) {
		this.gameId = gameId;
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
	public boolean isFouledOut() {
		return fouledOut;
	}
	public void setFouledOut(boolean fouledOut) {
		this.fouledOut = fouledOut;
	}
	
	
}
