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
package de.featjar.formula.transformer;

import de.featjar.base.data.Result;
import de.featjar.formula.structure.Expression;
import de.featjar.formula.structure.formula.Formula;
import de.featjar.formula.structure.formula.connective.Or;
import de.featjar.base.task.Monitor;
import de.featjar.base.tree.Trees;
import de.featjar.formula.visitor.NormalFormTester;
import de.featjar.formula.visitor.NormalForms;

/**
 * Transforms a formula into disjunctive normal form.
 *
 * @author Sebastian Krieter
 */
public class DNFTransformer implements FormulaTransformer {

    private final DistributiveTransformer distributiveTransformer;

    public DNFTransformer() {
        distributiveTransformer = new DistributiveTransformer(Formula.NormalForm.DNF);
    }

    public void setMaximumNumberOfLiterals(int maximumNumberOfLiterals) {
        distributiveTransformer.setMaximumNumberOfLiterals(maximumNumberOfLiterals);
    }

    @Override
    public Result<Formula> execute(Formula formula, Monitor monitor) {
        final NormalFormTester normalFormTester = NormalForms.getNormalFormTester(formula, Formula.NormalForm.DNF);
        if (normalFormTester.isNormalForm()) {
            if (!normalFormTester.isClausalNormalForm()) {
                return NormalForms.toNormalForm((Formula) Trees.clone(formula), Formula.NormalForm.DNF, true);
            } else {
                return Result.of((Formula) Trees.clone(formula));
            }
        } else {
            formula = new NNFTransformer().apply((Formula) Trees.clone(formula)).get(); // todo preferred computation
            return distributiveTransformer.execute((formula instanceof Or) ? formula : new Or(formula), monitor)
                    .map(f -> NormalForms.toClausalNormalForm(f, Formula.NormalForm.DNF));
        }
    }
}