package com.example.demo.user.controller;

import com.example.demo.user.service.ExcelDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/excel")
public class ExcelDynamicController {

    @Autowired
    private ExcelDynamicService excelDynamicService;

    @PostMapping("/upload-dynamic")
    public ResponseEntity<List<Map<String, Object>>> uploadExcel(@RequestParam("file") MultipartFile file) {
        List<Map<String, Object>> result = excelDynamicService.readExcelToMap(file);
        return ResponseEntity.ok(result);
    }
}
