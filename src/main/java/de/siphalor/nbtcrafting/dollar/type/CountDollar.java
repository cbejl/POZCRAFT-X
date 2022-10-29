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

package de.siphalor.nbtcrafting.dollar.type;

import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.AbstractNbtNumber;
import net.minecraft.nbt.NbtElement;

import de.siphalor.nbtcrafting.api.nbt.NbtUtil;
import de.siphalor.nbtcrafting.dollar.Dollar;
import de.siphalor.nbtcrafting.dollar.DollarEvaluationException;
import de.siphalor.nbtcrafting.dollar.DollarException;
import de.siphalor.nbtcrafting.dollar.part.DollarPart;

public class CountDollar extends Dollar {
	public CountDollar(DollarPart expression) {
		super(expression);
	}

	@Override
	public void apply(ItemStack stack, Map<String, Object> references) throws DollarException {
		NbtElement value = NbtUtil.asTag(expression.evaluate(references));
		if (!(value instanceof AbstractNbtNumber)) {
			throw new DollarEvaluationException("Couldn't set dollar computed count of stack as it's not a number");
		} else {
			stack.setCount(((AbstractNbtNumber) value).intValue());
		}
	}
}
