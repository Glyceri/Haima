package com.glyceri.haima.items;

import com.glyceri.haima.HaimaMod;
import com.glyceri.haima.items.haimaItems.HaimaBlankScroll;
import com.glyceri.haima.items.haimaItems.TestItem;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ItemHandler 
{
	public static final HaimaItem TEST_ITEM = new TestItem();
	
	public static final HaimaBlankScroll BLANK_SCROLL = new HaimaBlankScroll();
	
	public ItemHandler() { _register(); }
	
	void _register() 
	{
		register(TEST_ITEM);
		register(BLANK_SCROLL);
	}
	
	void register(HaimaItem haimaItem) 
	{
		Identifier itemID = Identifier.of(HaimaMod.MOD_ID, haimaItem.GetItemID());
		
		Registry.register(Registries.ITEM, itemID, haimaItem);
	}
}
