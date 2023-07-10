package com.livecricketscores.repository;

import com.livecricketscores.bean.user.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CassandraRepository<User,String> {

}
