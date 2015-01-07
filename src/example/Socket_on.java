package example;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class Socket_on extends ServerResource{
	@Get
	public Representation my_get() throws ResourceException, IOException {

		// Call the client resource
		
		ClientResource resource = new 
		// ClientResource("http://192.168.178.31/event?port=4&action=1&pass=password");
		ClientResource("http://www.ipv6lab.beuth-hochschule.de/event.php?port=3&action=1&pass=password");

		Writer writer = new StringWriter();
		resource.get().write(writer);

		StringBuilder stringBuilder = new StringBuilder();

		// Build the response as a HTML page
		stringBuilder.append("<html>");
		stringBuilder.append("<head><title>RESTful Application</title></head>");
		stringBuilder.append("<body bgcolor=white>");
		stringBuilder.append("<table border=\"0\">");
		stringBuilder.append("<tr>");
		stringBuilder.append("<td>");
		stringBuilder.append("<h3>Status der Steckdose</h3>");
		stringBuilder.append(writer.toString());
		stringBuilder.append("</td>");
		stringBuilder.append("</tr>");
		stringBuilder.append("</table>");
		stringBuilder.append("</body>");
		stringBuilder.append("</html>");

		return (new StringRepresentation(stringBuilder.toString(),
				MediaType.TEXT_HTML));

	}
}
