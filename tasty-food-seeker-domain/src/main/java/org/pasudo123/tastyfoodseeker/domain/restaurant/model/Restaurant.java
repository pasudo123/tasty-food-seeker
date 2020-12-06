package org.pasudo123.tastyfoodseeker.domain.restaurant.model;

import lombok.Builder;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
@Getter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT", nullable = false)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(100)", nullable = false)
    private String name = Strings.EMPTY;

    @Column(name = "category", columnDefinition = "VARCHAR(100)", nullable = false)
    private String category = Strings.EMPTY;

    @Column(name = "address", columnDefinition = "VARCHAR(100)", nullable = false)
    private String address = Strings.EMPTY;

    @Column(name = "loadAddress", columnDefinition = "VARCHAR(100)", nullable = false)
    private String loadAddress = Strings.EMPTY;

    @Column(name = "additional_info", columnDefinition = "TEXT", nullable = false)
    private String additionalInfo = Strings.EMPTY;

    @Builder
    public Restaurant(final String name,
                      final String category,
                      final String address,
                      final String loadAddress,
                      final String additionalInfo) {
        this.name = name;
        this.category = category;
        this.address = address;
        this.loadAddress = loadAddress;
        this.additionalInfo = additionalInfo;
    }
}
