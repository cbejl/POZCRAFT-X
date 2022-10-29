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

package de.siphalor.nbtcrafting.dollar;

import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtElement;

import de.siphalor.nbtcrafting.api.nbt.NbtUtil;
import de.siphalor.nbtcrafting.dollar.part.DollarPart;

public abstract class Dollar {
	protected DollarPart expression;

	protected Dollar(DollarPart expression) {
		this.expression = expression;
	}

	protected NbtElement evaluate(Map<String, Object> references) throws DollarEvaluationException {
		return NbtUtil.asTag(expression.evaluate(references));
	}

	public abstract void apply(ItemStack stack, Map<String, Object> references) throws DollarException;
}
