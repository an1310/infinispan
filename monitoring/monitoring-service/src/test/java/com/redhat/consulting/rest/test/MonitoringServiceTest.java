package com.redhat.consulting.rest.test;

import javax.ws.rs.core.MediaType;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class MonitoringServiceTest extends JerseyTest {
    
    private String host = "localhost";
    private String port = "8080";
    private String baseUrl = "http://"+host+":"+port+"/monitoring-service-1.0.0";
    
    @Override
    protected AppDescriptor configure() {
        return new WebAppDescriptor.Builder().build();
    }
    
    @Test
    public void testState() {
        WebResource webResource = client().resource(baseUrl+"/monitoring/state");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(response.getStatus()!=200) {
            throw new RuntimeException("Request for state failed. HTTP error code: "+response.getStatus());
        }
        String output = response.getEntity(String.class);
        Assert.assertFalse(StringUtils.isBlank(output));
    }

    @Test
    public void testMemoryUsage() {
        WebResource webResource = client().resource(baseUrl+"/monitoring/state");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(response.getStatus()!=200) {
            throw new RuntimeException("Request for memory usage failed. HTTP error code: "+response.getStatus());
        }
        String output = response.getEntity(String.class);
        Assert.assertFalse(StringUtils.isBlank(output));
    }

    @Test
    public void testRecordCount() {
        WebResource webResource = client().resource(baseUrl+"/monitoring/state");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(response.getStatus()!=200) {
            throw new RuntimeException("Request for record count failed. HTTP error code: "+response.getStatus());
        }
        String output = response.getEntity(String.class);
        Assert.assertFalse(StringUtils.isBlank(output));
    }

    @Test
    public void testIncomingMessages() {
        WebResource webResource = client().resource(baseUrl+"/monitoring/state");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(response.getStatus()!=200) {
            throw new RuntimeException("Request for incoming messages failed. HTTP error code: "+response.getStatus());
        }
        String output = response.getEntity(String.class);
        Assert.assertFalse(StringUtils.isBlank(output));
    }

    @Test
    public void testOutgoingMessages() {
        WebResource webResource = client().resource(baseUrl+"/monitoring/state");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(response.getStatus()!=200) {
            throw new RuntimeException("Request for outgoing messages failed. HTTP error code: "+response.getStatus());
        }
        String output = response.getEntity(String.class);
        Assert.assertFalse(StringUtils.isBlank(output));
    }

    @Test
    public void testIncomingBytes() {
        WebResource webResource = client().resource(baseUrl+"/monitoring/state");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(response.getStatus()!=200) {
            throw new RuntimeException("Request for incoming bytes failed. HTTP error code: "+response.getStatus());
        }
        String output = response.getEntity(String.class);
        Assert.assertFalse(StringUtils.isBlank(output));
    }

    @Test
    public void testIncomingGroupBytes() {
        WebResource webResource = client().resource(baseUrl+"/monitoring/state");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(response.getStatus()!=200) {
            throw new RuntimeException("Request for incominggroupbytes failed. HTTP error code: "+response.getStatus());
        }
        String output = response.getEntity(String.class);
        Assert.assertFalse(StringUtils.isBlank(output));
    }

    @Test
    public void testOutgoingBytes() {
        WebResource webResource = client().resource(baseUrl+"/monitoring/state");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(response.getStatus()!=200) {
            throw new RuntimeException("Request for outgoingbytes failed. HTTP error code: "+response.getStatus());
        }
        String output = response.getEntity(String.class);
        Assert.assertFalse(StringUtils.isBlank(output));
    }

    @Test
    public void testOutgoingGroupBytes() {
        WebResource webResource = client().resource(baseUrl+"/monitoring/state");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(response.getStatus()!=200) {
            throw new RuntimeException("Request for outgoinggroupbytes failed. HTTP error code: "+response.getStatus());
        }
        String output = response.getEntity(String.class);
        Assert.assertFalse(StringUtils.isBlank(output));
    }

    @Test
    public void testTransactions() {
        WebResource webResource = client().resource(baseUrl+"/monitoring/state");
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(response.getStatus()!=200) {
            throw new RuntimeException("Request for transactions failed. HTTP error code: "+response.getStatus());
        }
        String output = response.getEntity(String.class);
        Assert.assertFalse(StringUtils.isBlank(output));
    }

}
