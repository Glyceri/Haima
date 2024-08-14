package com.glyceri.haima.items;

import java.util.UUID;

import com.glyceri.haima.components.HaimaComponents;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public abstract class HaimaTaggedItem extends HaimaItem
{
	public HaimaTaggedItem(Properties properties)
	{
		super(properties);
	}
	
	public static void addItemToInventoryAndConsume(Player player, InteractionHand hand, ItemStack toAdd) {
		boolean shouldAdd = false;
		ItemStack stack = player.getItemInHand(hand);
		if (stack.getCount() == 1) {
			if (player.isCreative()) {
				shouldAdd = true;
			} else {
				player.setItemInHand(hand, toAdd);
			}
		} else {
			stack.shrink(1);
			shouldAdd = true;
		}
		if (shouldAdd) {
			if (!player.getInventory().add(toAdd)) {
				player.drop(toAdd, false, true);
			}
		}
	}

	
	public static ItemStack setTagged(ItemStack stack, Entity entity) 
	{
		stack.set(HaimaComponents.playerUUID.get(), entity.getUUID());
		stack.set(HaimaComponents.playerName.get(), entity.getDisplayName().getString());
		return stack;
	}
	
	public static boolean hasUUID(ItemStack stack) 
	{
		return stack.get(HaimaComponents.playerUUID.get()) != null;
	}
	
	public static void removeTag(ItemStack stack) 
	{
		if (hasUUID(stack)) {
			stack.remove(HaimaComponents.playerName.get());
			stack.remove(HaimaComponents.playerUUID.get());
		}
	}
	
	public static String getTaggedName(ItemStack stack) 
	{
		if (hasUUID(stack)) 
		{
			return stack.get(HaimaComponents.playerName.get());
		}
		
		return null;
	}
	
	public static UUID getTaggedUUID(ItemStack stack)
	{
		if (hasUUID(stack)) 
		{
			return stack.get(HaimaComponents.playerUUID.get());
		}
		
		return null;
	}
}
