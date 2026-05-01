package info.openrocket.core.logging.warning.types.aoa;

import info.openrocket.core.logging.warning.WarningContext;
import info.openrocket.core.logging.warning.types.AbstractWarningContext;

public class HighAoaWarningContext extends AbstractWarningContext {
    double angleOfAttack;

    public static class HighAoaWarningContextBuilder
            extends WarningContextBuilder<HighAoaWarningContextBuilder> {
        private double angleOfAttack;


        @Override
        protected HighAoaWarningContextBuilder getSelf() {
            return this;
        }

        @Override
        protected AbstractWarningContext create() {
            HighAoaWarningContext context = new HighAoaWarningContext();
            context.angleOfAttack = this.angleOfAttack;
            return context;
        }
    }
}
