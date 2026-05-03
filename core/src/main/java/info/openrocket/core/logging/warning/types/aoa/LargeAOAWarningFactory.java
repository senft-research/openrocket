package info.openrocket.core.logging.warning.types.aoa;

import info.openrocket.core.logging.Warning;
import info.openrocket.core.logging.warning.factories.WarningFactory;
import info.openrocket.core.logging.warning.exceptions.NoWarningFactoryRegisteredException;

public class LargeAOAWarningFactory implements WarningFactory<LargeAOAWarningContext> {
    @Override
    public Warning createWarning(LargeAOAWarningContext context) throws NoWarningFactoryRegisteredException {
        Warning warning = new Warning.LargeAOA(context.getAngleOfAttackRads());
        warning.setPriority(context.getPriority());
        warning.setID(context.getWarningId());
        return new Warning.LargeAOA(context.getAngleOfAttackRads());
    }
}
