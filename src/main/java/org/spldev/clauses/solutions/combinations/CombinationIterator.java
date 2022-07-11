/* -----------------------------------------------------------------------------
 * formula - Propositional and first-order formulas
 * Copyright (C) 2020 Sebastian Krieter
 * 
 * This file is part of formula.
 * 
 * formula is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3.0 of the License,
 * or (at your option) any later version.
 * 
 * formula is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with formula. If not, see <https://www.gnu.org/licenses/>.
 * 
 * See <https://github.com/FeatJAR/formula> for further information.
 * -----------------------------------------------------------------------------
 */
package org.spldev.clauses.solutions.combinations;

import java.util.*;

/**
 * An iterator for combinations.
 *
 * @author Sebastian Krieter
 */
public interface CombinationIterator extends Iterator<int[]>, Iterable<int[]> {

	public static <T> T[] select(List<T> items, int[] indices, T[] selection) {
		for (int i = 0; i < indices.length; i++) {
			selection[i] = items.get(indices[i]);
		}
		return selection;
	}

	public static <T> T[] select(T[] items, int[] indices, T[] selection) {
		for (int i = 0; i < indices.length; i++) {
			selection[i] = items[indices[i]];
		}
		return selection;
	}

	long getIndex();

	void reset();

	long size();

}
