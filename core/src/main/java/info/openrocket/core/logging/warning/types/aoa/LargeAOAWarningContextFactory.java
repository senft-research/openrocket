package info.openrocket.core.logging.warning.types.aoa;

import info.openrocket.core.logging.warning.factories.AbstractWarningContextFactory;

import java.util.Map;

public class LargeAOAWarningContextFactory extends AbstractWarningContextFactory<LargeAOAWarningContext.LargeAOAWarningContextBuilder
        > {

    @Override
    protected LargeAOAWarningContext.LargeAOAWarningContextBuilder initContext(Map<String, String> elements) {
        LargeAOAWarningContext.LargeAOAWarningContextBuilder builder = new LargeAOAWarningContext.LargeAOAWarningContextBuilder();
        return builder.withAngleOfAttack(Double.parseDouble(elements.get("aoa")));
    }
}
