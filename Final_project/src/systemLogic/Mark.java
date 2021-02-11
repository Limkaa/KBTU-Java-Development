package systemLogic;

import java.io.Serializable;

public class Mark implements Serializable {
	
	private static final long serialVersionUID = 9L;
	
	private double digitMark = 0;
	private int score = 0;
	private String textMark = "none";
	
	public Mark() {}
	public Mark(int score) {
		this.score = score;
		transformScore(score);
	}
	
	// Getting a numerical estimate
	public double getDigitMark() {
		return digitMark;
	}
	
	// Getting the number of points
	public int getScore() {
		return score;
	}
	
	// Setting the number of points
	public void setScore(int score) {
		this.score = score;
		transformScore(score);
	}
	
	// Getting a letter grade
	public String getTextMark() {
		return textMark;
	}
	
	// Converting the number of points into numerical and letter grade
	public void transformScore(int score) {
		if (95 <= score) {
			this.digitMark = 4.0; this.textMark = "A";
		} 
		else if (90 <= score) {
			this.digitMark = 3.67; this.textMark = "A-";
		}
		else if (85 <= score) {
			this.digitMark = 3.33; this.textMark = "B+";
		}
		else if (80 <= score) {
			this.digitMark = 3.0; this.textMark = "B";
		}
		else if (75 <= score) {
			this.digitMark = 2.67; this.textMark = "B-";
		}
		else if (70 <= score) {
			this.digitMark = 2.33; this.textMark = "C+";
		}
		else if (65 <= score) {
			this.digitMark = 2.0; this.textMark = "C";
		}
		else if (60 <= score) {
			this.digitMark = 1.67; this.textMark = "C-";
		}
		else if (55 <= score) {
			this.digitMark = 1.33; this.textMark = "D+";
		}
		else if (50 <= score) {
			this.digitMark = 1.0; this.textMark = "D-";
		}
		else if (25 <= score) {
			this.digitMark = 0.5; this.textMark = "FX";
		} 
		else {
			this.digitMark = 0.0; this.textMark = "F (retake)";
		}
	}
	
	@Override
	public String toString() {
		return "Mark [digitMark=" + digitMark + ", score=" + score + ", textMark=" + textMark + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(digitMark);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + score;
		result = prime * result + ((textMark == null) ? 0 : textMark.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Mark))
			return false;
		Mark other = (Mark) obj;
		if (Double.doubleToLongBits(digitMark) != Double.doubleToLongBits(other.digitMark))
			return false;
		if (score != other.score)
			return false;
		if (textMark == null) {
			if (other.textMark != null)
				return false;
		} else if (!textMark.equals(other.textMark))
			return false;
		return true;
	}
	
}
