import java.util.*;
public class UNIBIT_PRIVATE_LIMITED_Assesment{

    //merge Sort
   static void merge(int arr[], int st, int mid, int ed)
    {
        int merge[] = new int[ed-st+1];
		int idx1 = st;
		int idx2 = mid+1;
		int x =0;
		
		while(idx1 <= mid && idx2 <= ed) {
			   if(arr[idx1] <= arr[idx2]) {
				 merge[x++] = arr[idx1++];
				   
			     }else {
				   merge[x++] = arr[idx2++];
			   }
		    }
		
		while(idx1 <= mid) {
			merge[x++] = arr[idx1++];
		}
		
		while(idx2 <= ed) {
			merge[x++] = arr[idx2++];
		}
		
	  for(int i=0, j=st;  i<merge.length; i++, j++) {
		  arr[j] = merge[i];
	  }
    }
  static  void mergeSort(int arr[], int st, int ed)
    {
         if(st >= ed) {
			   return;
		   }
		   
		   int mid = st + (ed - st)/2;
		   mergeSort(arr, st , mid);
		   mergeSort(arr, mid+1, ed);
		   
		   merge(arr, st, mid, ed);
    }
    
    
   //First Combination 
  public static int[][] findCombination1(int[] arr, int sum) {
	  Set<Integer> s = new HashSet<>();
	    List<int[]> pairsList = new ArrayList<>();
	    int n = arr.length;

	    for (int i = 0; i < n; i++) {
	        int temp = sum - arr[i];
	        if (s.contains(temp)) {
	            pairsList.add(new int[]{temp, arr[i]});
	        }
	        s.add(arr[i]);
	    }

	    int[][] ans = new int[pairsList.size()][2];
	    for (int i = 0; i < pairsList.size(); i++) {
	        ans[i] = pairsList.get(i);
	    }

	    return ans;
  }
  
  // Second Combination
  public static List<List<Integer>> findCombinations(int[] nums, int target) {
      target *= 2; // Double the target value
      List<List<Integer>> combinations = new ArrayList<>();
      findCombinationsHelper(nums, target, new ArrayList<>(), combinations);
      return combinations;
  }

  private static void findCombinationsHelper(int[] nums, int target, List<Integer> current, List<List<Integer>> combinations) {
      if (target == 0) {
          combinations.add(new ArrayList<>(current));
          return;
      }

      if (nums.length == 0 || target < 0) {
          return;
      }

      for (int i = 0; i < nums.length; i++) {
          int num = nums[i];
          int[] remaining = new int[nums.length - i - 1];
          System.arraycopy(nums, i + 1, remaining, 0, remaining.length);

          if (num == target) {
              current.add(num);
              combinations.add(new ArrayList<>(current));
              current.remove(current.size() - 1);
          } else if (num <= target / 2) {
              current.add(num);
              findCombinationsHelper(remaining, target - num, current, combinations);
              current.remove(current.size() - 1);
          }
      }
  }

    
    public static void main(String[] args) {
    	  int[] nums = {1, 3, 2, 2, -4, -6, -2, 8};
          int target = 4;
          
          //First Combinations
          int[][] firstCombinations = findCombination1(nums, target);
          System.out.println("First Combination for " + target + " : " + Arrays.deepToString(firstCombinations));
    
          // Merge Sort
          System.out.println("Merge Sort");
        mergeSort(nums, 0,  nums.length-1);
		for(int i=0; i< nums.length; i++){
		System.out.print(nums[i] + " ");
	    }
		
		//Second Combination
		System.out.println();
		System.out.println("Second Combination");
		List<List<Integer>> SecondCombinations = findCombinations(nums, target);
        for (List<Integer> combination : SecondCombinations) {
            System.out.println(combination);
        }

    }
}
