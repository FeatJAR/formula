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
package de.featjar.structure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.featjar.formula.structure.AuxiliaryRoot;
import de.featjar.formula.structure.Formula;
import de.featjar.formula.structure.atomic.literal.VariableMap;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuxiliaryRootTest {

    private Formula expression1, expression2;

    @BeforeEach
    public void setUp() {
        final VariableMap map = new VariableMap(Arrays.asList("L1", "L2"));
        expression1 = map.createLiteral("L1");
        expression2 = map.createLiteral("L2");
    }

    @Test
    public void createAuxiliaryRoot() {
        final AuxiliaryRoot newRoot = new AuxiliaryRoot(expression1);
        assertEquals(expression1, newRoot.getChild());
        assertEquals("", newRoot.getName());
    }

    @Test
    public void replaceChild() {
        final AuxiliaryRoot newRoot = new AuxiliaryRoot(expression1);
        newRoot.setChild(expression2);
        assertEquals(expression2, newRoot.getChild());
    }
}
