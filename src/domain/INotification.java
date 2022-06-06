package domain;

import java.util.List;

public interface INotification {
	public List<Subscription> getRecipients(List<Subscription> subscriptions);
	public List<User> notificateUsersAboutRecipe(List<Subscription> subscriptions, Recipe recipe, RecipeBook recipeBook);
	public Email createEmailNotification(String sender, User user, Recipe recipe, RecipeBook recipeBook);
	public boolean sendEmailNotification(Email email);
}
