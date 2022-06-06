package domain;

import java.util.ArrayList;
import java.util.List;

public class EmailNotification implements INotification{
	
	private EmailNotification() {};
	
	private static class EmailNotificationHolder{
		private static final EmailNotification emailNotification = new EmailNotification();
	}
	
	public static EmailNotification getEmailNotification() {
		return EmailNotificationHolder.emailNotification;
	}
	
	@Override
	public List<Subscription> getRecipients(List<Subscription> subscriptions) {
		return subscriptions.stream().filter(subscription -> subscription.isNotification()).toList();
	}
	
	@Override
	public List<User> notificateUsersAboutRecipe(List<Subscription> subscriptions, Recipe recipeAdded, RecipeBook recipeBook) {
		List<Subscription> activeSubscriptionsWithProfile = getRecipients(subscriptions);
		
		List<User> usersThatReceivedEmail = new ArrayList<>();
		
		activeSubscriptionsWithProfile.stream().forEach(subscription -> {
			Email email = createEmailNotification(subscription.getUser(), recipeAdded, recipeBook);
			if(sendEmailNotification(email)) usersThatReceivedEmail.add(subscription.getUser());
		});
		
		return usersThatReceivedEmail;
	}

	@Override
	public Email createEmailNotification(User user, Recipe recipe, RecipeBook recipeBook) {
		String recipient = user.getEmail();
		String template = "Hello %s! A new recipe called %s was added to %s. We hope you enjoy it!";
		String content = String.format(template, recipient, recipe.getTitle(), recipeBook.getTitle());
		String subject = "New recipe available!";
		
		Email email = new Email(recipient, content, subject);
		//System.out.println(email);
		return email;
	}
	
	@Override
	public boolean sendEmailNotification(Email email) {
		return true;
	}

}
