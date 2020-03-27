public class MedianofTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length+nums2.length;
        int target = len/2;
        int n1 =0;
        int n2 =0;
        int merge[] = new int [len];
        for(int i=0;i<len;i++){
            if(n1<nums1.length && n2<nums2.length){
                merge[i] = (nums1[n1] < nums2[n2] ? nums1[n1++] : nums2[n2++]);
            }
            else if(n1<nums1.length)
            {
                merge[i] = nums1[n1++];
            } else if(n2<nums2.length){
                merge[i] = nums2[n2++];
            }
            
            if(target==i){
                if(len%2==0){
                    return (double) (merge[target-1] + merge[target])/2;
                } 
                else {
                    return (double) merge[target];
                }
            }
        }
        return 0;
    }

    public static void main(String[] args){
        int[] arr1 = {1,5};
        int[] arr2 = {};
        MedianofTwoSortedArrays mtsa = new MedianofTwoSortedArrays();
        System.out.println(mtsa.findMedianSortedArrays(arr1, arr2));
    }
}