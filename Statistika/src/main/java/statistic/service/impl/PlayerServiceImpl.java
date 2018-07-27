package statistic.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import statistic.model.Player;
import statistic.model.Team;
import statistic.repository.PlayerRepository;
import statistic.repository.TeamRepositiry;
import statistic.service.PlayerService;
@Service
@Transactional
public class PlayerServiceImpl implements PlayerService{

	@Autowired
	PlayerRepository plRe;
	@Autowired
	TeamRepositiry tr;
	@Override
	public boolean save(Player p) {

		Team t = tr.findOne(p.getTeam().getId());
		if(p.getTeam().getId() == null) {
			return false;
		}
		if(t.addPlayer(p)) {
			
			plRe.save(p);
			tr.save(t);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		plRe.delete(id);
	}

	@Override
	public Player findOne(long id) {
		// TODO Auto-generated method stub
		return plRe.findOne(id);
	}

	@Override
	public Page<Player> findAll(int pageNum) {
		// TODO Auto-generated method stub
		return plRe.findAll(
				new PageRequest(pageNum, 5));
	}

	@Override
	public void addOneShot(Long id) {
		Player p = plRe.findOne(id);
		p.setOnePointShoot((short) (p.getOnePointShoot() + 1));
		plRe.save(p);
	}

	@Override
	public void addOneScore(Long id) {
		Player p = plRe.findOne(id);
		p.setOnePointScore((short) (p.getOnePointScore()+1));
		p.setPoeniTotal(p.getPoeniTotal() + 1);
		plRe.save(p);
	}

	@Override
	public void addTwoShot(Long id) {
		Player p = plRe.findOne(id);
		p.setTwoPointShoot((short) (p.getTwoPointShoot() + 1));
		plRe.save(p);
		
	}

	@Override
	public void addTwoScore(Long id) {
		Player p = plRe.findOne(id);
		p.setTwoPointScore((short) (p.getTwoPointScore() + 1));
		p.setPoeniTotal(p.getPoeniTotal() + 2);
		plRe.save(p);
		
	}

	@Override
	public void addThreeShot(Long id) {
		Player p = plRe.findOne(id);
		p.setThreePointShoot((short) (p.getThreePointShoot() + 1));
		plRe.save(p);
		
	}

	@Override
	public void addThreeScore(Long id) {
		Player p = plRe.findOne(id);
		p.setThreePointScore((short) (p.getThreePointScore() + 1));
		p.setPoeniTotal(p.getPoeniTotal() + 3);
		plRe.save(p);
		
	}

	@Override
	public void addSteal(Long id) {
		Player p = plRe.findOne(id);
		p.setSteal((short) (p.getSteal() + 1));
		plRe.save(p);
		
	}

	@Override
	public void addTO(Long id) {
		Player p = plRe.findOne(id);
		p.setTurnOver((short) (p.getTurnOver() + 1));
		plRe.save(p);
		
	}

	@Override
	public void addBlock(Long id) {
		Player p = plRe.findOne(id);
		p.setBlock((short) (p.getBlock() + 1));
		plRe.save(p);
		
	}

	@Override
	public void addOffRebound(Long id) {
		Player p = plRe.findOne(id);
		p.setOffRebound((short) (p.getOffRebound() + 1));
		p.setSkokTotal(p.getSkokTotal() + 1);
		plRe.save(p);
		
	}

	@Override
	public void addDefRebound(Long id) {
		Player p = plRe.findOne(id);
		p.setDefRebound((short) (p.getDefRebound() + 1));
		p.setSkokTotal(p.getSkokTotal() + 1);
		plRe.save(p);
		
	}

	@Override
	public void addAssist(Long id) {
		Player p = plRe.findOne(id);
		p.setAssists((short) (p.getAssists() + 1));
		plRe.save(p);
		
	}

	@Override
	public void addFaul(Long id) {
		Player p = plRe.findOne(id);
		p.setFaul((short) (p.getFaul() + 1));
		if(p.getFaul() == 5) {
			p.setOut(true);
		}
		plRe.save(p);
		
	}

	@Override
	public Page<Player> findByTeamId(int pageNum, Long teamId) {
		// TODO Auto-generated method stub
		return plRe.findByTeamId(teamId, new PageRequest(pageNum, 12));
	}

}
