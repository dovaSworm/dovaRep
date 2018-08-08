package statistic.web.dto;

public class BallGameDTO {

	private Long id;
	private Long hostId;
	private Long guestId;
	private int hostPoints;
	private int guestPoints;
	private int hostTimeOut;
	private int guestTimeOut;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getHostId() {
		return hostId;
	}
	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}
	public Long getGuestId() {
		return guestId;
	}
	public void setGuestId(Long guestId) {
		this.guestId = guestId;
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
