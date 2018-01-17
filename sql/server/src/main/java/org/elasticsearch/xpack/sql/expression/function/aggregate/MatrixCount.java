/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.xpack.sql.expression.function.aggregate;

import java.util.List;
import org.elasticsearch.xpack.sql.expression.Expression;
import org.elasticsearch.xpack.sql.tree.Location;
import org.elasticsearch.xpack.sql.tree.NodeInfo;

public class MatrixCount extends NumericAggregate implements MatrixStatsEnclosed {

    public MatrixCount(Location location, Expression field) {
        super(location, field);
    }

    @Override
    protected NodeInfo<MatrixCount> info() {
        return NodeInfo.create(this, MatrixCount::new, field());
    }

    @Override
    public MatrixCount replaceChildren(List<Expression> newChildren) {
        if (newChildren.size() != 1) {
            throw new IllegalArgumentException("expected [1] child but received [" + newChildren.size() + "]");
        }
        return new MatrixCount(location(), newChildren.get(0));
    }

    @Override
    public String innerName() {
        return "matrix_count";
    }
}
