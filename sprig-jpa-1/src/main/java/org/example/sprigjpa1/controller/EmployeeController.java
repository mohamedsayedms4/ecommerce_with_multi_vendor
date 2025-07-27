package org.example.sprigjpa1.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.sprigjpa1.model.Employee;
import org.example.sprigjpa1.service.EmployeeService;
import org.example.sprigjpa1.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
@RequestMapping("/EMP")
public class EmployeeController {
    private final ImageUploadService imageUploadService;

    public EmployeeController(EmployeeService employeeService, ImageUploadService imageUploadService) {
        this.employeeService = employeeService;
        this.imageUploadService = imageUploadService;
    }

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/id/{id}")
    public Employee findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @GetMapping("/name/{name}")
    public List<Employee> findByName(@PathVariable String name) {
        return employeeService.findByFirstName(name);
    }

    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }



    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=employees.xlsx");

        List<Employee> employees = employeeService.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Employees");

            // Create Header Row with bold style
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "First Name", "Last Name", "Last Update", "Salary", "Department"};

            // Style
            var boldFont = workbook.createFont();
            boldFont.setBold(true);
            var headerStyle = workbook.createCellStyle();
            headerStyle.setFont(boldFont);

            for (int i = 0; i < headers.length; i++) {
                var cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Fill Data Rows
            int rowNum = 1;
            for (Employee emp : employees) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(emp.getId());
                row.createCell(1).setCellValue(emp.getFirstName());
                row.createCell(2).setCellValue(emp.getLastName());
                row.createCell(3).setCellValue(
                        emp.getLastupdate() != null ? emp.getLastupdate().toString() : ""
                );
                row.createCell(4).setCellValue(
                        emp.getSalary() != null ? emp.getSalary() : 0.0
                );
                row.createCell(5).setCellValue(
                        emp.getDepartment() != null ? emp.getDepartment().getName() : "N/A"
                );
            }

            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(response.getOutputStream());
        }
    }


    @PostMapping("/insert")
    public Employee insert(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PutMapping("/update")
    public Employee update(@RequestBody Employee employee) {
        return employeeService.update(employee);
    }

    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.deleteById(id);
    }

    @GetMapping("/department/{id}")
    public List<Employee> findByDepartment(@PathVariable("id") int departmentId) {
        return employeeService.findByDepartment(departmentId);
    }

    @GetMapping("/department1/{name}")
    public List<Employee> findByDepartmentName(@PathVariable("name") String name) {
        return employeeService.findByDepartmentName(name);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Employee e) {
        Optional<Employee> employee = employeeService.login(e.getUserName(), e.getPassword());

        if (employee.isPresent()) {
            return ResponseEntity.ok("Login successful. Welcome " + employee.get().getFirstName());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }



    @PostMapping("/{id}/upload-image")
    public ResponseEntity<String> uploadImage(@PathVariable Long id,
                                              @RequestParam("image") MultipartFile file) {
        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            // مسار ثابت داخل مجلد المشروع
            String uploadDir = System.getProperty("user.dir") + File.separator + "uploads" + File.separator;
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) uploadPath.mkdirs();

            file.transferTo(new File(uploadDir + fileName));

            Optional<Employee> empOpt = employeeService.findByIdOptional(id);
            if (empOpt.isPresent()) {
                Employee emp = empOpt.get();
                emp.setImagePath(uploadDir + fileName); 
                employeeService.save(emp);
                return ResponseEntity.ok("Image uploaded successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
        }
    }



    @GetMapping("/image/{id}")
    public void getImage(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Optional<Employee> empOpt = employeeService.findByIdOptional(id);
        if (empOpt.isPresent() && empOpt.get().getImagePath() != null) {
            File imgFile = new File(empOpt.get().getImagePath());
            if (imgFile.exists()) {
                response.setContentType("image/jpeg");
                StreamUtils.copy(new FileInputStream(imgFile), response.getOutputStream());
            } else {
                response.sendError(HttpStatus.NOT_FOUND.value(), "Image not found");
            }
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value(), "Employee or image not found");
        }
    }


    @PostMapping("/{id}/upload-cloud-image")
    public ResponseEntity<String> uploadImageFromserver(@PathVariable Long id,
                                              @RequestParam("image") MultipartFile file) {
        try {
            Optional<Employee> empOpt = employeeService.findByIdOptional(id);
            if (empOpt.isPresent()) {
                String imageUrl = imageUploadService.uploadFile(file);  // رفع الصورة على Cloudinary

                Employee emp = empOpt.get();
                emp.setImagePath(imageUrl); // خزّن الرابط بدل المسار المحلي
                employeeService.save(emp);

                return ResponseEntity.ok("Image uploaded successfully: " + imageUrl);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
        }
    }


    @GetMapping("/image/cloud/{id}")
    public ResponseEntity<?> getImage(@PathVariable Long id) {
        Optional<Employee> empOpt = employeeService.findByIdOptional(id);
        if (empOpt.isPresent() && empOpt.get().getImagePath() != null) {
            String imageUrl = empOpt.get().getImagePath();
            return ResponseEntity.status(HttpStatus.FOUND)  // 302 redirect
                    .location(URI.create(imageUrl))
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee or image not found");
        }
    }



}
