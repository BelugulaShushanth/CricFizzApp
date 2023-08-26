package com.cricFizzApp.bean.alert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

@Table("alerts")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class AlertDetails {

    @Id
    @PrimaryKeyColumn(name = "alertId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @Column("alertId")
    private String alertId;

    @CassandraType(type = CassandraType.Name.TEXT)
    @Column("mailId")
    private String mailId;

    @CassandraType(type = CassandraType.Name.TEXT)
    @Column("matchType")
    private String matchType;

    @CassandraType(type = CassandraType.Name.INT)
    @Column("seriesId")
    private Long seriesId;

    @CassandraType(type = CassandraType.Name.TEXT)
    @Column("seriesName")
    private String seriesName;

    @CassandraType(type = CassandraType.Name.INT)
    @Column("matchId")
    private Long matchId;

    @CassandraType(type = CassandraType.Name.TEXT)
    @Column("matchName")
    private String matchName;

    @CassandraType(type = CassandraType.Name.TEXT)
    @Column("alertType")
    private String alertType;

    @CassandraType(type = CassandraType.Name.INT)
    @Column("timePeriod")
    private Integer timePeriod;

    @CassandraType(type = CassandraType.Name.BOOLEAN)
    @Column("isActive")
    private Boolean isActive;

    @CassandraType(type = CassandraType.Name.TIMESTAMP)
    @Column("lastSentAlertDT")
    @JsonIgnore
    private LocalDateTime lastSentAlertDT;

    @CassandraType(type = CassandraType.Name.BIGINT)
    @Column("matchStartDT")
    @Transient
    private Long matchStartDT;
}
