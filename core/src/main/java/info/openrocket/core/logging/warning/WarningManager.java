package info.openrocket.core.logging.warning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarningManager {

    private static WarningManager instance;

    public static WarningManager getInstance(){
        if(instance == null){
            instance = new WarningManager();
        }
        return instance;
    }

    private final Map<String, WarningFactory> warningWrappers = new HashMap<>();
    private final Map<String,WarningWrapper> warningMessages = new HashMap<>();

    private WarningManager(){}

    public void registerWrapper(WarningFactory wrapper){
        warningWrappers.put(wrapper.getWarningId(), wrapper);
    }

    public void addWarning(String warningId, Object[] params) throws IllegalArgumentException {
        if(!warningWrappers.containsKey(warningId)){
            throw new IllegalArgumentException("Warning with id of " + warningId + " not found!");
        }
        WarningFactory warning = warningWrappers.get(warningId);
        try{
           warningMessages.put(warningId,warning.generateWarning(params));
        }
        catch(IllegalArgumentException e){
            //TODO might need to make a custom exception, seems silly to throw a illegal in an a catch for an illegal
            throw new IllegalArgumentException(generateIllegalArgumentInfo(warning, params));
        }
    }

    public void clearWarning(String warningId){
        warningMessages.remove(warningId);
    }

    public void clearWarnings(){
        warningMessages.clear();
    }

    public List<String> getWarnings(){
        List<String> warnings = new ArrayList<>();
        warningMessages.values().forEach(wrapper -> warnings.add(wrapper.generateWarning()));
        return warnings;
    }

    public String generateIllegalArgumentInfo(WarningFactory warning, Object[] params){
        return null; //TODO Needs logic making
    }


}
