package com.ebay.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExportService.class)
public class ExportServiceTest {
    @Autowired
    ExportService service;

    @Test
    public void exportDoc() {
        service.exportDoc();
    }
}