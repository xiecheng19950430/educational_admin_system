package com.ebay.controllers;

import com.ebay.services.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/export")
public class ExportController {
    @Autowired
    private ExportService service;

    @RequestMapping("/qualityReportDoc")
    public void exportQualityReportDoc(HttpServletRequest request, HttpServletResponse response) {
        service.exportQualityReportDoc(request, response);
    }
}
