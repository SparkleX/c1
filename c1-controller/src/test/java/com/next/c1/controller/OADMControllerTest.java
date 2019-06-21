package com.next.c1.controller;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.next.c1.domain.DoOADM;
import com.next.c1.service.OADMService;


@RunWith(SpringRunner.class)
@WebMvcTest(OADMController.class)
public class OADMControllerTest extends BaseControllerTest<DoOADM, OADMService>{

}