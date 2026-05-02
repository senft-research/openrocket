package info.openrocket.core.logging.warning.types.aoa;

import info.openrocket.core.logging.warning.types.AbstractWarningContext;

public class HighAoaWarningContext extends AbstractWarningContext {
    private double angleOfAttack;

    public double getAngleOfAttack(){
        return angleOfAttack;
    }

    public static class HighAoaWarningContextBuilder
            extends WarningContextBuilder<HighAoaWarningContextBuilder> {
        private double angleOfAttack;


        @Override
        protected HighAoaWarningContextBuilder getSelf() {
            return this;
        }

        public HighAoaWarningContextBuilder setAngleOfAttack(double angleOfAttack){
            this.angleOfAttack = angleOfAttack;
            return getSelf();
        }

        @Override
        protected AbstractWarningContext create() {
            HighAoaWarningContext context = new HighAoaWarningContext();
            context.angleOfAttack = this.angleOfAttack;
            return context;
        }
    }
}
