package com.next.c1.controller;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.next.c1.domain.DoOITM;
import com.next.c1.service.OITMService;


@RunWith(SpringRunner.class)
@WebMvcTest(OITMController.class)
public class OITMControllerTest extends BaseControllerTest<DoOITM, OITMService>{
}