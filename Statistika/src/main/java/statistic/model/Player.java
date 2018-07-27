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
	@ManyToOne(fetch=FetchType.EAGER)
	private Team team;
	@Column
	private int broj;
	@ManyToOne(fetch=FetchType.EAGER)
	private Pozicija pozicija;
	@Column
	private int onePointShoot;
	@Column
	private int onePointScore;
	@Column
	private int twoPointShoot;
	@Column
	private int twoPointScore;
	@Column
	private int threePointShoot;
	@Column
	private int threePointScore;
	@Column
	private int asistencije;
	@Column
	private int ukradene;
	@Column
	private int izgubljene;
	@Column
	private int skokNapad;
	@Column
	private int skokOdbrana;
	@Column
	private int rampa;
	@Column
	private int licna;
	@Column
	private boolean iskljucen = false;
	@Column
	private int poeniTotal;
	@Column
	private int skokTotal = skokOdbrana + skokNapad;
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
	public void fouledOut() {
		if(this.licna == 5) {
			this.iskljucen = true;
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
		if(team!=null && !team.getPlayers().contains(this)){
			team.getPlayers().add(this);
		}
	}

	public int getNumber() {
		return broj;
	}

	public void setNumber(int number) {
		this.broj = number;
	}

	public Pozicija getPosition() {
		return pozicija;
	}

	public void setPosition(Pozicija position) {
		this.pozicija = position;
		if(position!=null && !position.getPlayers().contains(this)){
			position.getPlayers().add(this);
		}
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
		return asistencije;
	}

	public void setAssists(int assists) {
		this.asistencije = assists;
	}

	public int getSteal() {
		return ukradene;
	}

	public void setSteal(int steal) {
		this.ukradene = steal;
	}

	public int getTurnOver() {
		return izgubljene;
	}

	public void setTurnOver(int turnOver) {
		this.izgubljene = turnOver;
	}

	public int getOffRebound() {
		return skokNapad;
	}

	public void setOffRebound(int offRebound) {
		this.skokNapad = offRebound;
	}

	public int getDefRebound() {
		return skokOdbrana;
	}

	public void setDefRebound(int defRebound) {
		this.skokOdbrana = defRebound;
	}

	public int getBlock() {
		return rampa;
	}

	public void setBlock(int block) {
		this.rampa = block;
	}

	public int getFaul() {
		return licna;
	}

	public void setFaul(int faul) {
		
		this.licna = faul;
	}

	public boolean isOut() {
		return iskljucen;
	}

	public void setOut(boolean out) {
		this.iskljucen = out;
	}
	
	
}
