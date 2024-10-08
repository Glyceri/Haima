package com.glyceri.haima.components;

import com.glyceri.haima.HaimaMod;
import com.mojang.serialization.Codec;

import net.blay09.mods.balm.api.DeferredObject;
import net.blay09.mods.balm.api.component.BalmComponents;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

public class HaimaComponents
{
	public static DeferredObject<DataComponentType<UUID>> playerUUID;
	public static DeferredObject<DataComponentType<String>> playerName;
	
	public static void initialise(BalmComponents components) 
	{
		playerUUID = components.registerComponent(() -> DataComponentType.<UUID>builder().persistent(UUIDUtil.CODEC).build(), ResourceLocation.fromNamespaceAndPath(HaimaMod.MOD_ID, "haima_player_uuid"));
		playerName = components.registerComponent(() -> DataComponentType.<String>builder().persistent(Codec.STRING).build(), ResourceLocation.fromNamespaceAndPath(HaimaMod.MOD_ID, "haima_player_name"));
	}
}
