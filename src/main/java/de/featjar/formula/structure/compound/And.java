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
package de.featjar.formula.structure.compound;

import de.featjar.formula.structure.Formula;
import java.util.List;

/**
 * A logical connector that is {@code true} iff all of its children are
 * {@code true}.
 *
 * @author Sebastian Krieter
 */
public class And extends Compound {

    public And(List<? extends Formula> nodes) {
        super(nodes);
    }

    public And(Formula... nodes) {
        super(nodes);
    }

    private And() {
        super();
    }

    @Override
    public And cloneNode() {
        return new And();
    }

    @Override
    public String getName() {
        return "and";
    }

    @Override
    public Object eval(List<?> values) {
        if (values.stream().anyMatch(v -> v == Boolean.FALSE)) {
            return Boolean.FALSE;
        }
        return values.stream().filter(v -> v == Boolean.TRUE).count() == children.size() ? Boolean.TRUE : null;
    }
}
