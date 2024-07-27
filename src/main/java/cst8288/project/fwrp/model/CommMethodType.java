package cst8288.project.fwrp.model;

import java.util.Optional;

public enum CommMethodType {
    Email(1), Phone(2), Both(3);
    final int code;

    CommMethodType(int code) {
        this.code = code;
    }

    public static Optional<CommMethodType> getByCode(int code) {
        for (CommMethodType commMethodType : CommMethodType.values()) {
            if (commMethodType.code == code) {
                return Optional.of(commMethodType);
            }
        }
        return Optional.empty();
    }
}
