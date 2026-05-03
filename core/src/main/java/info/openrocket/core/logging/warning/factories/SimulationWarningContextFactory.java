package info.openrocket.core.logging.warning.factories;

import info.openrocket.core.logging.warning.WarningType;
import info.openrocket.core.logging.warning.context.WarningContext;

import java.util.HashMap;
import java.util.Map;

public class SimulationWarningContextFactory implements WarningContextFactory {

    private final Map<WarningType, WarningContextFactory> factories = new HashMap<WarningType, WarningContextFactory>();


    public void registerFactory(WarningType warningType, WarningContextFactory contextFactory){
        this.factories.put(warningType, contextFactory);
    }

    //TODO refer to the todo inside WarningContextFactory.java
    @Override
    public WarningContext create(WarningType warningType) {
        return null;
    }
}
