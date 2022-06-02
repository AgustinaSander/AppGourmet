package domain;

import java.util.ArrayList;
import java.util.List;

import validations.Validation;

public class NotificationImpl implements INotification{
	
	private NotificationImpl() {};
	
	private static class NotificationImplHolder{
		private static final NotificationImpl notificationImpl = new NotificationImpl();
	}
	
	public static NotificationImpl getNotificationImpl() {
		return NotificationImplHolder.notificationImpl;
	}
	
	public List<Subscription> getRecipients(List<Subscription> subscriptions) {
		return subscriptions.stream().filter(subscription -> subscription.isNotification()).toList();
	}
	
	@Override
	public List<User> sendNotifications(Recipe recipe, RecipeBook recipeBook) {
		List<IProfile> profilesAllowedToEat = recipe.getProfilesAllowedToEat();
		List<Subscription> allSubscriptionsWithProfile = recipeBook.getSubscriptionsWithProfile(profilesAllowedToEat);
		
		List<Subscription> activeSubscriptionsWithProfile = getRecipients(allSubscriptionsWithProfile);
		
		List<User> usersThatReceivedEmail = new ArrayList<>();
		
		activeSubscriptionsWithProfile.stream().forEach(subscription -> {
			Email email = createEmail(subscription.getUser(), recipe, recipeBook);
			if(sendEmail(email)) usersThatReceivedEmail.add(subscription.getUser());
		});
		
		return usersThatReceivedEmail;
	}

	@Override
	public Email createEmail(User user, Recipe recipe, RecipeBook recipeBook) {
		String recipient = user.getEmail();
		String template = "Hello %s! A new recipe called %s was added to %s. We hope you enjoy it!";
		String content = String.format(template, recipient, recipe.getTitle(), recipeBook.getTitle());
		String subject = "New recipe available!";
		
		Email email = new Email(recipient, content, subject);
		//System.out.println(email);
		return email;
	}
	
	@Override
	public boolean sendEmail(Email email) {
		boolean dataIsValid = Validation.isValidEmailInformation(email);
		return dataIsValid;
	}

}
