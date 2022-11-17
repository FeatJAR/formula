/*
 * Copyright (C) 2022 Sebastian Krieter, Elias Kuiter
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
 * See <https://github.com/FeatureIDE/FeatJAR-formula> for further information.
 */
package de.featjar.formula.analysis.value;

import de.featjar.formula.analysis.AssignmentList;

import java.util.*;

/**
 * A list of value assignments.
 *
 * @param <U> the type of the implementing subclass
 * @param <T> the type of the literal list
 * @author Elias Kuiter
 */
public abstract class ValueAssignmentList<T extends ValueAssignmentList<?, U>, U extends ValueAssignment> implements AssignmentList<U> {
    protected final List<U> literalLists;

    public ValueAssignmentList() {
        literalLists = new ArrayList<>();
    }

    public ValueAssignmentList(int size) {
        literalLists = new ArrayList<>(size);
    }

    public ValueAssignmentList(Collection<? extends U> literalLists) {
        this.literalLists = new ArrayList<>(literalLists);
    }

    @SuppressWarnings("unchecked")
    public ValueAssignmentList(ValueAssignmentList<T, U> other) {
        literalLists = new ArrayList<>(other.size());
        other.stream().map(U::clone).forEach(predicateList -> add((U) predicateList));
    }

    protected abstract T newLiteralMatrix(List<U> literalLists);

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public T clone() {
        return newLiteralMatrix(literalLists);
    }

    @Override
    public List<U> getAll() {
        return literalLists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueAssignmentList<?, ?> that = (ValueAssignmentList<?, ?>) o;
        return Objects.equals(literalLists, that.literalLists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(literalLists);
    }
}
