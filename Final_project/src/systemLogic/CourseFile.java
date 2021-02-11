package systemLogic;

import java.io.Serializable;

public class CourseFile implements Serializable {

	private static final long serialVersionUID = 5L;
	
	private int id;
	private String name;
	private String description = "No notes";
	
	{
		Database.counterCourseFileId++;
		id = Database.counterCourseFileId;
	}
	
	public CourseFile() {}
	public CourseFile(String name, String description) {
		this.name = name;
		this.description = description;
	}
	 
	// Getting course file id
	public int getId() {
		return id;
	}
	
	// Getting file name
	public String getName() {
		return name;
	}
	
	// Setting the file name
	public void setName(String name) {
		this.name = name;
	}
	
	// Getting file description
	public String getDescription() {
		return description;
	}
	
	// Setting a new file description
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CourseFile))
			return false;
		CourseFile other = (CourseFile) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "CourseFile [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
}
