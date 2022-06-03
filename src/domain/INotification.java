package domain;

import java.util.List;

public interface INotification {
	public abstract List<Subscription> getRecipients(List<Subscription> subscriptions);
	public abstract List<User> notificateUsersAboutRecipe(List<Subscription> subscriptions, Recipe recipe, RecipeBook recipeBook);
	public abstract Email createEmailNotification(User user, Recipe recipe, RecipeBook recipeBook);
	public abstract boolean sendEmailNotification(Email email);
}
