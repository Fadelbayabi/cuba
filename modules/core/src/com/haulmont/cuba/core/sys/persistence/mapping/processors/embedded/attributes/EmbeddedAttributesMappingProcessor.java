/*
 * Copyright (c) 2008-2020 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.haulmont.cuba.core.sys.persistence.mapping.processors.embedded.attributes;

import com.haulmont.cuba.core.entity.annotation.EmbeddedParameters;
import com.haulmont.cuba.core.sys.persistence.mapping.processors.MappingProcessor;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.mappings.AggregateObjectMapping;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component("cuba_EmbeddedAttributesMappingProcessor")
public class EmbeddedAttributesMappingProcessor implements MappingProcessor {

    @Override
    public void process(DatabaseMapping mapping) {

        if (mapping instanceof AggregateObjectMapping) {
            ClassDescriptor descriptor = mapping.getDescriptor();
            Field referenceField =
                    FieldUtils.getFieldsListWithAnnotation(descriptor.getJavaClass(), EmbeddedParameters.class)
                            .stream().filter(f -> f.getName().equals(mapping.getAttributeName())).findFirst().orElse(null);
            if (referenceField != null) {
                EmbeddedParameters embeddedParameters =
                        referenceField.getAnnotation(EmbeddedParameters.class);
                if (!embeddedParameters.nullAllowed())
                    ((AggregateObjectMapping) mapping).setIsNullAllowed(false);
            }
        }

    }
}
