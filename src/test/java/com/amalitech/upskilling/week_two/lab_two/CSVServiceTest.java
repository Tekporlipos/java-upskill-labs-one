package com.amalitech.upskilling.week_two.lab_two;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CSVServiceTest {
    @Mock
    private CSVRepository csvRepository;

    @InjectMocks
    private CSVService csvService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        csvService = new CSVService(csvRepository);
    }

    @DataProvider(name = "data")
    public Object[][] data() {
    Map<String, String> record1 = new HashMap<>();
    record1.put("Name", "John");
    record1.put("Age", "30");

    Map<String, String> record2 = new HashMap<>();
    record2.put("Name", "Alice");
    record2.put("Age", "25");

    return new Object[][] { { record1 }, { record2 } };
}

    @Test(dataProvider = "data")
    public void testPrintAll_With_Data(Map<String, String> data) throws IOException {

        when(csvRepository.findAll()).thenReturn(new ArrayList<>(List.of(data)));

        // Calling the method to test
        csvService.printAll();

        // Verifying that findAll() method is called
        verify(csvRepository, times(1)).findAll();
    }

    @Test
    public void testPrintAll() throws IOException {
        // Mocking CSV records
        Map<String, String> record1 = new HashMap<>();
        record1.put("Name", "John");
        record1.put("Age", "30");

        Map<String, String> record2 = new HashMap<>();
        record2.put("Name", "Alice");
        record2.put("Age", "25");

        when(csvRepository.findAll()).thenReturn(new ArrayList<>(List.of(record1, record2)));

        // Calling the method to test
        csvService.printAll();

        // Verifying that findAll() method is called
        verify(csvRepository, times(1)).findAll();
    }

    @Test
    public void testSave() throws IOException {
        Map<String, String> data = new HashMap<>();
        data.put("Name", "Bob");
        data.put("Age", "40");

        // Calling the method to test
        csvService.save(data);

        // Verifying that save() method is called
        verify(csvRepository, times(1)).save(data);
    }

    @Test
    public void testUpdate() throws IOException {
        int rowIndex = 1;
        Map<String, String> newData = new HashMap<>();
        newData.put("Name", "Alice");
        newData.put("Age", "26");

        // Calling the method to test
        csvService.update(rowIndex, newData);

        // Verifying that update() method is called
        verify(csvRepository, times(1)).update(rowIndex, newData);
    }

    @Test
    public void testDelete() throws IOException {
        int rowIndex = 1;

        // Calling the method to test
        csvService.delete(rowIndex);

        // Verifying that delete() method is called
        verify(csvRepository, times(1)).delete(rowIndex);
    }
}
