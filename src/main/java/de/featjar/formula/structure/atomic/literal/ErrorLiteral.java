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
package de.featjar.formula.structure.atomic.literal;

import de.featjar.formula.structure.Formula;
import de.featjar.formula.structure.Terminal;
import java.util.List;
import java.util.Objects;

/**
 * A positive or negative literal that results from a parsing or other error in
 * the formula.
 *
 * @author Sebastian Krieter
 */
public class ErrorLiteral extends Terminal implements Literal {

    private final String errorMessage;
    private final boolean positive;

    public ErrorLiteral(String errorMessage) {
        this(errorMessage, true);
    }

    public ErrorLiteral(String errorMessage, boolean positive) {
        this.errorMessage = errorMessage;
        this.positive = positive;
    }

    @Override
    public String getName() {
        return errorMessage;
    }

    @Override
    public boolean isPositive() {
        return positive;
    }

    @Override
    public ErrorLiteral flip() {
        return new ErrorLiteral(errorMessage, !positive);
    }

    @Override
    public ErrorLiteral cloneNode() {
        return new ErrorLiteral(errorMessage, positive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positive);
    }

    @Override
    public boolean equalsNode(Object other) {
        if (getClass() != other.getClass()) {
            return false;
        }
        final ErrorLiteral otherLiteral = (ErrorLiteral) other;
        return (positive == otherLiteral.positive);
    }

    @Override
    public String toString() {
        return (positive ? "+" : "-") + getName();
    }

    @Override
    public Object eval(List<?> values) {
        assert Formula.checkValues(0, values);
        return positive;
    }
}
