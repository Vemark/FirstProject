package com.copart.repository;

import com.copart.entity.AppUsers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUsersRepository extends PagingAndSortingRepository<AppUsers, Long> {
  @Query("SELECT u FROM AppUsers u WHERE u.loginDomain = :domain AND u.username = :username")
  AppUsers findUser(@Param("username") String username, @Param("domain") String domain);
}
