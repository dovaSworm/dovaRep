package statistic.web.dto;

public class PlayerDTO {

	private Long id;
	private String name;
	private Long teamId;
	private String teamName;
	private int jerseyNmber;
	private Long positionId;
	private String positionName;

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

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getJerseyNmber() {
		return jerseyNmber;
	}

	public void setJerseyNmber(int jerseyNmber) {
		this.jerseyNmber = jerseyNmber;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

}
