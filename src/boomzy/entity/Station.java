package boomzy.entity;

//用于选择始发与终到车站
public class Station {
	private String start;
	private String end;

	public Station() {

	}

	public Station(String start, String end) {
		this.start = start;
		this.end = end;
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

}
