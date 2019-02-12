package ds;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class BinaryTree<T>{
    public TreeNode<T> root;
    
    /**
     * Insertion via preorder traversal of array 
     */
    public static <T> BinaryTree<T> fromArray(T [] lst){
        BinaryTree<T> tree = new BinaryTree<T>();
        tree.root = BinaryTree.levelOrderInsert(lst, 0);
        return tree;
    }
    
    public static <T> TreeNode<T> levelOrderInsert(T [] lst, int index){
        if(index >= lst.length) return null;
        TreeNode<T> n = new TreeNode<>(lst[index]);
        n.left = BinaryTree.levelOrderInsert(lst, index * 2 + 1);
        n.right = BinaryTree.levelOrderInsert(lst, index * 2 + 2);
        return n;
    }
    
    public List<T> bfs() {
    	List<T> lst = new LinkedList<>();
    	// use queue as backing data structure
    	MyQueue<TreeNode<T>> queue = new ListQueue<>();
    	queue.add(this.root);
    	while(!queue.isEmpty()) {
    		TreeNode<T> node = queue.remove();
    		// process
    		lst.add(node.value);
    		// add children if any to process next
    		if(node.left != null) queue.add(node.left);
    		if(node.right != null) queue.add(node.right);
    	}
    	return lst;
    }
    
    @Test
    public void testBfsEqualsLevelOrderInsertion() {
    	String[] lst = new String[] {"a","b","c","d","e","f"};
		BinaryTree<String> tree = BinaryTree.fromArray(lst);
		List<String> lst2 = tree.bfs();
    	for(int i=0; i<lst2.size(); i++) assertEquals(lst[i],lst2.get(i));
    }
}