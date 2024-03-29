package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.RequestOutputDto;
import ru.practicum.model.Request;

import java.time.LocalDateTime;
import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer> {

    @Query(value = "SELECT new ru.practicum.RequestOutputDto(a.name, r.uri, COUNT(r.ip)) " +
            "FROM Request as r " +
            "LEFT JOIN App as a ON a.id = r.app.id " +
            "WHERE r.timestamp between :startDate AND :endDate " +
            "AND r.uri IN (:uris) " +
            "GROUP BY a.name, r.uri " +
            "ORDER BY COUNT(r.ip) DESC ")
    List<RequestOutputDto> getAllRequestsWithUri(@Param("startDate") LocalDateTime start,
                                                 @Param("endDate") LocalDateTime end,
                                                 @Param("uris") List<String> uris);

    @Query(value = "SELECT new ru.practicum.RequestOutputDto(a.name, r.uri, COUNT(DISTINCT r.ip)) " +
            "FROM Request as r " +
            "LEFT JOIN App as a ON a.id = r.app.id " +
            "WHERE r.timestamp between :startDate AND :endDate " +
            "AND r.uri IN (:uris) " +
            "GROUP BY a.name, r.uri " +
            "ORDER BY COUNT(DISTINCT r.ip) DESC ")
    List<RequestOutputDto> getUniqueIpRequestsWithUri(@Param("startDate") LocalDateTime start,
                                                      @Param("endDate") LocalDateTime end,
                                                      @Param("uris") List<String> uris);

    @Query(value = "SELECT new ru.practicum.RequestOutputDto(a.name, r.uri, COUNT(DISTINCT r.ip)) " +
            "FROM Request as r " +
            "LEFT JOIN App as a ON a.id = r.app.id " +
            "WHERE r.timestamp between :startDate AND :endDate " +
            "GROUP BY a.name, r.uri " +
            "ORDER BY COUNT(DISTINCT r.ip) DESC ")
    List<RequestOutputDto> getUniqueIpRequestsWithoutUri(@Param("startDate") LocalDateTime start,
                                                         @Param("endDate") LocalDateTime end);

    @Query(value = "SELECT new ru.practicum.RequestOutputDto(a.name, r.uri, COUNT(r.ip)) " +
            "FROM Request as r " +
            "LEFT JOIN App as a ON a.id = r.app.id " +
            "WHERE r.timestamp between :startDate AND :endDate " +
            "GROUP BY a.name, r.uri " +
            "ORDER BY COUNT(r.ip) DESC ")
    List<RequestOutputDto> getAllRequestsWithoutUri(@Param("startDate") LocalDateTime start,
                                                    @Param("endDate") LocalDateTime end);

    @Query(value = "SELECT new ru.practicum.RequestOutputDto(a.name, r.uri, COUNT(r.ip)) " +
            "FROM Request as r " +
            "LEFT JOIN App as a ON a.id = r.app.id " +
            "WHERE r.timestamp between :startDate AND :endDate " +
            "AND r.uri IN (:uris) " +
            "AND r.ip = :ip " +
            "GROUP BY a.name, r.uri " +
            "ORDER BY COUNT(r.ip) DESC ")
    List<RequestOutputDto> getAllRequestsWithUriByIp(@Param("startDate") LocalDateTime start,
                                                     @Param("endDate") LocalDateTime end,
                                                     @Param("uris") List<String> uris,
                                                     @Param("ip") String ip);

    @Query(value = "SELECT new ru.practicum.RequestOutputDto(a.name, r.uri, COUNT(DISTINCT r.ip)) " +
            "FROM Request as r " +
            "LEFT JOIN App as a ON a.id = r.app.id " +
            "WHERE r.timestamp between :startDate AND :endDate " +
            "AND r.uri IN (:uris) " +
            "AND r.ip = :ip " +
            "GROUP BY a.name, r.uri " +
            "ORDER BY COUNT(DISTINCT r.ip) DESC ")
    List<RequestOutputDto> getUniqueIpRequestsWithUriByIp(@Param("startDate") LocalDateTime start,
                                                          @Param("endDate") LocalDateTime end,
                                                          @Param("uris") List<String> uris,
                                                          @Param("ip") String ip);

    @Query(value = "SELECT new ru.practicum.RequestOutputDto(a.name, r.uri, COUNT(DISTINCT r.ip)) " +
            "FROM Request as r " +
            "LEFT JOIN App as a ON a.id = r.app.id " +
            "WHERE r.timestamp between :startDate AND :endDate " +
            "AND r.ip = :ip " +
            "GROUP BY a.name, r.uri " +
            "ORDER BY COUNT(DISTINCT r.ip) DESC ")
    List<RequestOutputDto> getUniqueIpRequestsWithoutUriByIp(@Param("startDate") LocalDateTime start,
                                                             @Param("endDate") LocalDateTime end,
                                                             @Param("ip") String ip);

    @Query(value = "SELECT new ru.practicum.RequestOutputDto(a.name, r.uri, COUNT(r.ip)) " +
            "FROM Request as r " +
            "LEFT JOIN App as a ON a.id = r.app.id " +
            "WHERE r.timestamp between :startDate AND :endDate " +
            "AND r.ip = :ip " +
            "GROUP BY a.name, r.uri " +
            "ORDER BY COUNT(r.ip) DESC ")
    List<RequestOutputDto> getAllRequestsWithoutUriByIp(@Param("startDate") LocalDateTime start,
                                                        @Param("endDate") LocalDateTime end,
                                                        @Param("ip") String ip);
}