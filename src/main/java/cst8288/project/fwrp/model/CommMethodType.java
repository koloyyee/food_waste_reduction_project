package cst8288.project.fwrp.model;

public enum CommMethodType {
    Email(1), Phone(2), Both(3);
    final int code;

    CommMethodType(int code) {
        this.code = code;
    }
}
