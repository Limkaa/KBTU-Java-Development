package problemTwo;
import java.util.HashSet;

public class Main {
	public static void main(String args[]) {
		HashSet<Friend> friends = new HashSet<Friend>();
		
		friends.add(new Friend("Mark", 10));
		friends.add(new Friend("Mark", 10));
		friends.add(new BestFriend("Tony", 20, "We have some secret"));
		friends.add(new BestFriend("Tony", 20, "We have some secret"));
		System.out.println(friends);
	}
}
