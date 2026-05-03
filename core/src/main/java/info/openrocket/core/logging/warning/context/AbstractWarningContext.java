package info.openrocket.core.logging.warning.context;

import info.openrocket.core.logging.MessagePriority;
import info.openrocket.core.logging.warning.WarningType;
import info.openrocket.core.logging.warning.exceptions.IncorrectWarningContextParamsException;

import java.util.UUID;

public abstract class AbstractWarningContext implements WarningContext {
    protected UUID warningId;
    protected WarningType warningType;
    protected MessagePriority priority;
    @Override
    public UUID getWarningId() {
        return warningId;
    }

    @Override
    public WarningType getWarningType() {
        return warningType;
    }

    @Override
    public MessagePriority getPriority() {
        return priority;
    }

    public static abstract class AbstractWarningTypeBuilder<T extends AbstractWarningTypeBuilder<T>>{
        protected UUID warningId;
        protected WarningType warningType;
        protected MessagePriority priority;
        protected abstract T getSelf();
        protected abstract AbstractWarningContext create();
        protected abstract boolean requiredParamsInitialized();

        public WarningContext build(){
            AbstractWarningContext warningContext = create();
            warningContext.warningId = warningId;
            warningContext.warningType = warningType;
            warningContext.priority = priority;
            if(!requiredParamsInitialized()){
                //TODO this exception is not the cleanest in the world... might need more context (hehe... context)
                throw new IncorrectWarningContextParamsException("Required parameters not initialized");
            }
            return warningContext;
        }

        public AbstractWarningTypeBuilder<T> withWarningId(UUID warningId) {
            this.warningId = warningId;
            return getSelf();
        }

        public AbstractWarningTypeBuilder<T> withWarningType(WarningType warningType) {
            this.warningType = warningType;
            return getSelf();
        }

        public AbstractWarningTypeBuilder<T> withPriority(MessagePriority priority) {
            this.priority = priority;
            return getSelf();
        }



    }
}
