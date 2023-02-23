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

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Combination iterator that iterates of the combinations in lexicographical
 * order.
 *
 * @author Sebastian Krieter
 */
public class LexicographicIterator extends ACombinationIterator {

    public static Stream<int[]> stream(int t, int size) {
        return StreamSupport.stream(new LexicographicIterator(t, size).spliterator(), false);
    }

    private final int[] c;

    public LexicographicIterator(int t, int size) {
        super(size, t);
        c = new int[t];
        for (int i = 0; i < (c.length - 1); i++) {
            c[i] = i;
        }
        c[t - 1] = t - 2;
    }

    @Override
    protected int[] computeNext() {
        int i = t;
        for (; i > 0; i--) {
            final int ci = ++c[i - 1];
            if (ci < ((n - t) + i)) {
                break;
            }
        }
        if ((i == 0) && (c[i] == ((n - t) + 1))) {
            return null;
        }

        for (; i < t; i++) {
            if (i == 0) {
                c[i] = 0;
            } else {
                c[i] = c[i - 1] + 1;
            }
        }
        return c;
    }

    @Override
    public void reset() {
        super.reset();
        for (int i = 0; i < (c.length - 1); i++) {
            c[i] = i;
        }
        c[t - 1] = t - 2;
    }
}
