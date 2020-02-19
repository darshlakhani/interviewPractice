package com.darshit.leet;

import java.util.Arrays;
import java.util.Stack;

public class VerifyPreorderSeqBST {

    public static void main (String[] args) {
        int preOrder[] = {5,6,1,3};
        System.out.println("Verifying Preorder of BST: ");
        System.out.println(verifyPreOrder(preOrder));
    }

    public static boolean verifyPreOrder(int[] preorder) {
        int currentRoot = Integer.MIN_VALUE;

        Stack<Integer> leftSub = new Stack<>();
        for (int i=0; i<preorder.length; i++) {
            //if preorder value is less than peek() it means preorder is part of sub tree of current root value else we need to find new current root.
            if (leftSub.isEmpty() || leftSub.peek() > preorder[i]) {
                // right tree cannot have value less than root.
                if (preorder[i] < currentRoot) {
                    return false;
                }
            } else {
                // find root node of sub tree; greater preoder value than peek helps determine the root of current sub tree.
                while (!leftSub.isEmpty() && leftSub.peek() < preorder[i]) {
                    currentRoot = leftSub.pop();
                }
            }
            leftSub.push(preorder[i]);
        }
        return true;
    }
}
