package gameOfLife;

import java.awt.Color;

public class RainbowTile implements Tile
{
	private int age;
	
	public RainbowTile(int age)
	{
		this.age = age;
	}

	public int getAge()
	{
		return age;
	}

	public Color getColor()
	{
		if (age % 6 == 0)
		{
			return Color.RED;
		}
		else if (age % 6 == 1)
		{
			return Color.ORANGE;
		}
		else if (age % 6 == 2)
		{
			return Color.YELLOW;
		}
		else if (age % 6 == 3)
		{
			return Color.GREEN;
		}
		else if (age % 6 == 4)
		{
			return Color.BLUE;
		}
		else
		{
			return Color.MAGENTA;
		}
	}

	public Tile getUpdatedTile(Tile[] neighbors)
	{
		RainbowTile rtclone = new RainbowTile(age + 1);
		return rtclone;
		//same issue as constant tile: rbt vs tile
	}

}
