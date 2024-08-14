package com.glyceri.haima.items.haimaItems;

import com.glyceri.haima.HaimaMod;
import com.glyceri.haima.items.HaimaTaggedItem;
import com.glyceri.haima.items.ItemHandler;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class HaimaRitualKnife extends HaimaTaggedItem
{
	public static final String ITEM_ID = "ritual_knife";
	
	public HaimaRitualKnife(Properties properties)
	{
		super(properties.durability(214).stacksTo(1));
	}

	@Override
	public boolean hurtEnemy(ItemStack itemStack, LivingEntity livingEntity, LivingEntity livingEntity2) 
	{
		if (livingEntity2 instanceof Player player) 
		{
			tryCreateBloodBottle(itemStack, player, livingEntity);
		}
		return super.hurtEnemy(itemStack, livingEntity, livingEntity2);
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) 
	{
		boolean client = level.isClientSide;
		if (client) return super.use(level, player, interactionHand);
		
		ItemStack stack = player.getItemInHand(interactionHand);
		if (stack == null) return super.use(level, player, interactionHand);
		
		tryCreateBloodBottle(stack, player, player);
		
		if (player.isAlive()) 
		{		
			player.hurt(player.damageSources().cactus(), 2);
			damageKnife(stack, player);
		}
		
		return super.use(level, player, interactionHand);
	}
	
	void damageKnife(ItemStack itemStack, LivingEntity livingEntity) 
	{
		itemStack.hurtAndBreak(1, livingEntity, EquipmentSlot.MAINHAND);
	}
	
	void tryCreateBloodBottle(ItemStack ritualKnife, Player dealer, LivingEntity target) 
	{
		ItemStack slot = getSlotWithBottles(dealer);
		
		if (slot == null) 
		{
			dealer.sendSystemMessage(Component.translatable(HaimaMod.MOD_ID + ".message.ritual_knife_no_bottle"));
			return;
		}

		removeGlassBottle(slot, dealer);
		handleBloodBottle(dealer, target);
		setKnifeCooldown(dealer, ritualKnife);
	}
	
	ItemStack getSlotWithBottles(Player dealer) 
	{
		for(ItemStack curSlot : dealer.getInventory().items) 
		{
			if (curSlot.is(Items.GLASS_BOTTLE)) 
			{
				return curSlot;
			}
		}
		
		return null;
	}
	
	void removeGlassBottle(ItemStack stack, Player dealer) 
	{
		if (dealer.isCreative()) return;
		
		stack.shrink(1);
	}
	
	void handleBloodBottle(Player dealer, LivingEntity target) 
	{
		ItemStack bloodBottle = createBloodBottle();
		HaimaTaggedItem.setTagged(bloodBottle, target);
		addBottleToInventory(bloodBottle, dealer);
	}
	
	void setKnifeCooldown(Player dealer, ItemStack ritualKnife) 
	{
		ItemCooldowns cooldowns = dealer.getCooldowns();
		cooldowns.addCooldown(ritualKnife.getItem(), 20);
	}
	
	ItemStack createBloodBottle() 
	{
		return new ItemStack(ItemHandler.BLOOD_BOTTLE);
	}
	
	void addBottleToInventory(ItemStack stack, Player player) 
	{
		if (player.getInventory().add(stack)) return;
		
		player.drop(stack, false, true);
	}
}
