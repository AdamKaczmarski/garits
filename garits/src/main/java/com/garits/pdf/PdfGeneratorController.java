package com.garits.pdf;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/pdf")
@RestController
public class PdfGeneratorController {
    @GetMapping(value="/stock-ledger",produces = MediaType.APPLICATION_PDF_VALUE)
    ResponseEntity<byte[]> generateStockLedger() {
        return null;
    }
}
