package leetcode;

import java.util.*;


public class WebPageCrawler {
    HashSet<String> map = new HashSet<String>();
    Queue<String> queue = new LinkedList<String>();
    ArrayList<String> ret;
    Random random = new Random();
    public WebPageCrawler(){
    	
    }
    public WebPageCrawler(String url) {

        ret = new ArrayList<String>();
        addQueue(url);
	}
    private  synchronized String pollQueue(){

    		if(!this.queue.isEmpty())
    			return this.queue.poll();
    		return "";
    }
    private synchronized void addQueue(String child_url){
    		if(!map.contains(child_url) && child_url.contains("wikipedia.org")){
            queue.add(child_url);
        }
        map.add(child_url);    	
    }
    private void crawl(){
    		Thread thread = Thread.currentThread();
    		System.out.printf("Thread: %s\n",thread.getName());
        String temp;
        int tries = 100;
        	while(!queue.isEmpty() || tries > 0){
        		
        		if(queue.isEmpty()){
        			try {
        				Thread.sleep(random.nextInt(10));
        				tries--;
        			} catch (InterruptedException e) {
        				e.printStackTrace();
        			}        			
        		}
        		temp = pollQueue();
        		if(!temp.equals("")){
        			System.out.printf("Thread: %s crawling\n",thread.getName());
                ret.add(temp);
                for(String child_url:HtmlHelper.parseUrls(temp)){
                		this.addQueue(child_url);

                }        			
        		}
        }
    }
    public List<String> crawler(String url) {

        final WebPageCrawler wc = new WebPageCrawler(url);
        int size = 100;
        Thread[] threads = new Thread[size];
        for(int i=0; i < threads.length; i++){
        			threads[i] = new Thread(new Runnable() {
        			public void run() {
					wc.crawl();
        			}
        			
        			});
        			threads[i].start();
        			threads[i].setName(Integer.toString(i));
        }
        for(Thread t:threads){
        		try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        return wc.ret;
    }
    public static void main(String[] args) {
		WebPageCrawler wCrawler = new WebPageCrawler();
		for(String ret:wCrawler.crawler("wikipedia.org1")){
			System.out.println(ret);
		}
	}
}
class HtmlHelper{
	public static ArrayList<String> parseUrls(String url){
		Random random = new Random();
		ArrayList<String> ret = new ArrayList<String>();
		if(url.equals("wikipedia.org1"))
			ret.add("wikipedia.org2");
		if(url.equals("wikipedia.org2"))
			ret.add("wikipedia.org3");
		if(url.equals("wikipedia.org3"))
			ret.add("wikipedia.org4");
		if(url.equals("wikipedia.org4"))
			ret.add("wikipedia.org5");
		if(url.equals("wikipedia.org5"))
			ret.add("wikipedia.org6");
		if(url.equals("wikipedia.org6"))
			ret.add("wikipedia.org7");
		if(url.equals("wikipedia.org7"))
			ret.add("wikipedia.org8");
		if(url.equals("wikipedia.org8"))
			ret.add("wikipedia.org9");
		try {
			Thread.sleep(random.nextInt(500));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
}
