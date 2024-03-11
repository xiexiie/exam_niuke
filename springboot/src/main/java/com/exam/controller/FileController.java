package com.exam.controller;

import com.alibaba.druid.util.StringUtils;
import com.exam.entity.ApiResult;
import com.exam.entity.Student;
import com.exam.service.StudentService;
import com.exam.serviceimpl.StudentServiceImpl;
import com.exam.util.ApiResultHandler;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class FileController {

    @Autowired
    private StudentServiceImpl studentService;

    // 设置固定的日期格式
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    // 将 yml 中的自定义配置注入到这里
    @Value("${gorit.file.root.path}")
    private String filePath;
    // 日志打印
    private Logger log = LoggerFactory.getLogger("FileController");

    // 文件上传 （可以多文件上传）
    @PostMapping("/upload")
    public ApiResult fileUploads(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
//        // 得到格式化后的日期
        String format = sdf.format(new Date());
        // 获取上传的文件名称
        String fileName = file.getOriginalFilename();
        // 时间 和 日期拼接
        String newFileName = format + "_" + fileName;
        // 得到文件保存的位置以及新文件名
        File dest = new File(filePath + newFileName);
        try {
//             上传的文件被保存了
//            file.transferTo(dest);
//             打印日志
//            log.info("上传成功，当前上传的文件保存在 {}",filePath + newFileName);
//            读取文件，添加学生信息
            if(file == null){
                return ApiResultHandler.buildApiResult(400, "上传失败，文件为空！", 0);
            }
            //读取文件流
            InputStream inputStream = file.getInputStream();
            //文件名
            String fileName2 =file.getOriginalFilename();
            if (!fileName2.matches("^.+\\.(?i)(xls)$") && !fileName2.matches("^.+\\.(?i)(xlsx)$")) {

                return ApiResultHandler.buildApiResult(400, "上传失败，文件格式不正确！", 0);
            }
            Workbook wb = null;
            if (fileName2.matches("^.+\\.(?i)(xlsx)$")) {
                //xlsx格式
                wb = new XSSFWorkbook(inputStream);
            } else {
                //xls格式
                wb = new HSSFWorkbook(inputStream);
            }
            if (wb != null){
                //姓名 年级 性别 学号（密码）
                //默认读取第一个sheet
                Sheet sheet = wb.getSheetAt(0);
                if (sheet != null){
                    //最先读取首行
                    boolean firstRow = true;
                    List<Student> studentList = new ArrayList<>();
                    boolean isThrow = false;
                    //根据学号判断文件是否包含重复的学生，
                    //如果需要多个字段唯一确定一条数据可以使用List<Map<String, Object>>,
                    //下面同样使用contains判断是否已经包含同一条数据
                    List<String> phoneList=new ArrayList<>();
                    try {
                        if (sheet.getLastRowNum() > 0) {
                            for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
                                //循环行
                                Student student = new Student();
                                Row row = sheet.getRow(i);
                                //首行  提取注解
                                if (firstRow) {
                                    if (row.getCell(0).getStringCellValue().equals("姓名")
                                            && row.getCell(1).getStringCellValue().equals("年级") //性别
                                            && row.getCell(2).getStringCellValue().equals("性别") //手机号
                                            && row.getCell(3).getStringCellValue().equals("学号"))//年级班级
                                    {
                                    } else {
                                        return ApiResultHandler.buildApiResult(400, "格式不正确，请下载模板进行参考", 0);
                                    }
                                    firstRow = false;
                                } else {
                                    //忽略空白行
//                                    if (row == null || ToolHelp.isRowEmpty(row)) {
//                                        continue;
//                                    }
                                    int theRow = i + 1;
                                    if (row.getCell(0) != null) {
                                        row.getCell(0).setCellType(CellType.STRING);
                                        String stuName = row.getCell(0).getStringCellValue();
                                        if (StringUtils.isEmpty(stuName)) {
                                            isThrow = true;
                                            return ApiResultHandler.buildApiResult(400, "导入失败(第" + theRow + "行,姓名不能为空)", 0);

                                        } else {
                                            student.setStudentName(stuName);
                                        }
                                    } else {
                                        isThrow = true;
                                        return ApiResultHandler.buildApiResult(400, "导入失败(第" + theRow + "行,姓名不能为空)", 0);

                                    }
                                    if (row.getCell(1) != null) {
                                        row.getCell(1).setCellType(CellType.STRING);
                                        String grade = row.getCell(1).getStringCellValue();
                                        if (StringUtils.isEmpty(grade)) {
                                            isThrow = true;
                                            return ApiResultHandler.buildApiResult(400, "导入失败(第" + theRow + "行,年级不能为空)", 0);

                                        } else {
                                            student.setGrade(grade);
                                        }
                                    } else {
                                        isThrow = true;
                                        return ApiResultHandler.buildApiResult(400, "导入失败(第" + theRow + "行,年级不能为空)", 0);
                                    }
                                    if (row.getCell(3) != null) {
                                        //学号为密码
                                        row.getCell(3).setCellType(CellType.STRING);
                                        String pwd = row.getCell(3).getStringCellValue();
                                        if (StringUtils.isEmpty(pwd)) {
                                            isThrow = true;
                                            return ApiResultHandler.buildApiResult(400, "导入失败(第" + theRow + "行,学号有误)", 0);

                                        } else {
                                            if (!phoneList.isEmpty() && phoneList.size() > 0) {
                                                //判断手机号是否重复
                                                if (phoneList.contains(pwd)) {
                                                    isThrow = true;
                                                    return ApiResultHandler.buildApiResult(400, "导入失败(第" + pwd + "行,学号有重复)", 0);
                                                } else {
                                                    phoneList.add(pwd);
                                                    student.setPwd(pwd);
                                                }
                                            } else {
                                                student.setPwd(pwd);
                                            }
                                        }
                                    } else {
                                        isThrow = true;
                                        return ApiResultHandler.buildApiResult(400, "导入失败(第" + theRow + "行,学号有重复)", 0);
                                    }


                                    if (row.getCell(2) != null) {
                                        row.getCell(2).setCellType(CellType.STRING);
                                        String sex = row.getCell(2).getStringCellValue();
                                        if (StringUtils.isEmpty(sex)) {
                                            isThrow = true;
                                            return ApiResultHandler.buildApiResult(400, "导入失败(第" + theRow + "行,性别不能为空)", 0);

                                        } else {
                                            student.setSex(sex);
                                        }
                                    } else {
                                        isThrow = true;
                                        return ApiResultHandler.buildApiResult(400, "导入失败(第" + theRow + "行,性别不能为空)", 0);
                                    }
                                    //默认为男
                                    student.setSex("男");
                                    studentList.add(student);
                                }
                                if (isThrow) {
                                    break;
                                }
                            }
                        } else {
                            isThrow = true;
                            return ApiResultHandler.buildApiResult(400, "导入失败,数据为空", 0);
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                    for (Student student :studentList){
                        studentService.add(student);
                    }
                }
            }
            // 自定义返回的统一的 JSON 格式的数据，可以直接返回这个字符串也是可以的。
            return ApiResultHandler.buildApiResult(200, "上传成功,添加成功！", 1);
        } catch (IOException e) {
            log.error(e.toString());
        }
        // 待完成 —— 文件类型校验工作
        return  ApiResultHandler.buildApiResult(400, "上传失败！", 0);
    }
}

