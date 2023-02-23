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
package de.featjar.clauses.solutions.metrics;

import de.featjar.clauses.LiteralList;
import java.util.List;
import java.util.function.DoubleSupplier;

public class CountMetrics extends AggregatableMetrics {

    private final CountFunction function;

    public CountMetrics(CountFunction function) {
        this.function = function;
    }

    public static List<SampleMetric> getAllAggregates(CountFunction function) {
        return new CountMetrics(function).getAllAggregates();
    }

    @Override
    protected double[] computeValues() {
        final List<LiteralList> solutions = sample.getSolutions();
        final int size = solutions.size();
        final double[] values = new double[size];
        for (int i = 0; i < (size - 1); i++) {
            values[i] = function.compute(solutions.get(i));
        }
        return values;
    }

    @Override
    public SampleMetric getAggregate(String name, DoubleSupplier aggregate) {
        return new DoubleMetric(function.getName() + "_count_" + name, aggregate);
    }
}
