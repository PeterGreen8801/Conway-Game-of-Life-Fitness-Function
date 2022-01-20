public class ConwayGameOfLife
{
   int board[][];
   int nextBoard[][];
   int boardSize;
   int livecount;
   int playableBoard;
   
   ConwayGameOfLife(int gameBoard)
   {
      playableBoard = gameBoard;
      int livecount = 0;
      boardSize = playableBoard + 2;
      board = new int[boardSize][boardSize];
      nextBoard = new int[boardSize][boardSize];
   }
   
   private boolean aliveCheck(int i, int j)
   {
      return board[i][j] == 1;
   }
   
   private boolean borderCheck(int i, int j)
   {
      return i == boardSize - 1 || i == 0 || j == boardSize - 1 || j == 0;
   }
   private int liveNeighborCheck(int i, int j)
   {
      int liveNeighbors = 0;
      for(int a = i-1; a <= i+1;a++)
      {
         for(int b = j-1; b <= j+1;b++)
         {
            if(aliveCheck(a,b) && !(i == a && j == b))
            {
               liveNeighbors++;
            }
         }
      }
      return liveNeighbors;
   }
   private boolean lifeChecker(int i, int j)
   {
      if(borderCheck(i,j))
      {
         return false;
      }
      else
      {
         int neighbors = liveNeighborCheck(i,j);
         if(aliveCheck(i,j))
         {
            return (neighbors == 2 || neighbors == 3);
         }
         else
         {
            return liveNeighborCheck(i,j) == 3;
         }
      }
   }
   
   void mapToBoard(int gameBoard[][])
   {
      for(int i = 1; i < boardSize - 1;i++)
      {
         for(int j = 1; j < boardSize -1; j++)
         {
            board[i][j] = gameBoard[i-1][j-1];
         }
      }
   }
   
   private void boardMapper(int next[][])
   {
      for(int i = 0; i < boardSize; i++)
      {  
         for(int j = 0;j < boardSize; j++)
         {
            board[i][j] = next[i][j];
         }
      }
   }
   
   private void iterate()
   {
      livecount =0;
      for(int i = 0; i < boardSize; i++)
      {
         for(int j = 0; j < boardSize;j++)
         {
            if(lifeChecker(i,j))
            {
               nextBoard[i][j] = 1;
               livecount++;
            }
            else
            {
               nextBoard[i][j] = 0;
            }
         }
      }
      boardMapper(nextBoard);
   }
   
   int getLivecount()
   { 
      return livecount; 
   }
   
   void iterateGens(int generations)
   {
        for (int i=0; i < generations; i++)
        {
            iterate();
        }
    }
    
   private void printBoard()
   {
      System.out.println("Printing current board:");
      for(int i = 1; i < boardSize - 1; i++)
      {
         for(int j = 1; j < boardSize -1; j++)
         {
            if(aliveCheck(i,j))
            {
               System.out.print("*");
            }
            else
            {
               System.out.print(".");
            }
         }
         System.out.println();
      }
   }
   
   private void printCompactBoard()
   {
      String compactConvert = "";
      for(int i = 0; i < boardSize; i++)
      {
         for(int j = 0; j < boardSize; j++)
         {
            compactConvert = compactConvert + board[i][j];
            if(compactConvert.length() == 4)
            {
               int binary = Integer.parseInt(compactConvert,2);
               String hexadecimal = Integer.toHexString(binary);
               System.out.print(hexadecimal);
               compactConvert =  "";
            }
         }
         System.out.println();
      }
   }
   /*public static void main(String[] args)
   {
      ConwayGameOfLife board = new ConwayGameOfLife(30);
      int gameBoard[][]=
      {
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        };
        
        board.mapToBoard(gameBoard);
        board.printBoard();
        board.printCompactBoard();
        board.iterate();
        board.printBoard();
        board.printCompactBoard();
        board.iterate();
        board.printBoard();
        board.printCompactBoard();
   }*/
}
