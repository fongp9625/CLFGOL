package gameOfLife;

import java.awt.Color;

public class CopycatTile implements Tile
{
	private int row;
	private int column;
	private World w;
	
	public CopycatTile(int row, int column, World w)
	{
		this.row = row;
		this.column = column;
		this.w = w;
		//mr george said not to do something here and i forgot what he said
	}

	public int getAge()
	{
		return w.getTile(row, column).getAge();
	}

	public Color getColor()
	{
		return w.getTile(row, column).getColor();
	}

	public Tile getUpdatedTile(Tile[] neighbors)
	{
		return new CopycatTile(row, column, w);
	}

}
