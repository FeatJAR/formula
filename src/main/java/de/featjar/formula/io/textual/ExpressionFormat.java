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
package de.featjar.formula.io.textual;

import de.featjar.formula.structure.Expression;
import de.featjar.base.data.Result;
import de.featjar.base.io.InputMapper;
import de.featjar.base.io.format.Format;

import java.util.Optional;

public class ExpressionFormat implements Format<Expression> {

    public static final String ID = ExpressionFormat.class.getCanonicalName();

    @Override
    public Result<Expression> parse(InputMapper inputMapper) {
        return new NodeReader().read(inputMapper.get().read().get());
    }

    @Override
    public String serialize(Expression object) {
        return new NodeWriter().write(object);
    }

    @Override
    public boolean supportsParse() {
        return true;
    }

    @Override
    public boolean supportsSerialize() {
        return true;
    }

    @Override
    public String getIdentifier() {
        return ID;
    }

    @Override
    public Optional<String> getFileExtension() {
        return Optional.of("formula");
    }

    @Override
    public String getName() {
        return "Formula";
    }
}