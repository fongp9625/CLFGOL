package gameOfLife;

import java.awt.Color;

public class MonoTile extends LifeTile
{
	public MonoTile(int age)
	{
		super(age);
	}

	public Tile getUpdatedTile(Tile[] neighbors)
	{
		int active = getNumActiveNeighbors(neighbors);
		if (this.getAge() > 0)
		{
			if (active > 3 || active < 2)
			{
				return new MonoTile(0);
			}
			else
			{
				return new MonoTile(this.getAge() + 1);
			}
		}
		else
		{
			if (active == 3)
			{
				return new MonoTile(1);
			}
			else
			{
				return new MonoTile(0);
			}
		}
	}

	public Color getColor()
	{
		if (this.getAge() > 0)
		{
			return Color.WHITE;
		}
		else
		{
			return Color.BLACK;
		}
	}
}
