import java.util.*;

class NaryTreeNode{
	int val;
	List<NaryTreeNode> children;
	public NaryTreeNode(int val) {
		this.val = val;
		this.children = new ArrayList<NaryTreeNode>();
	}
}
public class NaryTree {
	String serialize(NaryTreeNode root){
		if(root ==  null)
			return "";
		StringBuilder sb = new StringBuilder();
		sb.append(root.val);
		sb.append("(");
		for(int i = 0;i<root.children.size();i++){
			NaryTreeNode child = root.children.get(i);
			sb.append(serialize(child));
		}
		sb.append(")");
		return sb.toString();
	}
	NaryTreeNode deserialize(String str){
		if(str.equals(""))
			return null;
		NaryTreeNode root = null;
		int base = 0;
		Stack<NaryTreeNode> stack = new Stack<>();
		boolean digit = false;
		for(int i=0;i<str.length();i++){
			char c = str.charAt(i);
			// if it's digit
			if(c >='0' && c <='9'){
				base *= 10;
				base += (c - '0');
				digit = true;
			}
			else{
				NaryTreeNode node = new NaryTreeNode(base);
				if(stack.size() > 0){
					if(digit)
						stack.peek().children.add(node);
				}
				else{
					root = node;
				}
				// push current node to stack
				if(c == '('){
					stack.push(node);
				}
				else if(c == ')'){
					stack.pop();
				}
				// do nothing for ','
				// reset base
				base = 0 ;
				digit = false;
			}
		}
		return root;
	}
	public static void main(String[] args) {
		NaryTree nTree = new NaryTree();
		NaryTreeNode root = new NaryTreeNode(100);
		NaryTreeNode node1 = new NaryTreeNode(99);
		NaryTreeNode node2 = new NaryTreeNode(7777);
		node1.children.add(node2);
		root.children.add(node1);
		root.children.add(new NaryTreeNode(88));
		String string = nTree.serialize(root);
		System.out.println(string);
		root = nTree.deserialize(string);
		Queue<NaryTreeNode> queue = new LinkedList<NaryTreeNode>();
		queue.offer(root);
		while(queue.size() > 0){
			int l = queue.size();
			for(int i=0;i<l;i++){
				NaryTreeNode node = queue.poll();
				System.out.print(node.val);
				for(NaryTreeNode child:node.children){
					queue.offer(child);
				}
				if(i != l-1)
					System.out.print(",");
			}
			System.out.println();
		}
		
	}
}
