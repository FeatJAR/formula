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
package de.featjar.formula.structure.transform;

import de.featjar.formula.structure.Formula;
import de.featjar.formula.structure.compound.And;
import de.featjar.formula.structure.compound.Compound;
import de.featjar.formula.structure.compound.Or;
import de.featjar.util.job.InternalMonitor;

/**
 * Transforms propositional formulas into CNF.
 *
 * @author Sebastian Krieter
 */
public class CNFDistributiveLawTransformer extends DistributiveLawTransformer {

    public CNFDistributiveLawTransformer() {
        super(Or.class, Or::new);
    }

    @Override
    public Compound execute(Formula formula, InternalMonitor monitor) throws MaximumNumberOfLiteralsExceededException {
        final Compound compound = (formula instanceof And) ? (And) formula : new And(formula);
        return super.execute(compound, monitor);
    }
}
