package statistic;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import statistic.model.Player;
import statistic.model.Pozicija;
import statistic.model.Team;
import statistic.service.PlayerService;
import statistic.service.PositionService;
import statistic.service.TeamService;

@Component
public class TestData {
	
	@Autowired
	private TeamService tmS;
	@Autowired
	private PositionService poS;
	@Autowired
	private PlayerService plS;
	
	@PostConstruct
	public void init() {
	
	Pozicija g = new Pozicija();
	g.setName("guard");
	poS.save(g);
	Pozicija f = new Pozicija();
	f.setName("krilo");
	poS.save(f);
	Pozicija c = new Pozicija();
	c.setName("centar");
	poS.save(c);
	for(int i = 1; i < 3; i++) {
		Team t1 = new Team();
		t1.setName("tim " + i);
		t1.setCity("grad "+ i);
		t1.setCoach("trener "+ i);
		t1.setCountry("zemlja "+ i);
		tmS.save(t1);

		for(int j = 1; j < 5; j++) {
			Player p = new Player();
			p.setName("ime igraca " + j);
			p.setNumber(j);
			p.setPosition(g);
			p.setTeam(t1);
			plS.save(p);
			t1.addPlayer(p);

		}
		for(int j = 5; j <9; j++) {
			Player p = new Player();
			p.setName("ime igraca " + j);
			p.setNumber(j);
			p.setPosition(f);
			p.setTeam(t1);
			plS.save(p);
			t1.addPlayer(p);
		}
		for(int j = 9; j < 13; j++) {
			Player p = new Player();
			p.setName("ime igraca " + j);
			p.setNumber(j);
			p.setPosition(c);
			p.setTeam(t1);
			plS.save(p);
			t1.addPlayer(p);
		}
		tmS.save(t1);

	}
	}

}
