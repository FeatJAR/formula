/*
 * Copyright (C) 2024 FeatJAR-Development-Team
 *
 * This file is part of FeatJAR-formula.
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
package de.featjar.formula.transform.cli;

import de.featjar.base.FeatJAR;
import de.featjar.base.cli.ICommand;
import de.featjar.base.cli.Option;
import de.featjar.base.cli.OptionList;
import de.featjar.base.computation.Computations;
import de.featjar.base.data.Result;
import de.featjar.base.io.IO;
import de.featjar.base.io.format.IFormat;
import de.featjar.formula.io.FormulaFormats;
import de.featjar.formula.structure.formula.IFormula;
import de.featjar.formula.transform.ComputeCNFFormula;
import de.featjar.formula.transform.ComputeNNFFormula;

/**
 * Converts the format of a given formula into another CNF format.
 *
 * @author Andreas Gerasimow
 */
public class ConvertCNFFormatCommand extends AConvertFormatCommand {

    @Override
    protected IFormula modifyFormula(IFormula formula) {
        return Computations.of(formula)
                .map(ComputeNNFFormula::new)
                .map(ComputeCNFFormula::new)
                .compute();
    }

    @Override
    public String getDescription() {
        return "Converts the format of a given formula into another CNF format.";
    }

    @Override
    public String getShortName() {
        return "convert-cnf-format";
    }
}
