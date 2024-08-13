package com.glyceri.haima.items;

import net.minecraft.item.Item;

public abstract class HaimaItem extends Item 
{
	public HaimaItem(Settings settings)
	{
		super(settings);
		
	}

	public abstract String GetItemID();
}
