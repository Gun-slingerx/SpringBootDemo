package org.example;

import static org.junit.Assert.assertTrue;

import org.example.msg.service.MsgTemplateService;
import org.example.msg.util.SnowflakeIdWorker;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit test for simple App.
 */
@SpringBootTest
public class AppTest 
{

    @Autowired
    SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    MsgTemplateService msgTemplateService;


    @Test
    public void shouldAnswerWithTrue()
    {
        Long id = snowflakeIdWorker.nextId();


        assertTrue( true );
    }
}
