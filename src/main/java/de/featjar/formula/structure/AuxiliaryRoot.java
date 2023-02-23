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
package de.featjar.formula.structure;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A propositional node that can be transformed into conjunctive normal form
 * (cnf).
 *
 * @author Sebastian Krieter
 */
public class AuxiliaryRoot extends NonTerminal {

    public AuxiliaryRoot(Formula node) {
        super(node);
    }

    private AuxiliaryRoot() {
        super();
    }

    @Override
    public String getName() {
        return "";
    }

    public Formula getChild() {
        return getChildren().iterator().next();
    }

    public void setChild(Formula node) {
        Objects.requireNonNull(node);
        setChildren(Arrays.asList(node));
    }

    @Override
    public AuxiliaryRoot cloneNode() {
        return new AuxiliaryRoot();
    }

    @Override
    public Class<?> getType() {
        return getChild().getType();
    }

    @Override
    public Object eval(List<?> values) {
        return getChild().eval(values);
    }
}
