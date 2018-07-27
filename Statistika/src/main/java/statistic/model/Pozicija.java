package statistic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table
public class Pozicija {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String name;
	@OneToMany(mappedBy="pozicija",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Player> players = new ArrayList<>();;
	
	public boolean addPlayer(Player p) {
		if(this.players.size() >= 12) {
			return false;
		}
		this.players.add(p);
		if(!this.equals(p.getPosition())){
			p.setPosition(this);
		}
		return true;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
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
	
}
