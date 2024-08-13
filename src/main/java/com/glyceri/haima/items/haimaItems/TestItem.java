package com.glyceri.haima.items.haimaItems;

import com.glyceri.haima.items.HaimaItem;

public class TestItem extends HaimaItem
{
	final String ITEM_ID = "test_item";
	
	public TestItem() 
	{
		super(new Settings());
	}

	@Override public String GetItemID() { return ITEM_ID; }
}
