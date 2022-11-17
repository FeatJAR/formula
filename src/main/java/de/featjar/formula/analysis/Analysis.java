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
package de.featjar.formula.analysis;

import de.featjar.base.data.Computation;

import java.util.Optional;
import java.util.Random;

/**
 * Base class for an analysis performed by a {@link Solver solver}.
 * Contains several mixins to control exactly what capabilities a concrete implementation has.
 *
 * @param <T> the type of the (primary) analysis input
 * @param <U> the type of the analysis result
 * @author Sebastian Krieter
 * @author Elias Kuiter
 */
public interface Analysis<T, U> extends Computation<U> {
    /**
     * {@return the input computation of this analysis}
     * This analysis uses the result of this computation as its primary input (e.g., the formula to analyze).
     */
    Computation<T> getInputComputation();

    /**
     * Sets the input computation of this analysis.
     *
     * @param inputComputation the input computation
     * @return itself
     */
    Analysis<T, U> setInputComputation(Computation<T> inputComputation);

    /**
     * A potentially long-running analysis that can be canceled if a given time has passed.
     */
    interface WithTimeout {
        /**
         * {@return the timeout of this analysis in milliseconds, if any}
         * This analysis terminates with an empty {@link de.featjar.base.data.Result} when it has
         * not terminated until the timeout passes.
         */
        Optional<Long> getTimeout();

        /**
         * Sets the timeout of this analysis in milliseconds.
         *
         * @param timeout the timeout in milliseconds
         * @return itself
         */
        WithTimeout setTimeout(Long timeout);
    }

    /**
     * An analysis that can be passed a further assignment to assume.
     *
     * @param <T> the type of the assignment
     */
    interface WithAssumedAssignment<T extends Assignment<?>> {
        /**
         * {@return the assignment assumed by this analysis}
         * This analysis can freely interpret this assignment.
         * Usually, it is interpreted as a conjunction (i.e., similar to a {@link Solution}).
         */
        T getAssumedAssignment();

        /**
         * Sets the assignment assumed by this analysis.
         *
         * @param assignment the assignment
         * @return itself
         */
        WithAssumedAssignment<T> setAssumedAssignment(T assignment);
    }

    /**
     * An analysis that can be passed a further list of clauses to assume.
     *
     * @param <T> type of the clause list
     */
    interface WithAssumedClauseList<T extends AssignmentList<? extends Clause<?>>> {
        /**
         * {@return the clause list assumed by this analysis}
         * This analysis interprets this list of clauses as a conjunction of
         * disjunctions of literals or equalities (i.e., a CNF).
         */
        T getAssumedClauseList();

        /**
         * Sets the clause list assumed by this analysis.
         *
         * @param clauseList the clause list
         * @return itself
         */
        WithAssumedClauseList<T> setAssumedClauseList(T clauseList);
    }

    /**
     * An analysis that may need to generate pseudorandom numbers.
     */
    interface WithRandom {
        /**
         * The default seed for the pseudorandom number generator returned by {@link #getRandom()}, if not specified otherwise.
         */
        int DEFAULT_RANDOM_SEED = 0;

        /**
         * {@return the pseudorandom number generator of this analysis}
         */
        Random getRandom();

        /**
         * Sets the pseudorandom number generator of this analysis.
         *
         * @param random the pseudorandom number generator
         * @return itself
         */
        WithRandom setRandom(Random random);
    }
}
