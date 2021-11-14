package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

/**
 *  This class can be @Repository instead of Accident Hibernate
 */
public class AccidentJdbcTemplate implements Storage {

    private final JdbcTemplate jdbc;

    private final RowMapper<Accident> accidentRowMapper = (rs, row) -> {
        Accident accident = new Accident();
        accident.setId(rs.getInt("accident_id"));
        accident.setName(rs.getString("accident_name"));
        accident.setText(rs.getString("accident_text"));
        accident.setAddress(rs.getString("accident_address"));
        AccidentType accidentType = new AccidentType();
        accidentType.setId(rs.getInt("accident_type_id"));
        accidentType.setName(rs.getString("accident_type_name"));
        accident.setType(accidentType);
        Set<Rule> rules = new HashSet<>(getRulesForAccident(accident.getId()));
        accident.setRules(rules);
        return accident;
    };

    private final RowMapper<AccidentType> accidentTypeRowMapper = (rs, row) -> {
        AccidentType accidentType = new AccidentType();
        accidentType.setId(rs.getInt("id"));
        accidentType.setName(rs.getString("name"));
        return accidentType;
    };

    private final RowMapper<Rule> ruleRowMapper = (rs, row) -> {
        Rule rule = new Rule();
        rule.setId(rs.getInt("rule_id"));
        rule.setName(rs.getString("name"));
        return rule;
    };

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Collection<Accident> getAllAccidents() {
        return jdbc.query(
                "SELECT a.id as accident_id, "
                          + " a.name as accident_name,"
                          + " a.text as accident_text,"
                          + " a.address as accident_address,"
                          + " a.type_id as accident_type_id,"
                          + " at.name as accident_type_name"
                          + " FROM accident a LEFT JOIN accident_type at ON a.type_id = at.id",
                accidentRowMapper
        );
    }

    @Override
    public Accident getAccidentById(int id) {
        return jdbc.queryForObject(
                "SELECT a.id as accident_id, "
                        + " a.name as accident_name,"
                        + " a.text as accident_text,"
                        + " a.address as accident_address,"
                        + " a.type_id as accident_type_id,"
                        + " at.name as accident_type_name"
                        + " FROM accident a LEFT JOIN accident_type at ON a.type_id = at.id"
                        + " WHERE a.id = ?",
                accidentRowMapper,
                id
        );
    }

    @Override
    public Collection<AccidentType> getAllAccidentTypes() {
        return jdbc.query("SELECT * FROM accident_type", accidentTypeRowMapper);
    }

    @Override
    public Collection<Rule> getAllRules() {
        return jdbc.query(
                "SELECT * FROM rules",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                }
        );
    }

    @Override
    public Accident create(Accident accident) {
        return accident.getId() == 0 ? save(accident) : update(accident);
    }

    private Accident save(Accident accident) {
        final String INSERT_ACCIDENT_SQL = "INSERT INTO accident (name, text, address, type_id) VALUES (?, ?, ?, ?)";
        final String INSERT_RULE_SQL = "INSERT INTO accident_rules (accident_id, rule_id) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_ACCIDENT_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.setId((int) Objects.requireNonNull(keyHolder.getKeys()).get("id"));
        Objects.requireNonNull(accident.getRules()).forEach(rule -> jdbc.update(
                INSERT_RULE_SQL,
                accident.getId(),
                rule.getId()
        ));
        return accident;
    }

    private Accident update(Accident accident) {
        jdbc.update("UPDATE accident SET name = ?, text = ?, address = ?,  "
                        + "type_id = ? WHERE id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        jdbc.update(
                "DELETE FROM accident_rules WHERE accident_id = ?",
                accident.getId());
        accident.getRules().forEach(rule -> jdbc.update(
                "INSERT INTO accident_rules (accident_id, rule_id) VALUES (?, ?)",
                accident.getId(),
                rule.getId()
        ));
        return accident;
    }

    @Override
    public AccidentType getAccidentTypeById(int id) {
        return jdbc.queryForObject(
                "SELECT * FROM accident_type ar "
                        + " WHERE ar.id = ?",
                accidentTypeRowMapper,
                id
        );
    }

    private List<Rule> getRulesForAccident(int accidentId) {
        return jdbc.query(
                "SELECT * FROM rules r JOIN accident_rules ar ON r.id = ar.rule_id"
                        + " WHERE ar.accident_id = ?",
                ruleRowMapper,
                accidentId
        );
    }
}