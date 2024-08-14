package com.glyceri.haima.items.haimaItems;

import com.glyceri.haima.HaimaMod;
import com.glyceri.haima.items.HaimaTaggedItem;
import com.glyceri.haima.items.ItemHandler;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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
			tryCreateBloodBottle(player, livingEntity);
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
		
		tryCreateBloodBottle(player, player);
		
		if (player.isAlive()) 
		{		
			player.hurt(player.damageSources().cactus(), 2);
		}
		
		return super.use(level, player, interactionHand);
	}
	
	void damageKnife(ItemStack itemStack, LivingEntity livingEntity) 
	{
		itemStack.hurtAndBreak(1, livingEntity, EquipmentSlot.MAINHAND);
	}
	
	void tryCreateBloodBottle(Player dealer, LivingEntity target) 
	{
		ItemStack slot = getSlotWithBottles(dealer);
		if (slot == null) 
		{
			dealer.sendSystemMessage(Component.translatable(HaimaMod.MOD_ID + ".message.ritual_knife_no_bottle"));
			return;
		}

		removeGlassBottle(slot, dealer);
		
		ItemStack bloodBottle = createBloodBottle();
		HaimaTaggedItem.setTagged(bloodBottle, target);
		
		addBottleToInventory(bloodBottle, dealer);
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
	
	ItemStack createBloodBottle() 
	{
		return new ItemStack(ItemHandler.BLOOD_BOTTLE);
	}
	
	void addBottleToInventory(ItemStack stack, Player player) 
	{
		if (player.getInventory().add(stack)) return;
		
		player.drop(stack, false, true);
	}
	
	/*
	@Override
	public void postHurtEnemy(ItemStack itemStack, LivingEntity livingEntity, LivingEntity livingEntity2) 
	{
		itemStack.hurtAndBreak(1, livingEntity, EquipmentSlot.MAINHAND);
	}
	*/
}
