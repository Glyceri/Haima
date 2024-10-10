package com.glyceri.haima;

import com.glyceri.haima.blocks.BlocksHandler;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

@Environment(EnvType.CLIENT)
public class HaimaModClient implements ClientModInitializer 
{
	
	@Override
	public void onInitializeClient() 
	{
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksHandler.BLOOD_GLYPH, RenderType.cutout());
	}
}