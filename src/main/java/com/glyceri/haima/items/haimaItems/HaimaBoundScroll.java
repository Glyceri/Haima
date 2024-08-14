package com.glyceri.haima.items.haimaItems;

import java.util.List;

import com.glyceri.haima.items.HaimaTaggedItem;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class HaimaBoundScroll extends HaimaTaggedItem
{
	public static final String ITEM_ID = "bound_scroll";
	
	public HaimaBoundScroll(Properties properties)
	{
		super(properties);
	}	
	
	@Override
	public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag tooltipFlag)
	{
		if (!hasUUID(itemStack)) return;
		
		tooltip.add(Component.literal(getTaggedName(itemStack)).withStyle(ChatFormatting.GRAY));
	
		boolean shifting = Screen.hasShiftDown();
		if (!shifting) return;
		
		tooltip.add(Component.literal(getTaggedUUID(itemStack).toString()).withStyle(ChatFormatting.DARK_GRAY));
	}
}
