class Tweet {
		  public static int id=0;
		  public int user_id;
		  public String text;
		  public static Tweet create(int user_id, String tweet_text) {
		    return new Tweet(user_id,tweet_text);
		  }
		  public Tweet(int user_id, String tweet_text){
		    this.user_id = user_id;
		    this.text = tweet_text;
		  }
}