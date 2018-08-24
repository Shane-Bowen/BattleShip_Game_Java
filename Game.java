import java.util.Scanner;

public class Game {

	static Scanner kb = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("**** Welcome to the Battle Ships Game ****");
		System.out.println("\nRight now, the sea is empty.");

		String[][] oceanMap = new String[10][10];
		String[][] player = new String[10][10];
		String[][] comp = new String[10][10];

		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				oceanMap[i][j] = " ";
				player[i][j] = " ";
			}
		}

		int countPlayer = 5;
		int countComp = 5;
		printOcean(oceanMap);
		player = scanShips(player);
		printOcean(player);
		comp = setCompShips(player);
		//printOcean(comp);
		playerGo(player, comp, countPlayer, countComp);
	}

	public static void printOcean(String[][] map)
	{
		System.out.print("  ");
		for(int i = 0; i < 10; i++)
		{
			System.out.print(i);
		}

		System.out.println("  ");
		for(int i = 0; i < 10; i++)
		{
			System.out.print(i);
			System.out.print("|");

			for(int j = 0; j < 10; j++)
			{
				System.out.print(map[i][j]);
			}

			System.out.print(i);
			System.out.println("|");
		}

		System.out.print("  ");
		for(int i = 0; i < 10; i++)
		{
			System.out.print(i);
		}
		System.out.println("  ");		

	}

	public static String[][] scanShips(String[][] map)
	{
		System.out.println("\nDeploy your ships");

		for(int i = 0; i < 5; i++)
		{
			System.out.print("Enter X coordinate for your ships: ");
			int x = kb.nextInt();

			System.out.print("Enter Y coordinate for your ships: ");
			int y = kb.nextInt();

			if(x<0 || x>9 || y<0 || y>10)
			{
				System.out.println("Coordinate must be inside the 10 x 10 grid!");
				i--;
			}

			else if(map[x][y] == "@")
			{
				System.out.println("This Coordinate is already being used!");
				i--;
			}

			else
			{
				map[x][y] = "@";
			}
		}

		return map;
	}
	
	public static String[][] setCompShips(String[][] map){
		
		System.out.println("Computer is Deploying Ships");
		
		String[][] compShips = new String[10][10];
		
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				compShips[i][j] = " ";
			}
		}
		
		for(int i = 0; i < 5; i++)
		{
			int x = (int) (Math.random() * 10);
			int y = (int) (Math.random() * 10);
			
			if(x < 0 || y < 0)
			{
				i--;
			}
			
			else
				if(map[x][y] == "@" || compShips[x][y] == "c")
				{
					i--;
				}
			
				else{
					compShips[x][y] = "c";
					System.out.println(i + ". ship DEPLOYED");
				}
		}
		
		return compShips;
		
	}
	

	private static void playerGo(String[][] player, String[][] comp, int countPlayer, int countComp) {
		
		if(countComp == 0)
		{
			System.out.println("Hooray! You Win the Battle");
		}
		
		else{
			System.out.println("Your Turn:");
			System.out.print("Enter X Coordinates: ");
			int x = kb.nextInt();
			System.out.print("Enter Y Coordinates: ");
			int y = kb.nextInt();
			
			while(x > 9 || x < 0 || y > 9 || y < 0)
			{
				System.out.print("Enter X Coordinates: ");
				x = kb.nextInt();
				System.out.print("Enter Y Coordinates: ");
				y = kb.nextInt();
			}
			
			if(player[x][y] == "@")
			{
				player[x][y] = "X";
				System.out.println("Oh No, You Sunk your own ship :(");
				countPlayer--;
				printOcean(player);
				System.out.println("Your Ships " + countPlayer + "|" + " Computer Ships " + countComp);
				compGo(player, comp, countPlayer, countComp);
			}
			
			else if(comp[x][y] == "c")
			{
				player[x][y] = "!";
				System.out.println("Boom! You sunk the ship!");
				countComp--;
				printOcean(player);
				System.out.println("Your Ships " + countPlayer + "|" + " Computer Ships " + countComp);
				compGo(player, comp, countPlayer, countComp);
				
				if(countComp == 0)
				{
					System.out.println("Hooray! You win the battle!");
				}
				
				else
				{
					compGo(player, comp, countPlayer, countComp);
				}
			}
			
			else{
				player[x][y] = "-";
				System.out.println("You Missed!");
				printOcean(player);
				System.out.println("Your Ships " + countPlayer + "|" + " Computer Ships " + countComp);
				compGo(player, comp, countPlayer, countComp);				
			}
		}
		
	}

	public static void compGo(String[][] player, String[][] comp, int countPlayer, int countComp) {
		
		if(countPlayer == 0)
		{
			System.out.println("Sorry! You Lose!");
		}
		
		else{
			System.out.println("Computers Turn");
			int x = (int) (Math.random() * 10);
			int y = (int) (Math.random() * 10);
			
			while(x > 9 || x < 0 || y > 9 || y < 0)
			{
				x = (int) (Math.random() * 10);
				y = (int) (Math.random() * 10);
			}
			
			if(player[x][y] == "@")
			{
				player[x][y] = "X";
				System.out.println("Oh no, Computer sunk your ship!");
				countPlayer--;
				printOcean(player);
				System.out.println("Your Ships " + countPlayer + "|" + " Computer Ships " + countComp);
				if(countPlayer == 0)
				{
					System.out.println("Sorry! You Lose!");
				}
				
				else{
					playerGo(player, comp, countPlayer, countComp);
				}
				
			}
			
			else if(comp[x][y] == "c")
			{
				player[x][y] = "!";
				System.out.println("Boom! Computer sunk his own ship!");
				countComp--;
				printOcean(player);
				System.out.println("Your Ships " + countPlayer + "|" + " Computer Ships " + countComp);
				if(countComp == 0)
				{
					System.out.println("Hooray! You win the battle!");
				}
				
				else{
					playerGo(player, comp, countPlayer, countComp);
				}
			}
			
			else{
				player[x][y] = "-";
				System.out.println("Computer Missed!");
				printOcean(player);
				System.out.println("Your Ships " + countPlayer + "|" + " Computer Ships " + countComp);
				playerGo(player, comp, countPlayer, countComp);				
			}
			
		}
		
	}
}