package com.garits.report;

import org.hibernate.annotations.NamedNativeQuery;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
import java.io.Serializable;
import java.util.Map;

@SqlResultSetMapping(
        name = "StatReportResult",
        entities = {
                @EntityResult(
                        entityClass = com.garits.report.StatReport.class,
                        fields = {
                                @FieldResult(name = "count", column = "count")}
                )
        }
)
@NamedNativeQuery(
        name = "Stats",
        query = "SELECT count(*) as count FROM customers",
        resultSetMapping = "StatReportResult"
)
public class StatReport implements Serializable {
    private Long count;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }


}
