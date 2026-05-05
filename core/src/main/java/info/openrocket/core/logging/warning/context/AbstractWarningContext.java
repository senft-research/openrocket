package info.openrocket.core.logging.warning.context;

import info.openrocket.core.logging.MessagePriority;
import info.openrocket.core.logging.warning.WarningType;
import info.openrocket.core.logging.warning.exceptions.RequiredWarningContextParamsMissingException;
import info.openrocket.core.logging.warning.factories.contexts.SimulationWarningContextFactory;

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

    /**
     * <p> Static abstract class representing the general functionality of a constructor for {@linkplain WarningContext
     * Warning Context} instances, via the builder-pattern.</p>
     * <p> The Primary reason for this builder is to allow for its children to introduce unique parameters to the Warning
     * Context construction, without having to alter the core logic that constructs Warnings from data retrieved from
     * .ork files.
     * </p>
     *
     * <p> For example: If one wanted to add a unique parameter to a warning, an implementation of this builder
     * could implement a method for the parameter to be included in its construction, with no changes to the
     * {@linkplain SimulationWarningContextFactory
     * Primary Context Factory} being required.
     * </p>
     * @param <T> Type parameter of the builder. This is a generics trick allows for a method that can return the impl
     *           of the builder, allowing for the impl-specific parameter methods to work as intended.
     */
    public static abstract class AbstractWarningContextBuilder<T extends AbstractWarningContextBuilder<T>>{
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
                throw new RequiredWarningContextParamsMissingException("Required parameters not initialized");
            }
            return warningContext;
        }

        public AbstractWarningContextBuilder<T> withWarningId(UUID warningId) {
            this.warningId = warningId;
            return getSelf();
        }

        public AbstractWarningContextBuilder<T> withWarningType(WarningType warningType) {
            this.warningType = warningType;
            return getSelf();
        }

        public AbstractWarningContextBuilder<T> withPriority(MessagePriority priority) {
            this.priority = priority;
            return getSelf();
        }



    }
}
