//Created by: Desi Borisova, Aliaksandr Baradzinchyk, and Tho Tran

public class DijkstraAlgorithm 
{
	private int[][] in_matrix;
	private int in_source;
	private static final int NO_ROOT = -1;
	
	public DijkstraAlgorithm(int[][] matrix, int source)
	{
		in_matrix = matrix;
		in_source = source;
	}
	
	public void dijkstra()
	{
		int vertices = in_matrix.length;
		
		//holds minimal cost between source and vertex v
		int[] cost = new int[vertices];
		//hold every vertex that has been visited
		boolean[] visited = new boolean[vertices];
		//stores the best path taken by the source to reach vertex v
		int[] path = new int[vertices];
		
		for(int vIndex = 0; vIndex < vertices; vIndex++)
		{
			cost[vIndex] = Integer.MAX_VALUE;
			visited[vIndex]= false;
		}
		
		//cost between the source and itself is always zero 
		cost[in_source] = 0;
		path[in_source] = NO_ROOT;
		
		for(int i = 1; i < vertices; i++)
		{
			int nearestVertex = -1;
			int minCost = Integer.MAX_VALUE;
			
			for(int vIndex = 0; vIndex < vertices; vIndex++)
			{
				if(visited[vIndex] == false && cost[vIndex] < minCost)
				{
					nearestVertex = vIndex;
					minCost = cost[vIndex];
				}
			}
			
			//update vertex as visited
			visited[nearestVertex] = true;
			
			//update cost value and path
			for (int vIndex = 0; vIndex < vertices; vIndex++)
			{
				int lineCost = in_matrix[nearestVertex][vIndex];
				
				if(lineCost > 0 && (minCost + lineCost) < cost[vIndex])
				{					
					path[vIndex] = nearestVertex;
					cost[vIndex] = minCost + lineCost;
				}  
			}
		}
		
		printTable(cost, path);
	}
	
	private void printTable(int[] cost, int[] path)
	{
		System.out.println("==========================================");
		System.out.println("Routing Table for " + "R" + (in_source + 1));
		System.out.println("------------------------------------------");
		System.out.println("Destination\t" + "Cost\t" + "Path\t");
		System.out.println("------------------------------------------");
		
		for(int vIndex = 0; vIndex < cost.length; vIndex++)
		{
			if(vIndex != in_source)
			{
				System.out.print("R" + (vIndex + 1) + "\t\t");
				System.out.print(cost[vIndex] + "\t");
				printPath(vIndex, path);
				System.out.print("\n");
			}
		}
		
		System.out.println("==========================================");
	}//end method
	
	private static void printPath(int currentVertex, int[] path)
	{
		if (currentVertex == NO_ROOT)
			return;
		
		printPath(path[currentVertex], path);
		System.out.print("R" + (currentVertex + 1) + " ");
	}
}//end class
