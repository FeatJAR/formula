/*
 * Copyright (C) 2023 Sebastian Krieter, Elias Kuiter
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
package de.featjar.analysis.solver;

import de.featjar.formula.structure.Formula;
import de.featjar.formula.structure.atomic.literal.VariableMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Modifiable formula for solvers.
 *
 * @param <O> type of the constraint object used within a solver
 *
 * @author Sebastian Krieter
 */
public interface DynamicFormula<O> {

    List<O> getConstraints();

    VariableMap getVariableMap();

    /**
     * Adds a clause.
     *
     * @param clause The clause to add.
     *
     * @return The identifying constraint object of the clause that can be used to
     *         remove it from the solver.
     *
     * @see #push(Collection)
     * @see #pop()
     * @see #pop(int)
     */
    List<O> push(Formula clause);

    /**
     * Adds multiple clauses.
     *
     * @param clauses A collection of clauses.
     *
     * @return A list of the identifying constraint objects of the added clauses
     *         that can be used to remove them from the solver.
     *
     * @see #push(Formula)
     * @see #pop()
     * @see #pop(int)
     */
    default List<O> push(Collection<Formula> clauses) {
        int addCount = 0;
        final ArrayList<O> constraintList = new ArrayList<>(clauses.size());
        for (final Formula clause : clauses) {
            try {
                push(clause);
                addCount++;
            } catch (final Exception e) {
                pop(addCount);
                throw e;
            }
        }
        return constraintList;
    }

    O peek();

    /**
     * Removes the last clause added to the solver. This method should be preferred
     * over remove, if possible.<br>
     * <b>Note:</b> This method may not be supported by all solvers.
     */
    O pop();

    /**
     * Removes the last clauses added to the solver. This method should be preferred
     * over remove, if possible.<br>
     * <b>Note:</b> This method may not be supported by all solvers.
     *
     * @param count The number of clauses to be removed.
     *
     * @see #push(Formula)
     */
    default void pop(int count) {
        if (count > size()) {
            count = size();
        }
        for (int i = 0; i < count; i++) {
            pop();
        }
    }

    default void clear() {
        pop(size());
    }

    int size();

    /**
     * Removes a certain clause. If possible, instead of using this method consider
     * using {@link #pop()} as it runs faster.<br>
     * <b>Note:</b> This method may not be supported by all solvers.
     *
     * @param constraint The identifying constraint object for the clause.
     */
    void remove(O constraint);
}
