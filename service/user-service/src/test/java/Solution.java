import java.util.concurrent.locks.ReentrantLock;

class Solution{
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("sum(1,100) = " + solution.sum(1, 100));
    }
    public int sum(int start,int end){
        return (start+end)*(end-start+1)/2;
    }
}