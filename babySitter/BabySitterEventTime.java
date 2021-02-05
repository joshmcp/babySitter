package babySitter;

public class BabySitterEventTime implements Comparable<BabySitterEventTime>{

	private String timeDisplayValue;
	private Integer timeId;
	
	public BabySitterEventTime(int timeId, String timeDisplayValue) {
		this.timeId = timeId;
		this.timeDisplayValue = timeDisplayValue;
	}
	
	public Integer getTimeId() {
		return timeId;
	}
	
	@Override
	public String toString() {
		return timeDisplayValue;
	}
	@Override
	public int compareTo(BabySitterEventTime time) {
		return (int)(this.timeId - time.getTimeId());
	}
}