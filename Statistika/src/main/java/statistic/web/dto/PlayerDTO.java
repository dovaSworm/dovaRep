package statistic.web.dto;


public class PlayerDTO {

	private Long id;
	private String name;
	private Long teamId;
	private int number;
	private Long positionId;
	private int onePointShoot;
	private int onePointScore;
	private int twoPointShoot;
	private int twoPointScore;
	private int threePointShoot;
	private int threePointScore;
	private int assists;
	private int steal;
	private int turnOver;
	private int offRebound;
	private int defRebound;
	private int block;
	private int faul;
	private int poeniTotal;
	private int skokTotal ;
	public int getPoeniTotal() {
		return poeniTotal;
	}
	public void setPoeniTotal(int poeniTotal) {
		this.poeniTotal = poeniTotal;
	}
	public int getSkokTotal() {
		return skokTotal;
	}
	public void setSkokTotal(int skokTotal) {
		this.skokTotal = skokTotal;
	}
	private boolean out = false;
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
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	public int getOnePointShoot() {
		return onePointShoot;
	}
	public void setOnePointShoot(int onePointShoot) {
		this.onePointShoot = onePointShoot;
	}
	public int getOnePointScore() {
		return onePointScore;
	}
	public void setOnePointScore(int onePointScore) {
		this.onePointScore = onePointScore;
	}
	public int getTwoPointShoot() {
		return twoPointShoot;
	}
	public void setTwoPointShoot(int twoPointShoot) {
		this.twoPointShoot = twoPointShoot;
	}
	public int getTwoPointScore() {
		return twoPointScore;
	}
	public void setTwoPointScore(int twoPointScore) {
		this.twoPointScore = twoPointScore;
	}
	public int getThreePointShoot() {
		return threePointShoot;
	}
	public void setThreePointShoot(int threePointShoot) {
		this.threePointShoot = threePointShoot;
	}
	public int getThreePointScore() {
		return threePointScore;
	}
	public void setThreePointScore(int threePointScore) {
		this.threePointScore = threePointScore;
	}
	public int getAssists() {
		return assists;
	}
	public void setAssists(int assists) {
		this.assists = assists;
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
	public int getOffRebound() {
		return offRebound;
	}
	public void setOffRebound(int offRebound) {
		this.offRebound = offRebound;
	}
	public int getDefRebound() {
		return defRebound;
	}
	public void setDefRebound(int defRebound) {
		this.defRebound = defRebound;
	}
	public int getBlock() {
		return block;
	}
	public void setBlock(int block) {
		this.block = block;
	}
	public int getFaul() {
		return faul;
	}
	public void setFaul(int faul) {
		this.faul = faul;
	}
	public boolean isOut() {
		return out;
	}
	public void setOut(boolean out) {
		this.out = out;
	}
	
	
}
