package com.glyceri.haima.blocks;

import com.glyceri.haima.HaimaMod;
import com.glyceri.haima.blocks.HaimaBlocks.HaimaBloodGlyph;
import com.glyceri.haima.colouring.ColourHandler;

import net.blay09.mods.balm.api.block.BalmBlocks;
import net.minecraft.resources.ResourceLocation;

public final class BlocksHandler
{
	
	public static HaimaBloodGlyph BLOOD_GLYPH;
	
	public static void initialise(BalmBlocks blocks) 
	{
		blocks.registerBlock(() -> BLOOD_GLYPH = new HaimaBloodGlyph(blocks.blockProperties(), 0x420101), id(HaimaBloodGlyph.BLOCK_ID));
		
		ColourHandler.initialiseBlockColours();
	}
	
	static ResourceLocation id(String name)
	{
		return ResourceLocation.fromNamespaceAndPath(HaimaMod.MOD_ID, name);
	}
}
