# Computer Network: Dynamic Routing

## Goal
<p>Build a network terminal-based simulator for the dynamic routing capability</p>

## Tasks
1) Create a class Router that represents a router in a network

2) **For every router, implement the following functions:**

     - Display_table: shows the developed routing table (line by line).

     - Builder: builds one packet (e.g., string object) to hold the router’s name, it’s neighbors, along with the
       corresponding cost for each neighbor. The packet is then sent to all other routers in the network. All routers 
       in the networks are running the Listener.

     - Listener: listens to packets sent by other routers, computes the shortest path to other routers, and update the 
       routing table accordingly whenever needed.

     - Change_Cost: allows the user of your simulator to simulate a change in the cost between the router and one 
       of its directly connected neighbors. This function accepts two integers, the first identifies the neighbor and 
       the second identifies the new cost.
 
3) Initializer: Initial information about the routers and cost of links that connect them are stored in a 
text file (we will call it configuration file) on your computer. The initializer, as a component, reads this file and creates 
a list/array of routers accordingly
