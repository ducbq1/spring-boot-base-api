package vn.vietinbank.evolve.domain;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuditLogs {
    String id;
    String name;
    String type;
    String date;
    String action;
}
