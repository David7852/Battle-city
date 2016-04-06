package aStar;


import java.util.ArrayList;


public class AreaMap {

	private int mapWith;//35
	private int mapHeight;//25
	private ArrayList<ArrayList<Node>> map;
	private int startLocationX = 0;
	private int startLocationY = 0;
	private int goalLocationX = 0;
	private int goalLocationY = 0;
	private int[][] obstacleMap;

	public AreaMap() {
		// TODO Auto-generated constructor stub
	}
	
	public void SetAreaMap(int mapWith, int mapHeight, int[][] obstacleMap,boolean color) {
		this.mapWith = mapWith;
		this.mapHeight = mapHeight;
		this.obstacleMap = obstacleMap;
		
		createMap(color);
		registerEdges();
	}
	private void createMap(boolean color) 
	{//llenar el mapa
		Node node;
		map = new ArrayList<ArrayList<Node>>();
		for (int x=0; x<mapWith; x++) 
		{
			map.add(new ArrayList<Node>());//bidimensional de nodos
			for (int y=0; y<mapHeight; y++) 
			{
				node = new Node(x,y,obstacleMap[x][y]);//celda x,y
				if(color)
				{
					if (obstacleMap[x][y] == 1||obstacleMap[x][y] == 5)//SI ES IGUAL A BLOQUE BLINDADO o TANQUE amigo
						node.setObstical(true);
				}
				else
					if (obstacleMap[x][y] == 2||obstacleMap[x][y] == 5)//SI ES IGUAL A BLOQUE BLINDADO o TANQUE amigo
						node.setObstical(true);
				map.get(x).add(node);//se agrega ese nodo/celda
			}
		}
	}

	/**
	 * Registers the nodes edges (connections to its neighbors).
	 */
	private void registerEdges() //diagonales no permitidas. NO PERMITIDAS NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO... god i am bored...
	{//este sistema usa las coordenadas x,y de java swing. no es un problema, asuntos de hacerlo notar
		for ( int x = 0; x < mapWith; x++ ) 
		{
			for ( int y = 0; y < mapHeight; y++ ) 
			{
				Node node = map.get(x).get(y);
				if (!(y==0))
					node.setNorth(map.get(x).get(y-1));
				//if (!(y==0) && !(x==mapWith))
				//	node.setNorthEast(map.get(x+1).get(y-1));
				if (!(x==mapWith-1))
					node.setEast(map.get(x+1).get(y));
				//if (!(x==mapWith) && !(y==mapHeight))
				//	node.setSouthEast(map.get(x+1).get(y+1));
				if (!(y==mapHeight-1))
					node.setSouth(map.get(x).get(y+1));
				//if (!(x==0) && !(y==mapHeight))
				//	node.setSouthWest(map.get(x-1).get(y+1));
				if (!(x==0))
					node.setWest(map.get(x-1).get(y));
				//if (!(x==0) && !(y==0))
				//	node.setNorthWest(map.get(x-1).get(y-1));
			}
		}
	}
	
	

	public ArrayList<ArrayList<Node>> getNodes() {
		return map;
	}
	public void setObstical(int x, int y, boolean isObstical) {
		map.get(x).get(y).setObstical(isObstical);
	}

	public Node getNode(int x, int y) {
		return map.get(x).get(y);
	}

	public void setStartLocation(int x, int y) {
		map.get(startLocationX).get(startLocationY).setStart(false);
		map.get(x).get(y).setStart(true);
		startLocationX = x;
		startLocationY = y;
	}

	public void setGoalLocation(int x, int y) {
		map.get(goalLocationX).get(goalLocationY).setGoal(false);
		map.get(x).get(y).setGoal(true);
		goalLocationX = x;
		goalLocationY = y;
	}

	public int getStartLocationX() {
		return startLocationX;
	}

	public int getStartLocationY() {
		return startLocationY;
	}
	
	public Node getStartNode() {
		return map.get(startLocationX).get(startLocationY);
	}

	public int getGoalLocationX() {
		return goalLocationX;
	}

	public int getGoalLocationY() {
		return goalLocationY;
	}
	
	public Node getGoalLocation() {
		return map.get(goalLocationX).get(goalLocationY);
	}
	
	public float getDistanceBetween(Node node1, Node node2) {
		//if the nodes are on top or next to each other, return 1				//this is useless now due to... 
		//if (node1.getX() == node2.getX() || node1.getY() == node2.getY())		\m/(>.<)\m/ ¡¡¡ME!!! \m/(>.<)\m/
		int value = mapHeight+mapWith;
		//Now, i love this kind of IF... I mean, look at this!... it is so long :') ... so beautifully creepy!
		//yeah, but it is nothing elegant! could you say. Well, screw you!... 
		//Don't listen to then IF, i think you are beautiful just the way you are.
		//also, you do some pretty cool stuff; for example: you detect when a cell of stupid water is worthy to path considering the actual direction of the route
		//other if can't do such a thing
		//
		if(//si el nodo siguiente es agua Y
			node2.getId()==3&&
				(
					(//Dicho nodo tiene a otro nodo agua alineado en el sentido mas probable de mi pre-supuesta ruta O
						((node1.getSouth()!=null&&node1.getSouth()==node2)&&(node2.getSouth()!=null&&node2.getSouth().getId()==3))
							||
						((node1.getNorth()!=null&&node1.getNorth()==node2)&&(node2.getNorth()!=null&&node2.getNorth().getId()==3))
							||
						((node1.getEast()!=null&&node1.getEast()==node2)&&(node2.getEast()!=null&&node2.getEast().getId()==3))
							||
						((node1.getWest()!=null&&node1.getWest()==node2)&&(node2.getWest()!=null&&node2.getWest().getId()==3))
					)
						||
					(//el tanque fue vilmente engañado y cayo en una casilla de agua Y entonces
						node1.getId()==3&&
						(//Si esta casilla tiene otras casillas agua a su alrededor distintas a la mia, es probable que me vea tentado a pisarlas porque; francamente, ¿quien se resiste a salpicar los charcos cuando controlas una maquina de destruccion gigante?.
							(node2.getSouth()!=null&&node2.getSouth().getId()==3&&node2.getSouth()!=node1)
								||
							(node2.getNorth()!=null&&node2.getNorth().getId()==3&&node2.getNorth()!=node1)
								||
							(node2.getEast()!=null&&node2.getEast().getId()==3&&node2.getEast()!=node1)
								||
							(node2.getWest()!=null&&node2.getWest().getId()==3&&node2.getWest()!=node1)
						)
					)
				)
		  )
		//bla bla.. como sea aumenta el costo	
			{
			value+=100;//a mayor, menos preferible
				if(node2.getId()==3&&node1.getId()!=3)//PERO, si vengo desde una casilla que no es agua, el costo es el mismo a pasar sobre tierra. 50% free :D
						value--;
			}
		else
			if(node2.getId()==7)//si el siguiente es un bosque, es mejor ir por ahi y esconderse de los paparazzis
			{
				value--;	//a menor, mas preferible
				if(node2.getId()==7&&node1.getId()==7)//si estoy oculto, EXTRAmejor si sigue asi
					value--;
			}
			else
				if(node2.getId()==4)//las piedras no son complicadas...
						value=value+1;
			return value;
		 
/*		else 
		{ //if they are diagonal to each other return diagonal distance: sqrt(1^2+1^2)
			return (float) 1.7*(mapHeight+mapWith);
		}*/
	}
	//useless comments everywhere.
	public int getMapWith() {
		return mapWith;
	}//like here...
	public int getMapHeight() {
		return mapHeight;
	}
	public void clear(boolean color) {
		startLocationX = 0;
		startLocationY = 0;
		goalLocationX = 0;
		goalLocationY = 0;
		createMap(color);
		registerEdges();
	}
}


















//or here :P