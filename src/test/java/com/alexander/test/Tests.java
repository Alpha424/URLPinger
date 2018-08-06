package com.alexander.test;

import com.alexander.ConstantValues;
import com.alexander.domain.Resource;
import com.alexander.service.QueryMethod;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Tests {
    @Autowired QueryMethod queryMethod;

    @Test
    public void queryGoogle() {
        Resource googleResource = new Resource("http://google.com");
        queryMethod.queryResource(googleResource);
        assertNotEquals(0, googleResource.getLastQueryStatus().getResponseCode());
    }

    @Test
    public void queryMalformedURL() {
        Resource malformedResource = new Resource("httpdwaffaf://google.com");
        queryMethod.queryResource(malformedResource);
        assertEquals(0, malformedResource.getLastQueryStatus().getResponseCode());
    }

    @Test
    public void queryURLWithoutProtocol() {
        Resource withoutProtocol = new Resource("google.com");
        queryMethod.queryResource(withoutProtocol);
        assertNotEquals(0, withoutProtocol.getLastQueryStatus().getResponseCode());
    }

}
