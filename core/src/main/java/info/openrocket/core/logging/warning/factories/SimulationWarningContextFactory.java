package info.openrocket.core.logging.warning.factories;

import info.openrocket.core.logging.warning.WarningType;
import info.openrocket.core.logging.warning.context.WarningContext;
import info.openrocket.core.logging.warning.exceptions.NoContextFactoryRegisteredException;

import java.util.HashMap;
import java.util.Map;

/**
 * Primary {@linkplain WarningContextFactory Warning Context Factory} responsible for the dynamic creation of Warning
 * Context instances for Simulation-Warnings stored in the flight data of .ork files.
 */
public class SimulationWarningContextFactory implements WarningContextFactory {

    //TODO It might be worth making the map store an array of factories (for the purposes of having legacy file formats
    //     able to be constructed via the same warning type? Though this might be over-engineering the solution...)

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
    public WarningContext create(WarningType warningType) {
        if(!factories.containsKey(warningType)){
            throw new NoContextFactoryRegisteredException("The Warning Type " + warningType.name()
                    + "has no registered context factory.");
        }
        //TODO this is about as far as I can get without sorting out the XML logic side
        WarningContextFactory contextFactory = this.factories.get(warningType);


        return null;
    }
}
