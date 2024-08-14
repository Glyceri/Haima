package com.glyceri.haima.items.haimaItems;

import java.util.List;
import java.util.UUID;

import com.glyceri.haima.items.HaimaTaggedItem;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class HaimaBoundScroll extends HaimaTaggedItem
{
	public static final String ITEM_ID = "bound_scroll";
	
	public HaimaBoundScroll(Properties properties)
	{
		super(properties);
	}	
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) 
	{
		boolean client = level.isClientSide;
		if (client) return super.use(level, player, interactionHand);
		
		ItemStack stack = player.getItemInHand(interactionHand);
		if (stack == null) return super.use(level, player, interactionHand);
		
		if (stack.getItem() instanceof HaimaBoundScroll)
		{
			UUID ownerUUID = HaimaBoundScroll.getTaggedUUID(stack);
			if (ownerUUID == null) return super.use(level, player, interactionHand);
			
			for (ServerLevel serverWorld : level.getServer().getAllLevels()) 
			{
				if (serverWorld.getEntity(ownerUUID) instanceof LivingEntity entity && entity.isAlive()) 
				{
					entity.setRemainingFireTicks(20);
				}
			}
		}
		return super.use(level, player, interactionHand);
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
