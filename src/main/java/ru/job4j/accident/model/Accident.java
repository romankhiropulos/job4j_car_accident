package ru.job4j.accident.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Component
@Entity
@Table(name = "accident")
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(
                name = "Accident_GetAll",
                query = "select distinct ac from Accident ac "
                        + " join fetch ac.type "
                        + " join fetch ac.rules "
        ),
        @org.hibernate.annotations.NamedQuery(
                name = "Accident_FindById",
                query = "select distinct ac from Accident ac"
                        + " join fetch ac.type "
                        + " join fetch ac.rules "
                        + " where ac.id = :accidentId"
        )
})
public class Accident {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "accident_id_seq")
    @GenericGenerator(name = "accident_id_seq", strategy = "increment")
    private int id;

    private String name;

    private String text;

    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", foreignKey = @ForeignKey(name = "ACCIDENT_TYPE_ID_FKEY"))
    private AccidentType type;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "accident_rules",
            joinColumns = {@JoinColumn(name = "accident_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "rule_id", nullable = false, updatable = false)}
    )
    private Set<Rule> rules;

    public Accident() {
    }

    public Accident(int id, String name, String text, String address) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.address = address;
    }

    public Accident(int id, String name, String text, String address, AccidentType type, Set<Rule> rules) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.address = address;
        this.type = type;
        this.rules = rules;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AccidentType getType() {
        return type;
    }

    public void setType(AccidentType type) {
        this.type = type;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Accident)) {
            return false;
        }
        Accident accident = (Accident) o;
        return this.getId() == accident.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
