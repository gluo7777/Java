
public class LongIncSub{
    public static void main(String[] args) {
        System.out.println(lcs(new int[]{10, 22, 1, 33, 21, 50, 41, 60, 80,2,3,4,5,6,7}));
    }
    static int lcs(int [] arr){
        // LCS[i] gives longest LCS up to i
        // arr[i] gives value at i
        int [] LCS = new int[arr.length];
        int max = 0;
        for(int i=0; i<arr.length; i++){
            LCS[i] = 0;
            // find max LCS
            for(int j=0; j<i; j++){
                if( LCS[i] < LCS[j] && arr[i] >= arr[j] ){
                    LCS[i] = LCS[j];
                }
            }
            LCS[i] = LCS[i] + 1;
            max = Math.max(LCS[i],max);
        }
        return max;
    }
}