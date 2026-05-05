package info.openrocket.core.logging.warning.factories.warnings;

import info.openrocket.core.logging.Warning;
import info.openrocket.core.logging.warning.WarningType;
import info.openrocket.core.logging.warning.context.WarningContext;
import info.openrocket.core.logging.warning.exceptions.InvalidWarningCreationException;
import info.openrocket.core.logging.warning.exceptions.NoWarningFactoryRegisteredException;
import info.openrocket.core.logging.warning.types.aoa.LargeAOAWarningFactory;

import java.util.HashMap;
import java.util.Map;

public class SimulationWarningFactory implements WarningFactory {

    private static SimulationWarningFactory instance;
    public static synchronized WarningFactory getInstance() {
        if (instance == null) {
            instance = new SimulationWarningFactory();
            instance.registerFactory(WarningType.LARGE_AOA, new LargeAOAWarningFactory());
        }
        return instance;
    }
    private Map<WarningType, WarningFactory> factories = new HashMap<>();


    public void registerFactory(WarningType type, WarningFactory factory) {
        factories.put(type, factory);
    }

    @Override
    public Warning createWarning(WarningContext context) throws InvalidWarningCreationException {
        if (!factories.containsKey(context.getWarningType())) {
            throw new NoWarningFactoryRegisteredException(context);
        }
        return factories.get(context.getWarningType()).createWarning(context);
    }
}
