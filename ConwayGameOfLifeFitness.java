import java.util.Random;
public class ConwayGameOfLifeFitness extends ConwayGameOfLife
{
   int bestFitness = 0;
   int[][] bestStarter;
   ConwayGameOfLife bestSolution;
   
   ConwayGameOfLifeFitness(int playableBoard) 
   {
      super(playableBoard);
      bestSolution = new ConwayGameOfLife(playableBoard);
   }
    
   private int[][] create8x8()
   {
      Random rand = new Random();
      int[][] starter = new int[8][8];
      for (int i = 0; i < 8; i++)
      {
         for (int j = 0; j < 8; j++)
         {
            starter[i][j] = rand.nextInt(2);
         }
      }
      return starter;
   }
    
   private void drop8x8(int[][] starter)
   {
      if(starter[0].length!=8)
      {
         System.out.print("Error: Needs to be 8x8");
      }
      else
      {
         livecount = 0;
         for(int i = 0; i< board.length; i++)
         {
            for(int j = 0; j< board.length; j++)
            {
               if(i<12 || i>19 || j<12 || j>19)
               { 
                  board[i][j] = 0;
               }
               else 
               { 
                  board[i][j] = starter[i-12][j-12];
                  if(starter[i-12][i-12]==1)
                  {
                     livecount++;
                  }
               }
            }
         }
      }
   }
   
   int getFitness(int[][] starter)
   {
      drop8x8(starter);
      ConwayGameOfLife solution = new ConwayGameOfLife(30);
      solution.mapToBoard(board);
      iterateGens(1000);
      return this.getLivecount();
   }
    
   void optimizer(int runtimeInMillis)
   {
      long startTime = System.currentTimeMillis();
      int i = 0;
      while(System.currentTimeMillis()-startTime<runtimeInMillis)
      {
         int[][] thisStarter = create8x8();
         int thisFitness = getFitness(thisStarter);
         boolean bestSoFar = thisFitness>bestFitness;
         if (bestSoFar)
         {
            System.out.printf("New best fitness. it: %d is %d\n",i,thisFitness);
            bestFitness=thisFitness;
            bestStarter = thisStarter;
         }
         i++;
      }
      System.out.printf("\nThe best fitness was it: %d and it came from this starter:\n",bestFitness);
      print8x8Starter(bestStarter);
      System.out.println();
      print8x8StarterAsCompact(bestStarter);
   }
   
   void print8x8Starter(int[][] starter)
   {
      for (int i=0; i<8; i++)
      {
         for (int j=0; j<8; j++)
         {
            System.out.print(starter[i][j]);
         }
         System.out.println();
      }
   }
   
   void print8x8StarterAsCompact(int[][] starter)
   {
        String toCompact = "";
        for (int i = 0; i < 8; i ++)
        {
            for (int j = 0; j < 8; j ++)
            {
                toCompact = toCompact + starter[i][j];
                if (toCompact.length() == 4)
                {
                    int binary = Integer.parseInt(toCompact, 2);
                    String hex = Integer.toHexString(binary);
                    System.out.print(hex);
                    toCompact = "";
                }
            }
            System.out.println();
        }

    }
    public static void main(String[] args) throws Exception 
    {
        ConwayGameOfLifeFitness board = new ConwayGameOfLifeFitness(30);
        int[][] designOne =
        {
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
        };
        int[][] designTwo =
        {
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0},
                {0,1,1,1,0,0,0,0},
                {0,1,0,1,0,0,0,0},
                {0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
        };

        System.out.printf("Design One has a fitness of %d\n",board.getFitness(designOne));
        System.out.printf("Design Two has a fitness of %d\n\n",board.getFitness(designTwo));
        board.optimizer(7200000);

   }
}