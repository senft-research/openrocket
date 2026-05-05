package info.openrocket.core.logging.warning.types.aoa;

import info.openrocket.core.logging.Warning;
import info.openrocket.core.logging.warning.factories.warnings.AbstractWarningFactory;

public class LargeAOAWarningFactory extends AbstractWarningFactory<LargeAOAWarningContext> {
    @Override
    public Warning initWarning(LargeAOAWarningContext context) {
        return new Warning.LargeAOA(context.getAngleOfAttackRads());
    }
}
