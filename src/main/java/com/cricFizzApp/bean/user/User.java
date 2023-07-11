package com.cricFizzApp.bean.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "users")
@NoArgsConstructor
@Setter
@Getter
public class User {

    @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String userId;

    @CassandraType(type = CassandraType.Name.TEXT)
    private String username;

    @CassandraType(type = CassandraType.Name.TEXT)
    private String password;

}
