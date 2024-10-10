package com.glyceri.haima.colouring;

import com.glyceri.haima.blocks.BlocksHandler;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

public class ColourHandler
{
	public static void initialiseBlockColours() 
	{
		ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BlocksHandler.BLOOD_GLYPH.getColour(), BlocksHandler.BLOOD_GLYPH);
	}
}
