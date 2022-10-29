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

package de.siphalor.nbtcrafting.dollar.part.operator;

import org.apache.commons.lang3.StringUtils;

import de.siphalor.nbtcrafting.dollar.DollarDeserializationException;
import de.siphalor.nbtcrafting.dollar.DollarException;
import de.siphalor.nbtcrafting.dollar.DollarParser;
import de.siphalor.nbtcrafting.dollar.part.DollarPart;
import de.siphalor.nbtcrafting.dollar.part.ValueDollarPart;
import de.siphalor.nbtcrafting.util.NumberUtil;

public class ProductDollarOperator extends BinaryDollarOperator {
	public ProductDollarOperator(DollarPart first, DollarPart second) {
		super(first, second);
	}

	public static DollarPart of(DollarPart first, DollarPart second) throws DollarException {
		DollarPart instance = new ProductDollarOperator(first, second);
		if (first.isConstant() && second.isConstant()) {
			return ValueDollarPart.of(instance.evaluate(null));
		}
		return null;
	}

	@Override
	public Object apply(Object first, Object second) {
		if (first instanceof Number || first == null) {
			first = NumberUtil.denullify((Number) first);
			if (second instanceof Number || second == null)
				return NumberUtil.product((Number) first, (Number) second);
			return StringUtils.repeat(second.toString(), ((Number) first).intValue());
		} else if (second instanceof Number || second == null) {
			return StringUtils.repeat(first.toString(), NumberUtil.denullify((Number) second).intValue());
		}
		return null;
	}

	public static class Deserializer implements DollarPart.Deserializer {
		@Override
		public boolean matches(int character, DollarParser dollarParser) {
			return character == '*';
		}

		@Override
		public DollarPart parse(DollarParser dollarParser, DollarPart lastDollarPart, int priority) throws DollarDeserializationException {
			if (lastDollarPart == null) {
				throw new DollarDeserializationException("Unexpected asterisk!");
			}
			dollarParser.skip();
			return new ProductDollarOperator(lastDollarPart, dollarParser.parse(priority));
		}
	}
}
