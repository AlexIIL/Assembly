/*
 * Copyright (c) 2018 modmuss50 and Gigabit101
 *
 *
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 *
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 *
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package teamreborn.assembly.util;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

/**
 * Created by mark on 12/04/15.
 */
public class ItemUtils {

	public static boolean isItemEqual(final ItemStack a, final ItemStack b, final boolean matchDamage,
	                                  final boolean matchNBT) {
		if (a.isEmpty() || b.isEmpty())
			return false;
		if (a.getItem() != b.getItem())
			return false;
		if (matchNBT && !ItemStack.areEqualIgnoreTags(a, b))
			return false;
		if (matchDamage) {
			if (a.getDamage() != b.getDamage())
				return false;
		}
		return true;
	}

	public static boolean isItemEqual(ItemStack a, ItemStack b, boolean matchDamage, boolean matchNBT,
	                                  boolean useTags) {
		if (isItemEqual(a, b, matchDamage, matchNBT)) {
			return true;
		}
		if (a.isEmpty() || b.isEmpty())
			return false;
		if (useTags) {
			//TODO check tags
		}
		return false;
	}


	public static void writeInvToNBT(Inventory inv, String tag, CompoundTag data) {
		ListTag list = new ListTag();
		for (byte slot = 0; slot < inv.getInvSize(); slot++) {
			ItemStack stack = inv.getInvStack(slot);
			if (!stack.isEmpty()) {
				CompoundTag itemTag = new CompoundTag();
				itemTag.putInt("Slot", slot);
				writeItemToNBT(stack, itemTag);
				list.add(itemTag);
			}
		}
		data.put(tag, list);
	}

	public static void readInvFromNBT(Inventory inv, String tag, CompoundTag data) {
		ListTag list = data.getList(tag, 10);
		for (byte entry = 0; entry < list.size(); entry++) {
			CompoundTag itemTag = list.getCompoundTag(entry);
			int slot = itemTag.getByte("Slot");
			if (slot >= 0 && slot < inv.getInvSize()) {
				ItemStack stack = readItemFromNBT(itemTag);
				inv.setInvStack(slot, stack);
			}
		}
	}

	public static void writeItemToNBT(ItemStack stack, CompoundTag data) {
		if (stack.isEmpty() || stack.getAmount() <= 0)
			return;
		stack.serialize(data);
	}

	public static ItemStack readItemFromNBT(CompoundTag data) {
		return ItemStack.fromTag(data);
	}

}