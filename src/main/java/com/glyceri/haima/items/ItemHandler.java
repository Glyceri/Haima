package com.glyceri.haima.items;

import com.glyceri.haima.HaimaMod;
import com.glyceri.haima.items.haimaItems.HaimaBlankScroll;
import com.glyceri.haima.items.haimaItems.TestItem;

import net.blay09.mods.balm.api.item.BalmItems;
import net.minecraft.resources.ResourceLocation;

public final class ItemHandler
{
	public static HaimaItem TEST_ITEM;
	public static HaimaBlankScroll BLANK_SCROLL;
	
	public static void Initialise(BalmItems items) 
	{
		items.registerItem(() -> TEST_ITEM = new TestItem(items.itemProperties()), id(TEST_ITEM.GetItemID()));
		items.registerItem(() -> BLANK_SCROLL = new HaimaBlankScroll(items.itemProperties()), id(BLANK_SCROLL.GetItemID()));
	}

    static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(HaimaMod.MOD_ID, name);
    }
}
