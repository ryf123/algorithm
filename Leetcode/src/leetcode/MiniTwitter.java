package leetcode;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.Static;

public class MiniTwitter {
	class Tweet {
		  public static int id;
		  public int user_id;
		  public String text;
		  public Tweet create(int user_id, String tweet_text) {
		    return new Tweet(user_id,tweet_text);
		  }
		  public Tweet(int user_id, String tweet_text){
		    this.user_id = user_id;
		    this.text = tweet_text;
		  }
	}
    public MiniTwitter() {
        // initialize your data structure here.
    }

    // @param user_id an integer
    // @param tweet a string
    // return a tweet
    public Tweet postTweet(int user_id, String tweet_text) {
        //  Write your code here
    }

    // @param user_id an integer
    // return a list of 10 new feeds recently
    // and sort by timeline
    public List<Tweet> getNewsFeed(int user_id) {
        // Write your code here
    }
        
    // @param user_id an integer
    // return a list of 10 new posts recently
    // and sort by timeline
    public List<Tweet>  getTimeline(int user_id) {
        // Write your code here
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id follows to_user_id
    public void follow(int from_user_id, int to_user_id) {
        // Write your code here
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id unfollows to_user_id
    public void unfollow(int from_user_id, int to_user_id) {
        // Write your code here
    }
}
