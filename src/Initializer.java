//Created by: Desi Borisova, Aliaksandr Baradzinchyk, and Tho Tran

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Initializer 
{
	public static void main(String[] args) 
	{
		ArrayList<Router> routerList = new ArrayList<Router>();
		int numOfRouters = 0;
		int currRouter = 0;
		
		//get number of routers
		try 
		{
			Scanner fScanner = new Scanner(new File("InitialConfig.txt"));
			
			while(fScanner.hasNextLine())
			{	
				numOfRouters++;
				fScanner.nextLine();
			}			
		}//end try
		catch(FileNotFoundException fnf)
		{
			System.out.println("File cannot be found " + fnf);
		}
		
		try 
		{
			Scanner fScanner = new Scanner(new File("InitialConfig.txt"));
			
			//add every router to routerList
			for(int i = 0; i < numOfRouters; i++)
			{
				String name = "R" + (i + 1);
				Router newRouter = new Router(name);
				routerList.add(newRouter);
			}
			
			//add destinations
			for(int i = 0; i < routerList.size(); i++)
			{
				Router r = routerList.get(i);
				
				for(int j = 0; j < routerList.size(); j++)
				{
					r.addDestination(routerList.get(j));
				}
			}
			
			while(fScanner.hasNextLine())
			{
				//replace everything except char and integer
				String line = fScanner.nextLine().replaceAll("[:|,|(|)]", " ");
				//create an array to hold source, neighbor, and cost
				String[] splitLine = line.split("\\s+");
				
				//add neighbor 
				for(int i = 1; i < splitLine.length; i+=2)
				{
					int index = Integer.parseInt(splitLine[i].replace("R", ""));
					routerList.get(currRouter).addNeighbor(routerList.get(index - 1));
				}
				
				//add cost
				for(int i = 2; i < splitLine.length; i+=2)
				{
					int cost = Integer.parseInt(splitLine[i]);
					routerList.get(currRouter).addCost(cost);
				}	
				
				currRouter++;
			}
			
			//initialize matrix capacity
			for(int m = 0; m < routerList.size(); m++)
				routerList.get(m).intializeMatrix(numOfRouters);
			
			//call builder method
			for(int k = 0; k < routerList.size(); k++)
				routerList.get(k).builder();

			//print the routing table for every router
			for(int i = 0; i < routerList.size(); i++)
			{
				DijkstraAlgorithm spTest1 = new DijkstraAlgorithm(routerList.get(i).getMatrix(), i);
				spTest1.dijkstra();
				System.out.println();
			}
			
			//choose R4 as the source router
			//change R5 & R6 cost and R1 & R2 cost
			//print new matrix, and calculate dijkstra 
			System.out.println("\nFor R6, change its neighbor R7 cost to 9"
								+ "\nFor R7, change its neighbor R6 cost to 9"
								+ "\nFor R1, change its neighbor R2 cost to 63"
								+ "\nFor R2, change its neighbor R1 cost to 63\n");
			
			routerList.get(5).changeCost("R7", 9);
			routerList.get(6).changeCost("R6", 9);
			routerList.get(0).changeCost("R2", 63);
			routerList.get(1).changeCost("R1", 63);
						
			for(int i = 0; i < routerList.size(); i++)
			{
				DijkstraAlgorithm spTest2 = new DijkstraAlgorithm(routerList.get(i).getMatrix(), i);
				spTest2.dijkstra();
				System.out.println();
			}	
		}//end try
		catch(FileNotFoundException fnf)
		{
			System.out.println("File cannot be found " + fnf);
		}
	}//end main
}//end class
