package info.openrocket.core.logging.warning;

import java.util.HashMap;
import java.util.Map;

public class WarningHandler {

    private static WarningHandler instance;

    public static WarningHandler getInstance(){
        if(instance == null){
            instance = new WarningHandler();
        }
        return instance;
    }

    private final Map<String, WarningWrapper> warningWrappers = new HashMap<>();

    private WarningHandler(){}

    public void registerWrapper(WarningWrapper wrapper){
        warningWrappers.put(wrapper.getWarningId(), wrapper);
    }

    public String getWarning(String warningId, Object[] params) throws IllegalArgumentException {
        if(!warningWrappers.containsKey(warningId)){
            throw new IllegalArgumentException("Warning with id of " + warningId + " not found!");
        }
        WarningWrapper warning = warningWrappers.get(warningId);
        try{
           return warning.generateWarning(params);
        }
        catch(IllegalArgumentException e){
            throw new IllegalArgumentException(generateIllegalArgumentInfo(warning, params));
        }
    }

    public String generateIllegalArgumentInfo(WarningWrapper warning, Object[] params){
        return null; //TODO Needs logic making
    }


}
