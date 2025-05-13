package com.example.planner.repository;

import com.example.planner.dto.PlanResponseDto;
import com.example.planner.entity.Plan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
    public List<PlanResponseDto> findPlanByDate(Date updatedDate) {
        return jdbcTemplate.query("SELECT * FROM plan WHERE updated_date = ? ORDER BY updated_date DESC", planRowMapper(), updatedDate);
    }

    @Override
    public List<PlanResponseDto> findPlanByName(String name) {
        return jdbcTemplate.query("SELECT * FROM plan WHERE name = ? ORDER BY updated_date DESC", planRowMapper(), name);
    }


    @Override
    public List<PlanResponseDto> findPlanByNameOrUpdatedDate(String name, Date updatedDate) {
        return jdbcTemplate.query("SELECT * FROM plan WHERE name = ? AND updated_date = ? ORDER BY updated_date DESC", planRowMapper(), name, updatedDate);

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