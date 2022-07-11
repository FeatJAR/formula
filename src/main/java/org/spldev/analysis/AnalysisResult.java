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
package org.spldev.analysis;

import java.util.*;

import org.spldev.clauses.*;

/**
 * Wrapper class for an analysis result containing additional information about
 * the performed analysis.
 *
 * @param <T> Type of the analysis result.
 *
 * @author Sebastian Krieter
 */
public class AnalysisResult<T> {

	private final String id;
	private final LiteralList assumptions;
	private final int hashCode;
	private final T result;

	public AnalysisResult(String id, LiteralList assumptions, T result) {
		this.id = id;
		this.assumptions = assumptions;
		this.result = result;
		this.hashCode = (31 * id.hashCode()) + Arrays.hashCode(assumptions.getLiterals());
	}

	public String getId() {
		return id;
	}

	public LiteralList getAssumptions() {
		return assumptions;
	}

	public T getResult() {
		return result;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		final AnalysisResult<?> other = (AnalysisResult<?>) obj;
		return id.equals(other.id) && Arrays.equals(assumptions.getLiterals(), other.assumptions.getLiterals());
	}

}
