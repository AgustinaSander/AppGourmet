package domain;

public class RankingSubscription {
	private int id;
	private Ranking ranking;
	private RecipeBook recipeBook;
	private boolean isActive;
	
	public RankingSubscription(Ranking ranking, RecipeBook recipeBook) {
		this.ranking = ranking;
		this.recipeBook = recipeBook;
		this.isActive = true;
	}

	public int getId() {
		return id;
	}

	public Ranking getRanking() {
		return ranking;
	}

	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}

	public RecipeBook getRecipeBook() {
		return recipeBook;
	}

	public void setRecipeBook(RecipeBook recipeBook) {
		this.recipeBook = recipeBook;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "RankingSubscription [id=" + id + ", ranking=" + ranking + ", recipeBook=" + recipeBook + ", isActive="
				+ isActive + "]";
	}
}
