package com.glyceri.haima.items.haimaItems;

import com.glyceri.haima.items.HaimaItem;

public class HaimaBlankScroll extends HaimaItem
{
	final String ITEM_ID = "blank_scroll";
	
	public HaimaBlankScroll(Properties properties)
	{
		super(properties);
	}

	@Override
	public String GetItemID() { return ITEM_ID; }
	
}
