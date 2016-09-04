package uk.co.jemos.podam.typeManufacturers;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.PodamBooleanValue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Default boolean type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class BooleanTypeManufacturerImpl extends AbstractTypeManufacturer<Boolean> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean getType(DataProviderStrategy strategy,
            AttributeMetadata attributeMetadata,
            Map<String, Type> genericTypesArgumentsMap) {

        Boolean retValue = null;

        for (Annotation annotation : attributeMetadata.getAttributeAnnotations()) {

            if (PodamBooleanValue.class.isAssignableFrom(annotation.getClass())) {
                PodamBooleanValue localStrategy = (PodamBooleanValue) annotation;
                retValue = localStrategy.boolValue();

                break;
            }
        }

        if (retValue == null) {
            retValue = strategy.getBoolean(attributeMetadata);
        }

        return retValue;
    }

}