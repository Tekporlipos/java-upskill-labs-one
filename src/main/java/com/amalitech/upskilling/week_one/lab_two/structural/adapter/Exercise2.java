package com.amalitech.upskilling.week_one.lab_two.structural;

import java.util.List;

class CSVDataSource {
    public List<String[]> readCSV(String filePath) {
        return List.of(new String[]{"data1", "data2"}, new String[]{"data3", "data4"});
    }
}

interface DataSource {
    List<String[]> getData();
}

class CSVDataSourceAdapter implements DataSource {
    private final CSVDataSource csvDataSource;
    private final String filePath;

    public CSVDataSourceAdapter(CSVDataSource csvDataSource, String filePath) {
        this.csvDataSource = csvDataSource;
        this.filePath = filePath;
    }

    @Override
    public List<String[]> getData() {
        return csvDataSource.readCSV(filePath);
    }
}

class AdapterPatternDataSourceExample {
    public static void main(String[] args) {
        CSVDataSource csvDataSource = new CSVDataSource();
        DataSource dataSource = new CSVDataSourceAdapter(csvDataSource, "data.csv");

        List<String[]> data = dataSource.getData();
        data.forEach(row -> System.out.println(String.join(", ", row)));
    }
}
