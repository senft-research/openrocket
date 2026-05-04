package info.openrocket.core.logging.warning.types.aoa;

import info.openrocket.core.logging.Warning;
import info.openrocket.core.logging.warning.context.WarningContext;
import info.openrocket.core.logging.warning.factories.AbstractWarningFactory;

import info.openrocket.core.logging.warning.exceptions.NoWarningFactoryRegisteredException;

public class LargeAOAWarningFactory extends AbstractWarningFactory<LargeAOAWarningContext> {
    @Override
    public Warning initWarning(LargeAOAWarningContext context) {
        return new Warning.LargeAOA(context.getAngleOfAttackRads());
    }
}
