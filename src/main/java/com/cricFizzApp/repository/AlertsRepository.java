package com.cricFizzApp.repository;

import com.cricFizzApp.bean.alert.AlertDetails;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertsRepository extends CassandraRepository<AlertDetails, String> {

    @AllowFiltering
    List<AlertDetails> findAllByMailId(String mailId);
}
