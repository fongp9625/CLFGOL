package gameOfLife;

import java.awt.Color;

public abstract class LifeTile implements Tile
{
	private int age;
	
	public LifeTile(int age)
	{
		this.age = age;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public int getNumActiveNeighbors(Tile[] neighbors)
	{
		int active = 0;
		for (int i = 0; i < neighbors.length; i++)
		{
			if (neighbors[i].getAge() > 0)
			{
				//System.out.println("neighbor " + i + neighbors[i].getColor());
				active++;
			}
		}
		return active;
	}
	
	public abstract Color getColor();
	
	public abstract Tile getUpdatedTile(Tile[] neighbors);
}
