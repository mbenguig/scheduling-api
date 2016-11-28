/*
 *  *
 * ProActive Parallel Suite(TM): The Java(TM) library for
 *    Parallel, Distributed, Multi-Core Computing for
 *    Enterprise Grids & Clouds
 *
 * Copyright (C) 1997-2015 INRIA/University of
 *                 Nice-Sophia Antipolis/ActiveEon
 * Contact: proactive@ow2.org or contact@activeeon.com
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; version 3 of
 * the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307
 * USA
 *
 * If needed, contact us to obtain a release under GPL Version 2 or 3
 * or a different license than the AGPL.
 *
 *  Initial developer(s):               The ProActive Team
 *                        http://proactive.inria.fr/team_members.htm
 *  Contributor(s):
 *
 *  * $$ACTIVEEON_INITIAL_DEV$$
 */
package org.ow2.proactive.scheduling.api.fetchers.converter;

import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.ow2.proactive.scheduler.core.db.JobData;
import org.ow2.proactive.scheduling.api.fetchers.cursor.JobCursorMapper;
import org.ow2.proactive.scheduling.api.schema.type.inputs.JobTaskCommonAbstractInput;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.function.BiFunction;

@AllArgsConstructor
public class JobTaskFilterInputBiFunction<T, I extends JobTaskCommonAbstractInput> implements BiFunction<CriteriaBuilder, Root<T>, List<Predicate[]>> {

    protected DataFetchingEnvironment environment;

    private JobTaskInputPredicatesConverter<T, I> converter;

    @Override
    public List<Predicate[]> apply(CriteriaBuilder criteriaBuilder, Root<T> root) {

        List<I> input = converter.mapToInput(environment);

        return converter.inputToPredicates(environment, criteriaBuilder, root, input);

    }


}