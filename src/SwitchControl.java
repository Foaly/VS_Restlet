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

/**
 * Created by Maximilian on 05.01.2015.
 */
public class SwitchControl extends ServerResource {
    @Get
    public Representation my_get() throws ResourceException, IOException {

        // Call the client resource

        ClientResource resource = new
            // ClientResource("http://192.168.178.31/event?port=4&action=0&pass=password");
            ClientResource("http://www.ipv6lab.beuth-hochschule.de/event.php?port=3&action=0&pass=password");

        Writer writer = new StringWriter();
        try {
            resource.get().write(writer);
        }
        catch (Exception e) {
            writer.write(e.getLocalizedMessage() + ": Failed to get resource from IPv6 Lab.");
            System.out.println(writer.toString());
        }


        StringBuilder stringBuilder = new StringBuilder();

        // Build the response as a HTML page
        stringBuilder.append("<html>");
        stringBuilder.append("<head><title>RESTful Application</title></head>");
        stringBuilder.append("<body bgcolor=white>");
        stringBuilder.append("<h1>Kontrollinterface der Steckdose</h1>");
        stringBuilder.append("<table border=\"1\" width=\"300px\">");

        stringBuilder.append("<tr>");
        stringBuilder.append("<td>Port Number</td>");
        stringBuilder.append("<td>On Switch</td>");
        stringBuilder.append("<td>Off Switch</td>");
        stringBuilder.append("</tr>");

        for (int i = 1; i < 5; i++) {
            stringBuilder.append("<tr>");
            stringBuilder.append("<td>" + i + "</td>");
            stringBuilder.append("<td><a href=\"/on?port=" + i + "\">On</a></td>");
            stringBuilder.append("<td><a href=\"/off?port=" + i + "\">Off</a></td>");
            stringBuilder.append("</tr>");
        }



        stringBuilder.append("</table>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");

        return (new StringRepresentation(stringBuilder.toString(),
                MediaType.TEXT_HTML));

    }
}
