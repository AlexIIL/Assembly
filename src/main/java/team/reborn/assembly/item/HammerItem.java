package team.reborn.assembly.item;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import reborncore.common.recipes.ExtendedRecipeRemainder;

import java.util.HashSet;

public class HammerItem extends MiningToolItem implements ExtendedRecipeRemainder {

	public HammerItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
		super(attackDamage, attackSpeed, material, new HashSet<>(), settings);
	}

	@Override
	public ItemStack getRemainderStack(ItemStack stack) {
		stack.damage(1, RANDOM, null);
		if (stack.getDamage() >= stack.getMaxDamage()) {
			return ItemStack.EMPTY;
		}
		return stack;
	}

	@Override
	public float getMiningSpeed(ItemStack stack, BlockState state) {
		return true ? this.miningSpeed : 1.0F;
	}
}
