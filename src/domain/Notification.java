package domain;

import java.util.List;

public interface Notification {
	public abstract void sendNotificationToUsers(List<Profile> profiles);
}
