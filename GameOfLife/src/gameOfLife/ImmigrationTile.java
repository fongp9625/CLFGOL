package gameOfLife; 

import java.awt.Color;

public class ImmigrationTile extends LifeTile
{
	private Color color;
	private Color neighborMajorityColor;
	
	public ImmigrationTile(int age)
	{
		super(age);
		
		if (age == 1)
		{
			color = Color.GREEN;
		}
		
		else if (age == 2)
		{
			color = Color.BLUE;
		}
		
		else
		{
			color = Color.BLACK;
		}
	}

	public Color getColor()
	{		
		return color;
	}

	public Tile getUpdatedTile(Tile[] neighbors)
	{
		int active = getNumActiveNeighbors(neighbors);
		getNeighborsColor(neighbors);
		
		if (this.getAge() > 0)
		{
			if (active > 3 || active < 2) // goes dormant
			{
				return new ImmigrationTile(0);
			}
			else
			{
				ImmigrationTile nit = new ImmigrationTile(this.getAge() + 1);
				nit.color = this.color;
				return nit;
			}
		}
		else //is dormant
		{
			if (active == 3) // comes alive with age 1
			{
				ImmigrationTile nit = new ImmigrationTile(1);
				nit.color = neighborMajorityColor;
				return nit;
			}
			else //stays dormant
			{
				return new ImmigrationTile(0);
			}
		}
	}
	
	public void getNeighborsColor(Tile[] neighbors)
	{
		int blue = 0;
		int green = 0;
		//System.out.println("getNeighborsColor");
		for (int i = 0; i < neighbors.length; i++)
		{
			if (neighbors[i].getAge() > 0)
			{
				if (neighbors[i].getColor() == Color.BLUE)
				{
					blue++;
					//System.out.println("blue " + blue);
				}
				else if (neighbors[i].getColor() == Color.GREEN)
				{
					green++;
					//System.out.println("green " + green);
				}
					
			}
			
		}
		if (blue > green)
		{
			neighborMajorityColor = Color.BLUE;
		}
			
		else 
		{
			neighborMajorityColor = Color.GREEN;
		}
			
	}
}
