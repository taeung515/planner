package com.example.planner.repository;

import com.example.planner.dto.PlanResponseDto;
import com.example.planner.entity.Plan;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplatePlanRepository implements PlanRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplatePlanRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public PlanResponseDto createPlan(Plan plan) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("plan")
                .usingColumns("name", "password", "todo")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("name", plan.getName());
        parameters.put("password", plan.getPassword());
        parameters.put("todo", plan.getTodo());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        List<PlanResponseDto> dto = jdbcTemplate.query("SELECT * FROM plan WHERE id = ? ", planRowMapper(), key);

        return dto.get(0);
    }

    @Override
    public List<PlanResponseDto> findAllPlans() {
        return jdbcTemplate.query("SELECT * FROM plan ORDER BY updated_date DESC", planRowMapper());
    }

    @Override
    public List<PlanResponseDto> findPlanByDate(LocalDate updatedDate) {
        return jdbcTemplate.query("SELECT * FROM plan WHERE DATE(updated_date) = ? ORDER BY updated_date DESC", planRowMapper(), updatedDate);
    }

    @Override
    public List<PlanResponseDto> findPlanByName(String name) {
        return jdbcTemplate.query("SELECT * FROM plan WHERE name = ? ORDER BY updated_date DESC", planRowMapper(), name);
    }


    @Override
    public List<PlanResponseDto> findPlanByNameOrUpdatedDate(String name, LocalDate updatedDate) {
        return jdbcTemplate.query("SELECT * FROM plan WHERE name = ? AND updated_date = ? ORDER BY updated_date DESC", planRowMapper(), name, updatedDate);

    }

    @Override
    public Plan findPlanByID(Long id) {
        List<Plan> query = jdbcTemplate.query("SELECT * FROM plan WHERE id = ?", planRowMapperV2(), id);
        return query.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id = " + id));
    }

    @Override
    public int updatePlan(Plan plan) {
        return jdbcTemplate.update("UPDATE plan SET name = ?, todo = ? where id = ?", plan.getName(), plan.getTodo(), plan.getId());
    }

    @Override
    public int deletePlan(Long id) {
        return jdbcTemplate.update("DELETE FROM plan WHERE id = ?", id);
    }

    private RowMapper<Plan> planRowMapperV2() {
        return new RowMapper<Plan>() {
            @Override
            public Plan mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Plan(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("todo"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date")
                );
            }
        };
    }


    private RowMapper<PlanResponseDto> planRowMapper() {
        return new RowMapper<>() {
            @Override
            public PlanResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new PlanResponseDto(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("todo"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date")
                );
            }
        };
    }
}