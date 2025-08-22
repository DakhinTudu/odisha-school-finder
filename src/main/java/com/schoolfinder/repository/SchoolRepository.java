package com.schoolfinder.repository;

import com.schoolfinder.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
	List<School> findByNameContainingIgnoreCase(String name);

	List<School> findByCityIgnoreCase(String city);

	List<School> findByBoardIgnoreCase(String board);

	List<School> findByTypeIgnoreCase(String type);

	@Query("SELECT s FROM School s WHERE " + "(:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND "
			+ "(:city IS NULL OR LOWER(s.city) = LOWER(:city)) AND "
			+ "(:board IS NULL OR LOWER(s.board) = LOWER(:board)) AND "
			+ "(:type IS NULL OR LOWER(s.type) = LOWER(:type))")
	List<School> searchSchools(@Param("name") String name, @Param("city") String city, @Param("board") String board,
			@Param("type") String type);

	@Query("SELECT DISTINCT s.city FROM School s ORDER BY s.city")
	List<String> findAllCities();

	@Query("SELECT DISTINCT s.board FROM School s ORDER BY s.board")
	List<String> findAllBoards();

	@Query("SELECT DISTINCT s.type FROM School s ORDER BY s.type")
	List<String> findAllTypes();
}
