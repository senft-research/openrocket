package info.openrocket.core.logging.warning.types.aoa;

import info.openrocket.core.logging.warning.context.AbstractWarningContext;

public class LargeAOAWarningContext extends AbstractWarningContext {
    private double angleOfAttackRads;
    public double getAngleOfAttackRads(){
        return this.angleOfAttackRads;
    }

    public static class LargeAOAWarningContextBuilder extends AbstractWarningTypeBuilder<LargeAOAWarningContextBuilder>{

        private Double angleOfAttackRads;
        @Override
        protected LargeAOAWarningContextBuilder getSelf() {
            return this;
        }

        @Override
        protected AbstractWarningContext create() {
            LargeAOAWarningContext warningContext = new LargeAOAWarningContext();
            warningContext.angleOfAttackRads = this.angleOfAttackRads;
            return warningContext;
        }

        @Override
        protected boolean requiredParamsInitialized() {
            return !this.angleOfAttackRads.isNaN();
        }
    }
}
