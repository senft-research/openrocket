package info.openrocket.core.logging.warning.factories.contexts;

import info.openrocket.core.logging.MessagePriority;
import info.openrocket.core.logging.warning.WarningType;
import info.openrocket.core.logging.warning.context.WarningContext;
import info.openrocket.core.logging.warning.exceptions.NoContextFactoryRegisteredException;
import info.openrocket.core.logging.warning.types.aoa.LargeAOAWarningContextFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Primary {@linkplain WarningContextFactory Warning Context Factory} responsible for the dynamic creation of Warning
 * Context instances for Simulation-Warnings stored in the flight data of .ork files.
 */
public class SimulationWarningContextFactory implements WarningContextFactory {
    private static SimulationWarningContextFactory instance;

    public static synchronized SimulationWarningContextFactory getInstance() {
        if (instance == null) {
            instance = new SimulationWarningContextFactory();
            instance.registerFactory(WarningType.LARGE_AOA, new LargeAOAWarningContextFactory());
        }
        return instance;
    }


    /**
     * Map that stores the various Warning Context Factories for each specified Warning Type Key. This way each set of
     * XML data can be signposted to the appropriate Context Factory.
     */
    private final Map<WarningType, WarningContextFactory> factories = new HashMap<>();

    /**
     * Registers a context factory to a specified warning type. The .ork warning data can then use the stored warning
     * type to find the appropriate Context Factory.
     * @param warningType The warning type to register this factory to.
     * @param contextFactory The factory to register.
     */
    public void registerFactory(WarningType warningType, WarningContextFactory contextFactory){
        this.factories.put(warningType, contextFactory);
    }

    //TODO refer to the todo inside WarningContextFactory.java
    @Override
    public WarningContext create(MessagePriority priority, UUID warningId, WarningType warningType, Map<String, String> elements) {
        if(!factories.containsKey(warningType)){
            throw new NoContextFactoryRegisteredException("The Warning Type " + warningType.name()
                    + "has no registered context factory.");
        }
        WarningContextFactory contextFactory = this.factories.get(warningType);
        return contextFactory.create(priority, warningId, warningType, elements);
    }
}
