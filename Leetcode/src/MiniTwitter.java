import java.util.*;

public class MiniTwitter {
	private HashMap<Integer, HashSet<Integer>> followedMap;
	private HashMap<Integer, HashSet<Integer>> followMap;
	private HashMap<Integer, Stack<Tweet>> msgQ; 
    public MiniTwitter() {
        // initialize your data structure here.
    		followedMap = new HashMap<Integer,HashSet<Integer>>();
    		msgQ = new HashMap<Integer, Stack<Tweet>>();
    		followMap = new HashMap<Integer, HashSet<Integer>>();
    }

    // @param user_id an integer
    // @param tweet a string
    // return a tweet
    public Tweet postTweet(int user_id, String tweet_text) {
        //  Write your code here
    		Tweet t = Tweet.create(user_id, tweet_text);
    		if(!msgQ.containsKey(user_id)){
    			msgQ.put(user_id, new Stack<Tweet>());
    			
    		}
    		msgQ.get(user_id).add(t);
    		return t;
    }

    // @param user_id an integer
    // return a list of 10 new feeds recently
    // and sort by timeline
    public List<Tweet> getNewsFeed(int user_id) {
        // Write your code here
    		ArrayList<Tweet> ret = new ArrayList<Tweet>();
    		ret.addAll(this.getTimeline(user_id));
    		if(!followMap.containsKey(user_id))
    			return ret;
    			
    		for(Integer follow:followMap.get(user_id)){
    			ret.addAll(this.getTimeline(follow));
    		}
    		Collections.sort(ret, new Comparator<Tweet>() {

				@Override
				public int compare(Tweet o1, Tweet o2) {
					// TODO Auto-generated method stub
					return o2.id - o1.id;
				}
			});
    		if(ret.size() <= 10)
    			return ret;
    		return ret.subList(ret.size()-10, ret.size());
    }
        
    // @param user_id an integer
    // return a list of 10 new posts recently
    // and sort by timeline
    public List<Tweet>  getTimeline(int user_id) {
		ArrayList<Tweet> ret = new ArrayList<Tweet>();
		if(!msgQ.containsKey(user_id))
			return ret;
		for(int i = msgQ.get(user_id).size()-1; i >=0 ; i--){
			ret.add(msgQ.get(user_id).get(i));
			if(ret.size() == 10)
				break;				
		}
		return ret;
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id follows to_user_id
    public void follow(int from_user_id, int to_user_id) {
        // Write your code here
    		if(!this.followedMap.containsKey(to_user_id))
    			this.followedMap.put(to_user_id, new HashSet<Integer>());
    		this.followedMap.get(to_user_id).add(from_user_id);
    		if(!this.followMap.containsKey(from_user_id))
    			this.followMap.put(from_user_id, new HashSet<Integer>());
    		this.followMap.get(from_user_id).add(to_user_id);
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id unfollows to_user_id
    public void unfollow(int from_user_id, int to_user_id) {
        // Write your code here
    		this.followedMap.get(to_user_id).remove(from_user_id);
    		this.followMap.get(from_user_id).remove(to_user_id);
    }
    public static void main(String[] args) {
		
	}
}