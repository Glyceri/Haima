package com.glyceri.haima.items.haimaItems;

import com.glyceri.haima.items.HaimaTaggedItem;
import com.glyceri.haima.items.ItemHandler;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HaimaBlankScroll extends HaimaTaggedItem
{
	public static final String ITEM_ID = "blank_scroll";
	
	public HaimaBlankScroll(Properties properties)
	{
		super(properties);
	}

	
	@Override
	public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand interactionHand)
	{
		// Idk if it can be null, but im not about to find out c:
		if (livingEntity != null) 
		{
			tryTag(player, livingEntity, interactionHand);
		}
		
		return super.interactLivingEntity(itemStack, player, livingEntity, interactionHand);
	}
	
	
	InteractionResult tryTag(Player player, LivingEntity entity, InteractionHand hand) 
	{
		ItemStack stack = player.getItemInHand(hand);
		
		if (entity.isAlive() && !hasUUID(stack))
		{
			Level world = player.getCommandSenderWorld();
			if (world != null) 
			{			
				boolean client = world.isClientSide;
			
				if (!client) 
				{
					if (entity instanceof Mob mob) 
					{
						mob.setPersistenceRequired();
					}
					super.addItemToInventoryAndConsume(player, hand, setTagged(new ItemStack(ItemHandler.BOUND_SCROLL), entity));
				}
				
				return InteractionResult.sidedSuccess(client);
			}
		}
		
		return InteractionResult.FAIL;
	}
}
