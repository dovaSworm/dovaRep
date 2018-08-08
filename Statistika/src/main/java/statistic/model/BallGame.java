package statistic.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class BallGame {
	@Id
	@Column
	@GeneratedValue
	private Long id;
	@JoinColumn(name = "host_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Team host;
	@Transient
	private List<PlayerInGame> hostPlayers = new ArrayList<>();
	@Transient
	private List<PlayerInGame> guestPlayers = new ArrayList<>();
	@JoinColumn(name = "guest_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Team guest;
	@Column
	private int hostPoints;
	@Column
	private int guestPoints;
	@Column
	private int hostTimeOut = 3;
	@Column
	private int guestTimeOut = 3;
	
	public void addHostPlayerInGame(PlayerInGame p) {
		this.hostPlayers.add(p);
		if(!this.equals(p.getGame())) {
			p.setGame(this);
		}
	}
	public void addGuestPlayerInGame(PlayerInGame p) {
		this.guestPlayers.add(p);
		if(!this.equals(p.getGame())) {
			p.setGame(this);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Team getHost() {
		return host;
	}

	public void setHost(Team host) {
		this.host = host;
	}

	public Team getGuest() {
		return guest;
	}

	public void setGuest(Team guest) {
		this.guest = guest;
	}

	public int getHostPoints() {
		return hostPoints;
	}

	public void setHostPoints(int hostPoints) {
		this.hostPoints = hostPoints;
	}

	public int getGuestPoints() {
		return guestPoints;
	}

	public void setGuestPoints(int guestPoints) {
		this.guestPoints = guestPoints;
	}

	public List<PlayerInGame> getHostPlayers() {
		return hostPlayers;
	}

	public void setHostPlayers(List<PlayerInGame> hostPlayers) {
		this.hostPlayers = hostPlayers;
	}

	public List<PlayerInGame> getGuestPlayers() {
		return guestPlayers;
	}

	public void setGuestPlayers(List<PlayerInGame> guestPlayers) {
		this.guestPlayers = guestPlayers;
	}

	public int getHostTimeOut() {
		return hostTimeOut;
	}

	public void setHostTimeOut(int hostTimeOut) {
		this.hostTimeOut = hostTimeOut;
	}

	public int getGuestTimeOut() {
		return guestTimeOut;
	}

	public void setGuestTimeOut(int guestTimeOut) {
		this.guestTimeOut = guestTimeOut;
	}


}
