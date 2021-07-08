package com.copart.repository;

import com.copart.entity.PlLoginInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlLoginInfoRepository extends PagingAndSortingRepository<PlLoginInfo, Long> {
  @Query("SELECT u FROM PlLoginInfo u WHERE u.loginName = :username")
  PlLoginInfo findByUsername(@Param("username") String username);
}
