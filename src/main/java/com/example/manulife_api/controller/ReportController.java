package com.example.manulife_api.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.manulife_api.model.User;
import com.example.manulife_api.repository.UserRepository;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final UserRepository userRepository;

    public ReportController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users/pdf")
    public ResponseEntity<byte[]> generateUserReport(@RequestParam(required = false) String jenisUser) {
        try {
            // 1. Ambil data dari database
            List<User> users = (jenisUser != null && !jenisUser.isEmpty()) 
                ? userRepository.findByJenisUser(jenisUser) 
                : userRepository.findAll();

            // 2. Pastikan data tidak kosong sebelum diproses
            if (users.isEmpty()) {
                return ResponseEntity.badRequest().body("Tidak ada data untuk laporan".getBytes());
            }

            // 3. Load template laporan (.jrxml) dari resources
            InputStream reportStream = new ClassPathResource("reports/user_report.jrxml").getInputStream();

            // 4. Compile file .jrxml menjadi .jasper di runtime
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            // 5. Convert data ke JRBeanCollectionDataSource agar bisa diterima oleh JasperReports
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);

            // 6. Parameter tambahan untuk laporan (opsional)
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("REPORT_TITLE", "Laporan Data User");

            // 7. Isi laporan dengan data dari Java (bukan dari query di .jrxml)
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // 8. Konversi laporan ke PDF
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
            byte[] pdfBytes = baos.toByteArray();

            // 9. Return PDF sebagai response
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=user_report.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(("Gagal membuat laporan: " + e.getMessage()).getBytes());
        }
    }
}
