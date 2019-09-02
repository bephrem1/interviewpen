/*


  Permutation- LeetCode: https://leetcode.com/problems/sudoku-solver/

  This code passes all Leetcode test cases as of Sep 2 2019 (12:18 pm)
  Runtime: 1 ms*, faster than 97.78% of Java online submissions for Permutation.

  The video(String version) to explain this code is here: https://www.youtube.com/watch?v=GCm7m5671Ps
*/


/*
  Driver function to kick off the recursion
*/
public List<List<Integer>> permute(int[] nums) {

   List<List<Integer>> result = new ArrayList<List<Integer>>();

   ArrayList<Integer> decisionSpace = new ArrayList<Integer>();
   for(int i : nums){
      decisionSpace.add(i);
    }

   /* helper function to add all possible permuation to result list
   */
   permuteHelper(decisionSpace,new ArrayList<Integer>(), result) ;

   return result;
    }

private void permuteHelper(ArrayList<Integer> decisionSpace, ArrayList<Integer> choosen, List<List<Integer>> result){

        // Base case : If we have nothing to permute , we just add choosen list result
        if(decisionSpace.size() == 0){
            result.add(new ArrayList<Integer>(choosen));
        }
        else{
            for(int i = 0; i < decisionSpace.size(); i++){
                  // choose / explore / unchoose
                    int valueSelected = decisionSpace.get(i);
                    choosen.add(valueSelected);  // choosen
                    decisionSpace.remove(i);
                    permuteHelper(decisionSpace,choosen,result); // explored
                    decisionSpace.add(i,valueSelected);
                    choosen.remove(choosen.size()-1);  // unchoosen
            }
        }


}
