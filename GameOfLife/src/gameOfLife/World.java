package gameOfLife;

import java.awt.Color;

import core.CustomAppearance;

public class World implements TileGrid
{
	private int row;
	private int column;
	private Tile[][] tiles;
	
	public World (int row, int column)
	{
		this.row = row;
		this.column = column;
		
    	CustomAppearance ca = new CustomAppearance(row, column);
		ca.setCellColor(Color.YELLOW);
		ca.setBorderColor(Color.BLACK);
		core.API.initialize(ca);
		
		this.tiles = new Tile[row][column]; //should I add "this.row/column". 
		for (int r = 0; r < this.tiles.length; r++)
		{
			for (int c = 0; c < this.tiles[0].length; c++)
			{
				this.tiles[r][c] = new ConstantTile(0);
			}
		}
		this.drawWorld();
	}
	
	public void drawWorld()
	{
		for (int r = 0; r < tiles.length; r++)
		{
			for (int c = 0; c < tiles[0].length; c++)
			{
				Color color = tiles[r][c].getColor();
				core.API.paintSolidColor(r, c, color);
			}
		}
	}
	
	public Tile getTile(int row, int column)
	{
		return tiles[row][column];
	}
	
	public void setTile(int row, int column, Tile t)
	{
		tiles[row][column] = t;
		drawWorld();
	}
	
	public void processCommand(String command)
	{
		String[] commands = command.split(" ");
		String type = commands[0]; //name for "type", like "description"?
		if (type.equals("fill"))
		{
			fill(command);
		}
		else if (type.equals("set"))
		{
			set(command);
		}
		
		else if (type.equals("evolve"))
		{
			evolve(command);
		}
		
		else if (type.equals("setCopycats"))
		{
			setCopycats(command);
		}
		
		else if (type.equals("setShape"))
		{
			setShape(command);
		}
		
		else if (type.equals("help"))
		{
			help();
		}
		else
		{
			custom(command);
		}
	}
	//CHLOE'S RANDOM METHODS AHEAD...........
	//WELCOME TO MY "WORLD"... haha get it?
	//wow chloe stop being so punny....
	//k just pls go back 2 work...
	
	public void fill(String command)
	{
		String[] commands = command.split(" ");
		String tile = commands[1];
		int age = Integer.parseInt(commands[2]);
		
		for (int r = 0; r < tiles.length; r++)
		{
			for (int c = 0; c < tiles[0].length; c++)
			{
				if (tile.equals("constant"))
				{
					tiles[r][c] = new ConstantTile(age);
				}		
				else if (tile.equals("rainbow"))
				{
					tiles[r][c] = new RainbowTile(age);
				}
				else if (tile.equals("mono"))
				{
					tiles[r][c] = new MonoTile(age);
				}
				else if (tile.equals("immigration"))
				{
					tiles[r][c] = new ImmigrationTile(age);
				}
			}
		}
		drawWorld();
	}
	
	public void set(String command)
	{
		String[] commands = command.split(" ");
		String tile = commands[1];
		int age = Integer.parseInt(commands[2]);
		int row = Integer.parseInt(commands[3]);
		int column = Integer.parseInt(commands[4]);
		if (tile.equals("constant"))
		{
			tiles[row][column] = new ConstantTile(age);
		}
		else if (tile.equals("rainbow"))
		{
			tiles[row][column] = new RainbowTile(age);
		}
		else if (tile.equals("mono"))
		{
			tiles[row][column] = new MonoTile(age);
		}
		else if (tile.equals("immigration"))
		{
			tiles[row][column] = new ImmigrationTile(age);
			//System.out.println("tile at " + row + " , " + column + " color: " + tiles[row][column].getColor());
		}
		drawWorld();
	}
	
	public void evolve(String command)
	{
		String[] commands = command.split(" ");
		int steps = Integer.parseInt(commands[1]);
		int pause = Integer.parseInt(commands[2]);
		/*
		 TEST: MOUSE TRIALS EC
		if (steps > 1)
		{
			for (int i = 1; i <= pause / 5; i++)
			{
				core.API.pause(5);
				core.API.getMouseRow();
				core.API.getMouseColumn();
				tiles[core.API.getMouseRow()][core.API.getMouseColumn()] = new MonoTile(1);
			} 
			drawWorld();
		}
		*/

		for (int i = 1; i <= steps; i++)
		{
			steps(pause);
		}		
	}	
	
	public void steps(int pause)
	{
		Tile[][] tiles2 = new Tile[row][column];
		
		Tile[] neighbors;
		
		for (int r = 0; r < tiles.length; r++)
		{
			for (int c = 0; c < tiles[0].length; c++)
			{
				neighbors = getNeighbors(tiles, r, c);
				tiles2[r][c] = tiles[r][c].getUpdatedTile(neighbors); 
			}
		}

		tiles = tiles2;
		drawWorld();
		core.API.pause(pause);	
	}
	
	public Tile[] getNeighbors(Tile[][] tiles2, int r, int c)
	{
		Tile[] neighbors = new Tile[8];
		int mR = r - 1;
		int pR = r + 1;
		int mC = c - 1;
		int pC = c + 1;
		if (mR < 0)
		{
			mR = row - 1;
		}
		if (mC < 0)
		{
			mC = column - 1;
		}
		if (pR >= row) 
		{
			pR = pR % row;
		}
		if (pC >= column) 
		{
			pC = pC % column;
		}

		neighbors[0] = tiles2[mR][mC];
		neighbors[1] = tiles2[mR][c];
		neighbors[2] = tiles2[mR][pC];
		neighbors[3] = tiles2[r][mC];
		neighbors[4] = tiles2[r][pC];
		neighbors[5] = tiles2[pR][mC];
		neighbors[6] = tiles2[pR][c];
		neighbors[7] = tiles2[pR][pC];
		
		/*
		TEST: NEIGHBORS
		for (int i = 0; i < 8; i++)
		{
			System.out.println("NEIGHBOR[" + i + "]: " + neighbors[i]);
		}
		*/
		return neighbors; 
	}
	
	public void setCopycats(String command)
	{
		//ok idk why i made a method for this there is only like one thing to do for copycats but i wanted to keep it consistent
		//same goes for the mirror. i'm just gonna keep it in this method though 
		String[] commands = command.split(" ");
		String pattern = commands[1];
		int copy = 0;
		if (column % 2 == 0)
		{
			copy = tiles[0].length / 2;
		}
		else if (column % 2 == 1)
		{
			copy = tiles[0].length / 2 + 1;
		}

		 // THIS IS THE WORKING CODE!!!!!
		if (pattern.equals("mirror"))
		{
			for (int r = 0; r < tiles.length; r++)
			{
				for (int c = copy; c < tiles[0].length; c++)
				{
					tiles[r][c] = new CopycatTile(r, column - 1 - c, this);
				}
			}
		}

		/*
		//TEST AHEAD FOR RIGHT HAND SIDE COPY CATS
		if (pattern.equals("mirror"))
		{
			System.out.println("right");
			for (int r = 0; r < tiles.length; r++)
			{
				for (int c = copy; c < tiles[0].length; c++)
				{
					tiles[r][c] = new CopycatTile(r, column - 1 - c, this);
				}
			}
		}
		*/
	}
	
	public void setShape(String command)
	{
		//THE SHAPES DONT SHOW UP UNLESS I CLICK ON THE GRID WHYYYYY
		String[] commands = command.split(" ");
		String shape = commands[1];
		int startR = Integer.parseInt(commands[2]);
		int startC = Integer.parseInt(commands[3]);
		
		int start1R = startR + 1;
		int start2R = startR + 2;
		int start1C = startC + 1;
		int start2C = startC + 2;
		if (start1R >= row) 
		{
			start1R = start1R % row;
			System.out.println(start1R);
		}
		if (start2R >= row) 
		{
			start2R = start1R % row + 1;
			System.out.println(start2R);
		}
		if (start2C >= column) 
		{
			start2C = start1C % column + 1;
			System.out.println(start2C);
		}
		if (start1C >= column) 
		{
			start1C = start1C % column;
			System.out.println(start1C);
		}

		
		if (shape.equals("glider"))
		{
			tiles[startR][start1C] = new MonoTile(1);
			tiles[start1R][start2C] = new MonoTile(1);
			tiles[start2R][startC] = new MonoTile(1);
			tiles[start2R][start1C] = new MonoTile(1);
			tiles[start2R][start2C] = new MonoTile(1);
			
		}
			
		else if (shape.equals("oscillator"))
		{
			tiles[startR][startC] = new MonoTile(1);
			tiles[startR][start1C] = new MonoTile(1);
			tiles[startR][start2C] = new MonoTile(1);
		}
	}
	
	public void help()
	{
		System.out.println("These are the available setShape patterns: ");
		System.out.println("To draw a shape, call the setShape method with the name of the shape");
		System.out.println();
		// also may want to include example command "setShape name ....."
		// also should i include minimum grid size (starting 0,0)?
		String[] help = {"glider", "oscillator"};
		for (int i = 0; i < help.length; i++)
		{
			System.out.println("-" + help[i]);
		}
	}
	
	public void custom(String command)
	{
		int r = 0;
		int c = 0;
		int count = 0;
		for (int i = 0; i < command.length(); i++)
		{
			count++;
			if (!command.substring(i, i + 1).equals("."))
			{
				if (command.substring(i, i + 1).equals("O")) //capital o "O"
				{
					c += count - 1;
					//System.out.println("count: " + count + " c: " + c);
					tiles[r][c] = new MonoTile(1);
					c = 0;
				}
				if (command.substring(i, i + 1).equals("n"))
				{
					r++;
					c = 0;
					count = 0;
				}				
			}	
		}
	}
}
//ATTENTION ALL "THIS." TO REFER TO THE CLASS STATE OF THE ARRAY OF TILES HAS BEEN REMOVED (EXCEPT IN CONSTRUCTOR)


