package systemLogic;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogFile implements Serializable {
	
	private static final long serialVersionUID = 8L;
	
	private int logId;
	private LogType type;
	private String actionTime;
	
	{
		Database.counterLogFileId++;
		logId = Database.counterLogFileId;
	}
	
	public LogFile() {}
	public LogFile(LogType type) {
		this.type = type;
		
		LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    
		this.actionTime = formattedDate;
	}
	
	// Getting the id of the log
	public int getLogId() {
		return logId;
	}
	
	// Getting the log type
	public LogType getType() {
		return type;
	}
	
	// Getting log creation time
	public String getActionTime() {
		return actionTime;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actionTime == null) ? 0 : actionTime.hashCode());
		result = prime * result + logId;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof LogFile))
			return false;
		LogFile other = (LogFile) obj;
		if (actionTime == null) {
			if (other.actionTime != null)
				return false;
		} else if (!actionTime.equals(other.actionTime))
			return false;
		if (logId != other.logId)
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "LogFile [logId=" + logId + ", type=" + type + ", actionTime=" + actionTime + "]";
	}
}
