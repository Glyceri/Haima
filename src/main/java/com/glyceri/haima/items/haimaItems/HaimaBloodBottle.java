package com.glyceri.haima.items.haimaItems;

import com.glyceri.haima.items.HaimaTaggedItem;

public class HaimaBloodBottle extends HaimaTaggedItem
{
	public static final String ITEM_ID = "blood_bottle";

	public HaimaBloodBottle(Properties properties)
	{
		super(properties.stacksTo(16));
	}

	
}
