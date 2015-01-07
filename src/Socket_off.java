import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class Socket_off extends ServerResource {
	@Get
	public Representation my_get() throws ResourceException, IOException {

		// get port from the URL parameters
		String port = getQuery().getValues("port");

		Writer writer = new StringWriter();

		// if there is a port specified, create and call the client resource
		if(port != null) {
			ClientResource resource = new
					// ClientResource("http://192.168.178.31/event?port=4&action=0&pass=password");
					//ClientResource("http://www.ipv6lab.beuth-hochschule.de/event.php?port=" + port + "&action=0&pass=password");
					ClientResource("http://141.64.156.12/event?port=" + port + "&action=0&pass=password");

			try {
				// try to call the client resource
				resource.get().write(writer);
			} catch (Exception e) {
				writer.write(e.getLocalizedMessage() + ": Failed to get resource from IPv6 Lab.");
				System.out.println(writer.toString());
			}
		}
		else {
			writer.write("Error: No port was specified!");
		}



		StringBuilder stringBuilder = new StringBuilder();

		// Build the response as a HTML page
		stringBuilder.append("<html>");
		stringBuilder.append("<head><title>RESTful Application</title></head>");
		stringBuilder.append("<body bgcolor=white>");
		stringBuilder.append("<h2>Status der Steckdose</h2>");
		stringBuilder.append("<p>" + writer.toString() + "</p>");
		stringBuilder.append("</body>");
		stringBuilder.append("</html>");

		return (new StringRepresentation(stringBuilder.toString(),
				MediaType.TEXT_HTML));

	}
}
