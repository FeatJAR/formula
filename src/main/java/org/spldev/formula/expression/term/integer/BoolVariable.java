/* -----------------------------------------------------------------------------
 * Formula Lib - Library to represent and edit propositional formulas.
 * Copyright (C) 2021  Sebastian Krieter
 * 
 * This file is part of Formula Lib.
 * 
 * Formula Lib is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * 
 * Formula Lib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Formula Lib.  If not, see <https://www.gnu.org/licenses/>.
 * 
 * See <https://github.com/skrieter/formula> for further information.
 * -----------------------------------------------------------------------------
 */
package org.spldev.formula.expression.term.integer;

import org.spldev.formula.expression.atomic.literal.*;
import org.spldev.formula.expression.term.*;

public class BoolVariable extends Variable<Boolean> {

	public BoolVariable(int index, VariableMap map) {
		super(index, map);
	}

	protected BoolVariable(BoolVariable oldVariable) {
		super(oldVariable);
	}

	@Override
	public Class<Boolean> getType() {
		return Boolean.class;
	}

	@Override
	public BoolVariable cloneNode() {
		return new BoolVariable(this);
	}

	@Override
	public BoolVariable clone(int index, VariableMap map) {
		final BoolVariable variable = new BoolVariable(index, map);
		variable.setDefaultValue(defaultValue);
		return variable;
	}

}