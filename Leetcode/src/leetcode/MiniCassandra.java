package leetcode;
import java.util.*;
public class MiniCassandra {
    HashMap<String,TreeMap<Integer,Column>> map;
    public MiniCassandra() {
        // initialize your data structure here.
        this.map = new HashMap<String,TreeMap<Integer,Column>>();
    }
    
    /**
     * @param raw_key a string
     * @param column_start an integer
     * @param column_end an integer
     * @return void
     */
    public void insert(String raw_key, int column_key, String column_value) {
        // Write your code here
        Column col = new Column(column_key,column_value);
        if(this.map.containsKey(raw_key))
            this.map.get(raw_key).put(column_key,col);
        else{
        		TreeMap<Integer, Column> treeMap = new TreeMap<Integer, Column>();
            treeMap.put(column_key, col);
            this.map.put(raw_key,treeMap);
        }
    }

    /**
     * @param raw_key a string
     * @param column_start an integer
     * @param column_end an integer
     * @return a list of Columns
     */
    public List<Column> query(String raw_key, int column_start, int column_end) {
        // Write your code here
        ArrayList<Column> ret = new ArrayList<Column>();
        if(!this.map.containsKey(raw_key))
            return ret;

        for(Map.Entry<Integer, Column> entry:this.map.get(raw_key).subMap(column_start, true, column_end, true).entrySet()){
        		ret.add(entry.getValue());
        }
        return ret;
    }
}
