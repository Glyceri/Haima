package com.glyceri.haima;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.EmptyLoadContext;
import net.fabricmc.api.ModInitializer;

import com.glyceri.haima.components.HaimaComponents;
import com.glyceri.haima.items.ItemHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HaimaMod implements ModInitializer 
{
    public static final Logger LOGGER = LoggerFactory.getLogger("haima");
    public static final String MOD_ID = "haima";
    
	@Override
	public void onInitialize() 
	{
		LOGGER.info("Starting Haima");
		Balm.initialize(HaimaMod.MOD_ID, EmptyLoadContext.INSTANCE, () ->
		{
			ItemHandler.Initialise(Balm.getItems());
			HaimaComponents.initialise(Balm.getComponents());
		});
	}
}