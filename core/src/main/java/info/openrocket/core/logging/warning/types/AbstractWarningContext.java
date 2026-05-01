package info.openrocket.core.logging.warning.types;

import info.openrocket.core.logging.warning.WarningContext;
import info.openrocket.core.logging.warning.WarningType;

import java.util.UUID;

public abstract class AbstractWarningContext implements WarningContext {
    protected UUID warningId = UUID.randomUUID();
    protected WarningType warningType;
    @Override
    public WarningType getWarningType() {
        return this.warningType;
    }

    @Override
    public UUID getWarningId() {
        return this.warningId;
    }

    public static abstract class WarningContextBuilder<T extends WarningContextBuilder<T>>{
        protected abstract T getSelf();
        protected abstract AbstractWarningContext create();
        protected WarningType warningType;
        protected UUID warningId;

        public WarningContext build(){
            AbstractWarningContext context = create();
            context.warningId = warningId;
            context.warningType = warningType;
            return context;
        }

        public T setWarningType(WarningType warningType) {
            this.warningType = warningType;
            return getSelf();
        }
        public T setWarningId(UUID warningId) {
            this.warningId = warningId;
            return getSelf();
        }

    }
}
