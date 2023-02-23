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
package de.featjar.clauses.solutions.combinations;

/**
 * Combination iterator that reverses the order of
 * {@link LexicographicIterator}.
 *
 * @author Sebastian Krieter
 */
public class InverseLexicographicIterator extends ACombinationIterator {

    private final int[] c;

    public InverseLexicographicIterator(int t, int size) {
        super(size, t);
        c = new int[t];
        for (int i = t; i > 0; i--) {
            c[t - i] = n - i;
        }
        c[t - 1] = n;
    }

    @Override
    protected int[] computeNext() {
        int i = t - 1;
        for (; i >= 0; i--) {
            if (i == 0) {
                c[i]--;
            } else if ((c[i - 1] + 1) < c[i]) {
                c[i]--;
                return c;
            } else {
                c[i] = (n - t) + i;
            }
        }
        if (c[0] < 0) {
            return null;
        }

        return c;
    }
}
