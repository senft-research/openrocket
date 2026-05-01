package info.openrocket.core.logging.warning;

import info.openrocket.core.rocketcomponent.RocketComponent;

import java.util.*;

public class WarningManager {

    private static WarningManager instance;

    public static WarningManager getInstance() {
        if (instance == null) {
            instance = new WarningManager();
        }
        return instance;
    }

    private final Map<WarningType, WarningFactory<?>> warningFactories = new HashMap<>();
    private final Map<UUID, WarningWrapper> warningWrappers = new HashMap<>();

    private WarningManager() {
    }

    public void registerWrapper(WarningFactory<?> wrapper) {
        warningFactories.put(wrapper.getWarningType(), wrapper);
    }

    public void addWarning(WarningContext context) {
        WarningType warningType = context.getWarningType();
        if (!warningFactories.containsKey(warningType)) {
            throw new IllegalArgumentException("Warning with id of " + warningType + " not found!");
        }
        WarningFactory<?> factory = warningFactories.get(warningType);
        WarningWrapper wrapper = handleFactory(factory, context);
    }

    private <T extends WarningContext> WarningWrapper handleFactory(WarningFactory<T> factory, WarningContext context) {
        return factory.returnCorrectContext(context)
                .map(factory::generateWarning)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Warning context is not of correct type!"
                ));
    }

    public void addWarning(WarningType warningType, UUID warningId, RocketComponent... params) throws IllegalArgumentException {
        if (!warningFactories.containsKey(warningType)) {
            throw new IllegalArgumentException("Warning with id of " + warningType + " not found!");
        }
        WarningFactory factory = warningFactories.get(warningType);
        try {
            warningWrappers.put(warningId, factory.generateWarning(params));
        } catch (IllegalArgumentException e) {
            //TODO might need to make a custom exception, seems silly to throw a illegal in an a catch for an illegal
            throw new IllegalArgumentException(generateIllegalArgumentInfo(factory, params));
        }
    }

    public void clearWarning(UUID warningId) {
        warningWrappers.remove(warningId);
    }

    public void clearWarnings() {
        warningWrappers.clear();
    }

    public List<String> getWarnings() {
        List<String> warnings = new ArrayList<>();
        warningWrappers.values().forEach(wrapper -> warnings.add(wrapper.generateWarning()));
        return warnings;
    }

    public String generateIllegalArgumentInfo(WarningFactory warning, Object[] params) {
        return null; //TODO Needs logic making
    }


}
