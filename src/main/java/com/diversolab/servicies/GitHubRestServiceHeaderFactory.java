package com.diversolab.servicies;

import java.util.Base64;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MultivaluedMap;

import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;
import org.jboss.resteasy.specimpl.MultivaluedMapImpl;

@ApplicationScoped
@ConfigProperties(prefix = "github")
public class GitHubRestServiceHeaderFactory implements ClientHeadersFactory {

	String user = "";
	String password = "";

	@Override
	public MultivaluedMap<String, String> update(MultivaluedMap<String, String> incomingHeaders,
                                                 MultivaluedMap<String, String> clientOutgoingHeaders) {
		MultivaluedMap<String, String> result = new MultivaluedMapImpl<>();
		result.add("Authorization",
				"Basic " + Base64.getEncoder().encodeToString(String.format("%s:%s", user, password).getBytes()));
		return result;
	}

}