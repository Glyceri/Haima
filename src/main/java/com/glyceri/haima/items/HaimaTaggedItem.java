package com.glyceri.haima.items;

import java.util.UUID;

import net.minecraft.world.item.ItemStack;

public abstract class HaimaTaggedItem extends HaimaItem
{
	public HaimaTaggedItem(Properties properties)
	{
		super(properties);
	}
	
	/*public static boolean hasUUID(ItemStack stack)
	{
		
		
	}*/
	
	public static UUID getTaggedUUID(ItemStack stack)
	{
		
		return null;
	}
}
