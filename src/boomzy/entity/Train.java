package boomzy.entity;
//用于显示列车信息
public class Train {
	private String tno;
	private String start;
	private String end;
	private String trainType;
	private double startTime;
	private double endTime;
	private double price;
	private int ticketNum;

	public Train() {
		super();
	}

	public Train(String tno, String start, String end, String trainType, double startTime, double endTime, double price,
			int ticketNum) {
		super();
		this.tno = tno;
		this.start = start;
		this.end = end;
		this.trainType = trainType;
		this.startTime = startTime;
		this.endTime = endTime;
		this.price = price;
		this.ticketNum = ticketNum;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	public double getStartTime() {
		return startTime;
	}

	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	public double getEndTime() {
		return endTime;
	}

	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(int ticketNum) {
		this.ticketNum = ticketNum;
	}

	
	

}
