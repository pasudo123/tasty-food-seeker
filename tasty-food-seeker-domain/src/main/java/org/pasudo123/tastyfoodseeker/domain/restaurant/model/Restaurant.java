package org.pasudo123.tastyfoodseeker.domain.restaurant.model;

import com.google.common.hash.Hashing;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;

@Entity
@Table(name = "restaurant", indexes = {
        @Index(name = "name_idx", columnList = "name"),
        @Index(name = "gu_idx", columnList = "gu"),
        @Index(name = "hash_idx", columnList = "sha256_hash")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {

    private static final String BRACKET = ">";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT", nullable = false)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(100)", nullable = false)
    private String name = Strings.EMPTY;

    @Column(name = "category", columnDefinition = "VARCHAR(100)", nullable = false)
    private String category = Strings.EMPTY;

    @Enumerated(EnumType.STRING)
    @Column(name = "gu", columnDefinition = "VARCHAR(50)", nullable = false)
    private Gu gu;

    @Column(name = "address", columnDefinition = "VARCHAR(100)", nullable = false)
    private String address = Strings.EMPTY;

    @Column(name = "road_address", columnDefinition = "VARCHAR(100)", nullable = false)
    private String roadAddress = Strings.EMPTY;

    @Column(name = "additional_info", columnDefinition = "TEXT", nullable = false)
    private String additionalInfo = Strings.EMPTY;

    @Column(name = "sha256_hash", columnDefinition = "VARCHAR(32)", nullable = false)
    private String sha256Hash;

    @Builder
    public Restaurant(final String name,
                      final String category,
                      final String address,
                      final String roadAddress,
                      final String additionalInfo) {
        this.name = name;
        this.category = category;
        this.gu = Gu.findGuByAddress(address);
        this.address = address;
        this.roadAddress = roadAddress;
        this.additionalInfo = additionalInfo;
        this.sha256Hash = getSha256Hash(name, address);
    }

    @SuppressWarnings("UnstableApiUsage")
    private String getSha256Hash(final String name, final String address) {
        final String concatString = name.concat(address);
        return Hashing.sha256().
                hashString(concatString, StandardCharsets.UTF_8)
                .toString()
                .substring(0, 32);
    }

    public String getCategory() {
        return category.substring(category.indexOf(BRACKET) + 1).strip();
    }
}
