package info.openrocket.core.logging.warning;

import info.openrocket.core.logging.Warning;
import info.openrocket.core.logging.warning.types.aoa.HighAoaWarningFactory;

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
        warningFactories.put(WarningType.HIGH_ANGLE_OF_ATTACK, new HighAoaWarningFactory());
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
        this.warningWrappers.put(wrapper.getWarningId(), wrapper);
    }

    private <T extends WarningContext> WarningWrapper handleFactory(WarningFactory<T> factory, WarningContext context) {
        return factory.returnCorrectContext(context)
                .map(factory::generateWarning)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Warning context is not of correct type!"
                ));
    }

    public WarningWrapper getWarning(UUID warningId) {
        //TODO rather than null we should throw exception
        return this.warningWrappers.getOrDefault(warningId,null);
    }
    public void clearWarning(UUID warningId) {
        warningWrappers.remove(warningId);
    }

    public void clearWarnings() {
        warningWrappers.clear();
    }

 /*   public List<String> getWarnings() {
        List<String> warnings = new ArrayList<>();
        warningWrappers.values().forEach(wrapper -> warnings.add(wrapper.generateWarningString()));
        return warnings;
    }*/

    public List<Warning> getWarnings(){
        List<Warning> warnings = new ArrayList<>();
        warningWrappers.forEach((uuid, warningWrapper) -> {
            warnings.add(warningWrapper.generateWarning());
        });
        return warnings;
    }

    public String generateIllegalArgumentInfo(WarningFactory warning, Object[] params) {
        return null; //TODO Needs logic making
    }


}
