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
	@ManyToOne(fetch = FetchType.LAZY)
	private Team team;
	@Column
	private int jerseyNumber;
	@ManyToOne(fetch = FetchType.LAZY)
	private PlayingPosition playingPosition;
	@Column
	private boolean onRoster = true;

	public int getJerseyNumber() {
		return jerseyNumber;
	}

	public void setJerseyNumber(int jerseyNumber) {
		this.jerseyNumber = jerseyNumber;
	}

	public boolean isOnRoster() {
		return onRoster;
	}

	public void setOnRoster(boolean onRoster) {
		this.onRoster = onRoster;
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

}
