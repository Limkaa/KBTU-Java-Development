package problemTwo;

import java.util.Objects;

public class BestFriend extends Friend {
	private String secret;

	public BestFriend(String name, int friendshipDuration, String secret) {
		super(name, friendshipDuration);
		this.secret = secret;
	}
	
	public void tellSecret(String secret) {
		this.secret = secret;
	}
	
	public boolean equals(Object o) {
		if (!super.equals(o)) return false;
		BestFriend bf = (BestFriend)o; 
		return this.secret.equals(bf.secret);
	}
	
	public int hashCode() {
		return Objects.hash(super.getName(), super.getFriendshipDuration(), secret);
	}
	
	public String toString() {
		return super.toString() + " We are bestfriends. Our secret: " + secret;
	}
}
