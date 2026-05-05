package info.openrocket.core.logging.warning.factories.warnings;

import info.openrocket.core.logging.Warning;
import info.openrocket.core.logging.warning.context.WarningContext;
import info.openrocket.core.logging.warning.exceptions.InvalidWarningCreationException;
import info.openrocket.core.logging.warning.exceptions.NoWarningFactoryRegisteredException;

public abstract class AbstractWarningFactory<T extends WarningContext> implements WarningFactory {

    //TODO sort out the registration throw in the interface, the interface doesnt have register methods...
    @Override
    public Warning createWarning(WarningContext context) throws InvalidWarningCreationException {
        try{
            Warning warning = initWarning(castContext(context));
            warning.setID(context.getWarningId());
            warning.setPriority(context.getPriority());
            return warning;
        }
        catch(ClassCastException e){
            //TODO placeholder that needs sorting for something smoother
            return null;
        }
    }

    public abstract Warning initWarning(T context);

    //TODO gonna have to sort this out with some try-catch blocks at some point, or else face Joe's "Disappointed Face".
    public T castContext(WarningContext context) throws ClassCastException{
        return (T) context;
    }
}
