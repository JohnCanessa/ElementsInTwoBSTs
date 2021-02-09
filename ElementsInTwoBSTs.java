import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;


/**
 * LeetCode 1305. All Elements in Two Binary Search Trees
 * https://leetcode.com/problems/all-elements-in-two-binary-search-trees/
 */
public class ElementsInTwoBSTs {


    /**
     * Traverse BST adding tree node values to the list.
     * Recursive O(n)
     */
    static void traverse0(TreeNode root, List<Integer> lst) {
        if (root != null) {

            // **** visit left sub tree ****
            traverse0(root.left, lst);

            // **** ****
            lst.add(root.val);

            // **** visit right sub tree ****
            traverse0(root.right, lst);
        }
    }


    /**
     * Return a list containing all the integers from 
     * both trees sorted in ascending order.
     * 
     * Runtime: 13 ms, faster than 84.28% of Java online submissions.
     * Memory Usage: 41.5 MB, less than 91.33% of Java online submissions.
     */
    static List<Integer> getAllElements0(TreeNode root1, TreeNode root2) {
        
        // **** initialization ****
        List<Integer> lst = new ArrayList<>();
        
        // **** traverse root1 adding elements to the list ****
        traverse0(root1, lst);

        // **** traverse root2 adding elements to the list ****
        traverse0(root2, lst);

        // **** sort array list ****
        Collections.sort(lst);

        // **** return ****
        return lst;
    }


    /**
     * Traverse BST adding tree node values to the priority queue.
     * Recursive O(n)
     */
    static void traverse1(TreeNode root, PriorityQueue<Integer> pq) {
        if (root != null) {

            // **** visit left sub tree ****
            traverse1(root.left, pq);

            // **** ****
            pq.add(root.val);

            // **** visit right sub tree ****
            traverse1(root.right, pq);
        }
    }


    /**
     * Return a list containing all the integers from 
     * both trees sorted in ascending order.
     * 
     * Runtime: 33 ms, faster than 12.92% of Java online submissions.
     * Memory Usage: 41.4 MB, less than 95.16% of Java online submissions.
     */
    static List<Integer> getAllElements1(TreeNode root1, TreeNode root2) {
        
        // **** initialization ****
        List<Integer> lst           = new ArrayList<>();
        PriorityQueue<Integer> pq   = new PriorityQueue<>((a,b) -> a - b);
        
        // **** traverse root1 adding elements to the priority queue ****
        traverse1(root1, pq);

        // **** traverse root2 adding elements to the priority queue ****
        traverse1(root2, pq);

        // **** populate the list using the priority queue ****
        while (!pq.isEmpty())
            lst.add(pq.remove());

        // **** return the list ****
        return lst;
    }


    // **** holds elements from both binary trees ****
    static List<Integer> al;


    /**
     * Traverse BST adding tree node values to the priority queue.
     * Recursive O(n)
     */
    static void traverse(TreeNode root) {

        // **** end condition ****
        if (root == null)
            return;

        // **** visit left sub tree ****
        traverse(root.left);

        // **** ****
        al.add(root.val);

        // **** visit right sub tree ****
        traverse(root.right);
    }


    /**
     * Return a list containing all the integers from 
     * both trees sorted in ascending order.
     * 
     * Runtime: 13 ms, faster than 84.28% of Java online submissions.
     * Memory Usage: 41.7 MB, less than 82.99% of Java online submission.
     */
    static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        
        // **** initialization ****
        al = new ArrayList<>();

        // **** traverse root1 adding elements to the priority queue ****
        traverse(root1);

        // **** traverse root2 adding elements to the priority queue ****
        traverse(root2);

        // **** sort the list ****
        Collections.sort(al);

        // **** return the list ****
        return al;
    }


    /**
     * Test scafolding
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** initialization ****
        String buffer;

        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read data for first BST ****
        buffer = br.readLine().trim();

        // **** populate String[] ****
        String[] strArr1 = null;
        if (!buffer.equals(""))
            strArr1 = buffer.split(",");
        else
            strArr1 = new String[0];

        // **** read data for second BST ****
        buffer = br.readLine().trim();

        // **** populate String[] ****
        String[] strArr2 = null;
        if (!buffer.equals(""))
            strArr2 = buffer.split(",");
        else
            strArr2 = new String[0];

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< strArr1: " + Arrays.toString(strArr1));
        System.out.println("main <<< strArr2: " + Arrays.toString(strArr2));

        // **** convert String[] to Integer[] ****
        Integer[] arr1 = new Integer[strArr1.length];
        for (int i = 0; i < arr1.length; i++) {
            if (strArr1[i].equals("null"))
                arr1[i] = null;
            else
                arr1[i] = Integer.parseInt(strArr1[i]);
        }

        // ???? ????
        System.out.println("main <<<    arr1: " + Arrays.toString(arr1));

        // **** convert String[] to Integer[] ****
        Integer[] arr2 = new Integer[strArr2.length];
        for (int i = 0; i < arr2.length; i++) {
            if (strArr2[i].equals("null"))
                arr2[i] = null;
            else
                arr2[i] = Integer.parseInt(strArr2[i]);
        }

        // ???? ????
        System.out.println("main <<<    arr2: " + Arrays.toString(arr2));

        // **** create and populate the root1 BST ****
        BST root1 = new BST();
        root1.root = root1.populate(arr1);
    
        // ???? ????
        System.out.println("main <<< root1 levelOrder:");
        System.out.println(root1.levelOrder());

        // **** create and populate the root2 BST ****
        BST root2 = new BST();
        root2.root = root1.populate(arr2);
    
        // ???? ????
        System.out.println("main <<< root2 levelOrder:");
        System.out.println(root2.levelOrder());

        // **** generate and display the list ****
        System.out.println("main <<< getAllElements: " + 
                            getAllElements1(root1.root, root2.root));

        // **** generate and display the list ****
        System.out.println("main <<< getAllElements: " + 
                            getAllElements0(root1.root, root2.root));

        // **** generate and display the list ****
        System.out.println("main <<< getAllElements: " + 
                            getAllElements(root1.root, root2.root));
    }

}