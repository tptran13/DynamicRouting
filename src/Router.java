//Created by: Desi Borisova, Aliaksandr Baradzinchyk, and Tho Tran

import java.util.ArrayList;

public class Router 
{
	private String in_name;
	private ArrayList<Router> in_neighbor = new ArrayList<Router>();
	private ArrayList<Integer> in_cost = new ArrayList<Integer>(); 
	private ArrayList<Router> in_destination = new ArrayList<Router>();
	private int[][] in_matrix;

	//constructor
	public Router(String name)
	{
		in_name = name;
	}
	
	public void addNeighbor(Router neighbor)
	{
		in_neighbor.add(neighbor);
	}
	
	public void addDestination(Router destination)
	{
		in_destination.add(destination);
	}
	
	public void addCost(int cost)
	{
		in_cost.add(cost);
	}

	public void intializeMatrix(int cap)
	{
		in_matrix=new int [cap][cap];
	}
	
	public int[][] getMatrix()
	{
		return in_matrix;
	}
	
	public void builder()
	{ 	
		String message = in_name + " ";
		
		for(int i = 0; i < in_neighbor.size(); i++)
			message += in_neighbor.get(i).in_name + " " + in_cost.get(i) + " ";
		
		for(int i= 0; i < in_destination.size(); i++)
			in_destination.get(i).listener(message);
	}
	
	private void listener(String message)
	{
		String[] messageArr = message.split(" "); 
		
		for(int i = 0, j = 1, k = 2;  j < messageArr.length; j+=2, k+=2)
		{
			int source = Integer.parseInt(messageArr[i].replace("R", "")) - 1;
			int neighbor = Integer.parseInt(messageArr[j].replace("R", "")) - 1;
			int cost = Integer.parseInt(messageArr[k]);		
			in_matrix[source][neighbor] = cost;
		}
	}
	
	public void changeCost(String name, int newCost)
	{
		int index = 0;
		
		for(int i = 0; i < in_neighbor.size(); i++)
		{
			if(!name.equals(in_neighbor.get(i).in_name))
				index++;
			else 
				break;
		}
		
		in_cost.set(index, newCost);
		builder();
	}
	
	/*public void printInfo()
	{		
		String source = in_name;
		int i = 0;
		
		if(in_neighbor.size() > 0)
		{
			while(i < in_neighbor.size())
			{
				System.out.println(source + "-->" + in_neighbor.get(i).in_name + " " + "costs: " + in_cost.get(i));
				i++;
			}
			System.out.println("================================================================");
		}
		else
		{
			System.out.println(source);
			System.out.println("================================================================");
		}
	}*/
	
	/*public void printMatrix()
	{		
		int row = in_matrix.length, col = in_matrix[0].length;
		
		for(int i = -1; i < col; i++)
		{
			if(i == -1)
				System.out.print("   ");
			else
				System.out.print("R" + (i + 1) + "\t");
		}
		System.out.println();
		
		for(int i = 0; i < row; i++)
		{
			System.out.print("R" + (i + 1) + " ");
			
			for(int j = 0; j < col; j++)
			{
				System.out.print(in_matrix[i][j] + "\t");
			}
			
			System.out.println();
		}
	}*/
}//end class
