import java.util.*;
import java.io.*;

public class inClassSudoku {

   public static void main(String[] args) throws FileNotFoundException{
      if (handleArgs(args)){
         int[][] puzzle = readPuzzle(args[0]);
         displayPuzzle(puzzle);
         if (solve(puzzle,0,0)){
            System.out.println("SOLVED!!!");
            displayPuzzle(puzzle);
         } else {
            System.out.println("No solution.");
         }
      }
   }
   
   public static boolean solve(int[][] puzzle, int row, int col){
      if (col == 9){
         row = row + 1;
         col = 0;
      }
      
      if (row == 9){
         //Found solution
         return true;
      }
      
      if (puzzle[row][col] != 0){
         return solve(puzzle, row, col + 1);
      } else {
         for (int i = 1; i <= 9; i++){
            if (checkNum(puzzle, i, row, col)){
               puzzle[row][col] = i;
               if (solve(puzzle, row, col+1)){
                  return true;
               }
            }
         }
         puzzle[row][col] = 0;
         return false;
      }
   }
   
   public static boolean checkNum(int[][] puzzle, int num, int row, int col){
      //check 3x3 box
      int boxRow = row - (row % 3);
      int boxCol = col - (col % 3);
      for (int i = boxRow; i < boxRow + 3; i++){
         for (int j = boxCol; j < boxCol + 3; j++){
            if (puzzle[i][j] == num){
               return false;
            }
         }
      }
      
      //check row
      for( int i = 0; i < 9; i++){
         if (puzzle[row][i] == num){
            return false;
         }
      }
      
      //check col
      for( int i = 0; i < 9; i++){
         if (puzzle[i][col] == num){
            return false;
         }
      }
      return true;
   }
   
   public static boolean handleArgs(String[] args){
      String fileName = args[0];
      File file = new File(fileName);
      //Scanner input = new Scanner(file);
      if (!file.canRead()){
         return false;
      }    
      return true;
   }
   
   public static int[][] readPuzzle(String fileName) throws FileNotFoundException{
      int[][] puzzle = new int[9][9];
      Scanner input = new Scanner(new File(fileName));
      for (int row = 0; row < 9; row++){
         for (int col = 0; col < 9; col++){
            puzzle[row][col] = input.nextInt();
         }
      }
      return puzzle;
   }
   public static void displayPuzzle(int[][] puzzle){
      System.out.print("-------------\n");
      for (int i = 0; i < 9; i++){
         for (int j = 0; j < 9; j++){
            if (j % 3 == 0){
               System.out.print('|');
            } 
            System.out.print(puzzle[i][j]);
         }
         System.out.print("|\n");
         if ((i + 1) % 3 == 0){
            System.out.print("-------------\n");
         }
      } 
   }
}
