package gameOfLife;

import java.awt.Color;

public class ConstantTile implements Tile
{
	private int age;
	
	public ConstantTile(int age)
	{
		this.age = age;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public Color getColor()
	{
		Color c;
		if (age == 0)
		{
			c = Color.DARK_GRAY;
		}
		else
		{
			c = Color.LIGHT_GRAY;
		}
		
		return c;
	}
	
	public Tile getUpdatedTile(Tile[] neighbors)
	{
		ConstantTile ct = new ConstantTile(age);
		return ct;
		//possible error: Constant tile vs Tile
	}
}

