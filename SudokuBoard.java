import java.util.*;
import java.io.*;

public class SudokuBoard {
   private int[][] board;
   
   public SudokuBoard(String filename) {
      board = new int[9][9];
      try {
         Scanner file = new Scanner(new File(filename));
         
         for(int r = 0; r < 9; r++) {
            String line = file.nextLine();
            for(int c = 0; c < 9; c++) {
               Scanner lineScan = new Scanner(line);
               char num = lineScan.next().charAt(c);
               if(num == '.') {
                  board[r][c] = 0;
               }else {
                  board[r][c] = num - '0';
               }
            }
         }
         
      } catch(FileNotFoundException e) {
         System.out.println("Cannot load: " + filename);
      } catch(InputMismatchException e) {
         System.out.println(filename + " does not meet format expectations.");
      }
   }
   
   private boolean empty(int[][] board){
      for(int r = 0; r < board.length ; r++){
         for(int c= 0 ; c < board[r].length ; c++){
            if (board[r][c] < 0 || board[r][c] > 9) {
                return false;
               }
            }
      }
       return true;
   }

   private boolean hasNoDuplicates(int[][] board) {
       // Check for duplicates in each row
       for (int r = 0; r < board.length; r++) {
           Set<Integer> rowSet = new HashSet<>();
           for (int c = 0; c < board[r].length; c++) {
               int value = board[r][c];
               if (value != 0) {
                   if (rowSet.contains(value)) {
                       return false; 
                   }
                   rowSet.add(value);
               }
           }
       }
      // Check for duplicates in each colomuns
       for (int c = 0; c < board[0].length; c++) {
           Set<Integer> colSet = new HashSet<>();
           for (int r = 0; r < board.length; r++) {
               int value = board[r][c];
               if (value != 0) {
                   if (colSet.contains(value)) {
                       return false; 
                   }
                   colSet.add(value);
               }
           }
       }
   
       return true; 
   }

    
      private int[][] miniSquare(int[][] board , int spot) {
      int[][] mini = new int[3][3];
      for(int r = 0; r < 3; r++) {
         for(int c = 0; c < 3; c++) {
            mini[r][c] = board[(spot - 1) / 3 * 3 + r][(spot - 1) % 3 * 3 + c];
         }
      }
 
      return mini;
   }
   
   private boolean checkMini() {
      for (int i = 1; i <= 9; i++) {
        if (!hasNoDuplicates(miniSquare(board , i))) {
            return false;
         }
      }
       System.out.println("pass mini");
       return true;
   }

   public boolean isValid(){
      if( empty(board) && hasNoDuplicates(board) && checkMini()){
         return true;
      }
      return false;
   }
   
   
   public String toString() {
      String build = " -----------------\n";
      for(int r = 0; r < board.length; r++) {
         build += "|\t";
         for(int c = 0; c < board[0].length; c++) {
            build += board[r][c] + "|\t";
         }
         build += "|\n";
      }
      build += " -----------------\n";
      return build;   
   }
}

/*
# PROGRAM OUTPUT

  ----jGRASP exec: java SudokuEngine
  -----------------
 |	50|	0|	0|	49|	0|	53|	0|	0|	51|	|
 |	0|	53|	52|	0|	0|	0|	55|	49|	0|	|
 |	0|	49|	0|	50|	0|	51|	0|	56|	0|	|
 |	54|	0|	50|	56|	0|	55|	51|	0|	52|	|
 |	0|	0|	0|	0|	0|	0|	0|	0|	0|	|
 |	49|	0|	53|	51|	0|	57|	56|	0|	54|	|
 |	0|	50|	0|	55|	0|	49|	0|	54|	0|	|
 |	0|	56|	49|	0|	0|	0|	50|	52|	0|	|
 |	55|	0|	0|	52|	0|	50|	0|	0|	49|	|
  -----------------
 
 
  ----jGRASP: Operation complete.

*/