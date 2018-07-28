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
		p.setOnePointShot((p.getOnePointShot() + 1));
		plRe.save(p);
	}

	@Override
	public void addOneScore(Long id) {
		Player p = plRe.findOne(id);
		p.setOnePointScore((p.getOnePointScore()+1));
		p.setPoeniTotal(p.getPoeniTotal() + 1);
		plRe.save(p);
	}

	@Override
	public void addTwoShot(Long id) {
		Player p = plRe.findOne(id);
		p.setTwoPointShot((p.getTwoPointShot() + 1));
		plRe.save(p);
		
	}

	@Override
	public void addTwoScore(Long id) {
		Player p = plRe.findOne(id);
		p.setTwoPointScore((p.getTwoPointScore() + 1));
		p.setPoeniTotal(p.getPoeniTotal() + 2);
		plRe.save(p);
		
	}

	@Override
	public void addThreeShot(Long id) {
		Player p = plRe.findOne(id);
		p.setThreePointShot((p.getThreePointShot() + 1));
		plRe.save(p);
		
	}

	@Override
	public void addThreeScore(Long id) {
		Player p = plRe.findOne(id);
		p.setThreePointScore((p.getThreePointScore() + 1));
		p.setPoeniTotal(p.getPoeniTotal() + 3);
		plRe.save(p);
		
	}

	@Override
	public void addSteal(Long id) {
		Player p = plRe.findOne(id);
		p.setSteal((p.getSteal() + 1));
		plRe.save(p);
		
	}

	@Override
	public void addTO(Long id) {
		Player p = plRe.findOne(id);
		p.setTurnOver((p.getTurnOver() + 1));
		plRe.save(p);
		
	}

	@Override
	public void addBlock(Long id) {
		Player p = plRe.findOne(id);
		p.setBlockShot((p.getBlockShot() + 1));
		plRe.save(p);
		
	}

	@Override
	public void addOffRebound(Long id) {
		Player p = plRe.findOne(id);
		p.setReboundOff((p.getReboundOff() + 1));
		p.setTotalRebounds(p.getTotalRebounds() + 1);
		plRe.save(p);
		
	}

	@Override
	public void addDefRebound(Long id) {
		Player p = plRe.findOne(id);
		p.setReboundDef((p.getReboundDef() + 1));
		p.setTotalRebounds(p.getTotalRebounds() + 1);
		plRe.save(p);
		
	}

	@Override
	public void addAssist(Long id) {
		Player p = plRe.findOne(id);
		p.setAssist((p.getAssist() + 1));
		plRe.save(p);
		
	}

	@Override
	public void addFaul(Long id) {
		Player p = plRe.findOne(id);
		p.setPersonalFaul((short) (p.getPersonalFaul() + 1));
		if(p.getPersonalFaul() == 5) {
			p.setFouledOut(true);
		}
		plRe.save(p);
		
	}

	@Override
	public Page<Player> findByTeamId(int pageNum, Long teamId) {
		// TODO Auto-generated method stub
		return plRe.findByTeamId(teamId, new PageRequest(pageNum, 12));
	}

}
