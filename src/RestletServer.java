
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;


public class RestletServer extends Application {


	public static void main(String[] args) throws Exception {

		// URI of the root directory.
		final String ROOT_URI = "file:///F:/Uni/Semester_4/VS/Restlet%20Server/index.html";

		// Create a component
		Component component = new Component();
		component.getServers().add(Protocol.HTTP, 8112);
		component.getClients().add(Protocol.FILE);
		component.getClients().add(Protocol.HTTP);

		// Create an application
		Application application = new Application() {
		    @Override
		    public Restlet createInboundRoot() {

		    	// Create a router
				Router router = new Router(getContext());

				// Serve static HTML from ROOT_URI by default
				Directory directory = new Directory(getContext(), ROOT_URI);
				router.attachDefault(directory);

				// Provide other functions depending on URL
				router.attach("/on", Socket_on.class);
				router.attach("/off", Socket_off.class);
				router.attach("/hello", HelloResource.class);
				router.attach("/switchcontrol", SwitchControl.class);
				return router;
		    }
		};


		// Attach the application to the component and start it
		component.getDefaultHost().attach(application);
		component.start();

	}
}