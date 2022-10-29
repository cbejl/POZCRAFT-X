/*
 * Copyright 2020-2022 Siphalor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.
 * See the License for the specific language governing
 * permissions and limitations under the License.
 */

package net.cbejltpycbl.pozcraft.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import de.siphalor.nbtcrafting.util.duck.IItemStack;

@Mixin(value = ItemStack.class, priority = 2000)
public class MixinItemStack implements IItemStack {
	@Shadow
	private NbtCompound nbt;

	@Inject(method = "areNbtEqual", at = @At(value = "RETURN", ordinal = 2), cancellable = true)
	private static void areTagsEqualReturn1(ItemStack stack1, ItemStack stack2, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
		if (stack2.getNbt().isEmpty())
			callbackInfoReturnable.setReturnValue(true);
	}

	@Inject(method = "areNbtEqual", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NbtCompound;equals(Ljava/lang/Object;)Z"), cancellable = true)
	private static void areTagsEqualReturn2(ItemStack stack1, ItemStack stack2, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
		if (stack1.getNbt() == null && stack2.getNbt().isEmpty())
			callbackInfoReturnable.setReturnValue(true);
	}

	@Unique
	@Override
	public void nbtCrafting$setRawTag(NbtCompound tag) {
		if (tag == null || tag.isEmpty())
			this.nbt = null;
		else
			this.nbt = tag;
	}
}
