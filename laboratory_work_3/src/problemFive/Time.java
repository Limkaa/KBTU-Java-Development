package problemFive;

public class Time implements Comparable<Object>{
	private int hours;
	private int minutes;
	private int seconds;
	
	public Time() {}
	public Time(int hours, int minutes, int seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	public double getHours() {
		return hours;
	}
	
	public double getMinutes() {
		return minutes;
	}
	
	public double getSeconds() {
		return seconds;
	}
	
	public int compareTo(Object object) {
		Time other = (Time) object;
        if (this.hours > other.getHours())
            return 1;
        else if (this.hours < other.getHours())
	        return -1;
        else 
        	if (this.minutes > other.getMinutes())
                return 1;
            else if (this.minutes < other.getMinutes())
    	        return -1;
            else 
            	if (this.seconds > other.getSeconds())
                    return 1;
                else if (this.seconds < other.getSeconds())
        	        return -1;
        return 0;
    }
	
	public String toString() {
		return hours + ":" + minutes + ":" + seconds;
	}
}
