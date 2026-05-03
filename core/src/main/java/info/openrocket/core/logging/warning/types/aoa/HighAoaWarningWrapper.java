package info.openrocket.core.logging.warning.types.aoa;

import info.openrocket.core.logging.MessagePriority;
import info.openrocket.core.logging.Warning;
import info.openrocket.core.logging.warning.WarningType;
import info.openrocket.core.logging.warning.WarningWrapper;
import info.openrocket.core.unit.DegreeUnit;
import info.openrocket.core.unit.UnitGroup;

import java.util.UUID;

public class HighAoaWarningWrapper implements WarningWrapper {
    double angleOfAttack;
    WarningType warningType;
    UUID warningId;

    public HighAoaWarningWrapper(double angleOfAttack, UUID warningId) {
        this.warningType = WarningType.HIGH_ANGLE_OF_ATTACK;
        this.angleOfAttack = angleOfAttack;
        this.warningId = warningId;
    }
    @Override
    public String generateWarningString() {
        return "Angle is too high: " + UnitGroup.UNITS_ANGLE.toStringUnit(angleOfAttack);
    }

    @Override
    public Warning generateWarning() {
        return new Warning.LargeAOA(angleOfAttack);
    }

    @Override
    public UUID getWarningId() {
        return warningId;
    }

    @Override
    public WarningType getWarningType() {
        return warningType;
    }
}
