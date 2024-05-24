package com.amalitech.upskilling.week_one.lab_two.creational.builder;

class SearchQuery {
    private final String keyword;
    private final String location;
    private final int minPrice;
    private final int maxPrice;

    private SearchQuery(SearchQueryBuilder builder) {
        this.keyword = builder.keyword;
        this.location = builder.location;
        this.minPrice = builder.minPrice;
        this.maxPrice = builder.maxPrice;
    }

    public static class SearchQueryBuilder {
        private final String keyword;
        private String location;
        private int minPrice;
        private int maxPrice;

        public SearchQueryBuilder(String keyword) {
            this.keyword = keyword;
        }

        public SearchQueryBuilder location(String location) {
            this.location = location;
            return this;
        }

        public SearchQueryBuilder minPrice(int minPrice) {
            this.minPrice = minPrice;
            return this;
        }

        public SearchQueryBuilder maxPrice(int maxPrice) {
            this.maxPrice = maxPrice;
            return this;
        }

        public SearchQuery build() {
            return new SearchQuery(this);
        }
    }

    @Override
    public String toString() {
        return "SearchQuery{" +
                "keyword='" + keyword + '\'' +
                ", location='" + location + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                '}';
    }
}

class BuilderPatternSearchQueryExample {
    public static void main(String[] args) {
        SearchQuery query = new SearchQuery.SearchQueryBuilder("laptop")
                .location("New York")
                .minPrice(500)
                .maxPrice(1500)
                .build();
        System.out.println(query);
    }
}

