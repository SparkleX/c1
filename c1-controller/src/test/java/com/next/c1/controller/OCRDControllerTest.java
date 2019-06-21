package com.next.c1.controller;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.next.c1.domain.DoOCRD;
import com.next.c1.service.OCRDService;


@RunWith(SpringRunner.class)
@WebMvcTest(OCRDController.class)
public class OCRDControllerTest extends BaseControllerTest<DoOCRD, OCRDService>{
}