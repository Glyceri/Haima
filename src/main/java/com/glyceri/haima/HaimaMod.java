package com.glyceri.haima;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.EmptyLoadContext;
import net.fabricmc.api.ModInitializer;

import com.glyceri.haima.blocks.BlocksHandler;
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
			BlocksHandler.initialise(Balm.getBlocks());
			HaimaComponents.initialise(Balm.getComponents());
			
		});
	}
	
	
	/* Time to yap!
	 * Haima is meant to be quite a dark mod that you really shouldn't want to deal with.
	 * 
	 * There is this organization simply called "Het Gilde" (The guild) (because im bad at naming things)
	 * that will send you a letter after sleeping 3x
	 * The letter informs you to perform a ritual which will be a simple one. But you have to do it every time you want
	 * to interact with het gilde.
	 * Interacting with het gilde constists of 3 things. You get a task, you complete a task or you create a task.
	 * What are these tasks?
	 * 
	 * Assassinations
	 * 
	 * Het gilde will put up assassinations around the world and completing them will earn you bloodpoints.
	 * Dying... any death will COST you bloodpoints. You can got negative with bloodpoints, but only up until a certain point.
	 * 
	 * You can trade bloodpoints for a rogue set that will have very special abilities. I hope it can make your nameplate perma dissapear. Make your footsteps and other kinds of sounds gone, stuff like that.
	 * You can also trade it for assassination requests. You can request a player to kill. Players that have not done the ritual can NOT be targeted, het gilde is only after tainted blood.
	 */
}