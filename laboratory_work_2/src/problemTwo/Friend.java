package problemTwo;

import java.util.Objects;

public class Friend {
	private String name;
	private int friendshipDuration;
	
	public Friend() {};
	
	public Friend(String name, int friendshipDuration) {
		this.name = name;
		this.friendshipDuration = friendshipDuration;
	}
	
	public String getName() {
		return name;
	}
	
	public int getFriendshipDuration() {
		return friendshipDuration;
	}
	
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (this.getClass() != o.getClass()) return false;
		Friend friend = (Friend)o;
		return (friendshipDuration == friend.friendshipDuration) && this.name.equals(friend.name);
	}
	
	public int hashCode() {
		return Objects.hash(name, friendshipDuration);
	}
	
	public String toString() {
		return "I know " + name + " for " + friendshipDuration + " years.";
	}
}
