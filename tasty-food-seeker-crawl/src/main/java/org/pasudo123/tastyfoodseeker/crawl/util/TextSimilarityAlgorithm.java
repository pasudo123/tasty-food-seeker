package org.pasudo123.tastyfoodseeker.crawl.util;

import info.debatty.java.stringsimilarity.JaroWinkler;

public class TextSimilarityAlgorithm {

    private TextSimilarityAlgorithm() {}

    public static Double getSimByJaroWinkler(final String x, final String y) {
        final JaroWinkler jaroWinkler = new JaroWinkler();
        return jaroWinkler.similarity(x, y);
    }
}
