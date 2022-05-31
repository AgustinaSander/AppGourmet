package domain;

import java.util.List;

public interface INotification {
	public abstract Email createEmail(User user, Recipe recipe, RecipeBook recipeBook);
	public abstract List<User> sendNotifications(Recipe recipe, RecipeBook recipeBook);
	public abstract boolean sendEmail(Email email);
}
