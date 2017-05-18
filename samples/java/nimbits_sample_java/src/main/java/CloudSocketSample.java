import com.nimbits.client.model.UrlContainer;
import com.nimbits.client.model.accesskey.AccessKey;
import com.nimbits.client.model.accesskey.AccessKeyModel;
import com.nimbits.client.model.common.impl.CommonFactory;
import com.nimbits.client.model.email.EmailAddress;
import com.nimbits.client.model.server.Server;
import com.nimbits.client.model.server.ServerFactory;

/**
 * An example of writing values to a data point and also having a web socket open to receive those events.
 * What is special about this is that it uses the cloud server that relays data through a websocket middle tier.
 * EMAIL_ADDRESS : the account owners email
 * ACCESS_KEY : an access key you created by logging into the web console at INSTANCE_URL
 * INSTANCE_URL : The url to the nimbits server instance
 */

public class CloudSocketSample {


    private static final EmailAddress EMAIL_ADDRESS = CommonFactory.createEmailAddress("test@example.com");

    //a running jetty server with nimbits installed (using root.war)
    private static final UrlContainer INSTANCE_URL = UrlContainer.getInstance("cloud.nimbits.com");//"localhost:8080");
    //  private static final UrlContainer INSTANCE_URL = UrlContainer.getInstance("localhost:8085");//"localhost:8080");

    //you can create this server object with an API KEY you configured your server with to make authentication easy

    private static final AccessKey API_KEY = new AccessKeyModel.Builder().code("key").create();// AccessToken.getInstance("key");
    private static final Server cloudServer = ServerFactory.getInstance(INSTANCE_URL, EMAIL_ADDRESS, API_KEY);


    public static void main(String[] args) throws Exception {


//        NimbitsClient client = NimbitsClientFactory.getInstance(cloudServer);
//        User user = client.login();
//
//        System.out.println("Hello " + user.getEmail() + " " + user.getToken() + " " + cloudServer.getUrl());
//
//
//        //connect to the server with your new session token
//        Server authenticationContainer = ServerFactory.getInstance(INSTANCE_URL, EMAIL_ADDRESS, new AccessKeyModel.Builder().code(user.getToken()).create());
//
//        SocketConnection socketConnection = new SocketConnection(authenticationContainer, new SocketListener() {
//
//
//            public void onOpen(Connection connection)
//            {
//                System.out.println("connected!");
//            }
//
//            public void onClose(int closeCode, String message)
//            {
//                System.out.println("closing socket: " + message);
//            }
//
//            @Override
//            public void onNotify(Point point) {
//                System.out.println("A subscription to a point's events has been processed");
//                System.out.println(point.getName().getValue());
//                System.out.println(point.getValue().getAlertState().name());
//                // System.out.println(point.getValue().getData());
//            }
//
//            @Override
//            public void onUpdate(Point point) {
//                System.out.println("A point was updated");
//                System.out.println(point.getName().getValue());
//                System.out.println(point.getValue().getAlertState().name());
//                // System.out.println(point.getValue().getData());
//            }
//        });

//        socketConnection.sendMessage("Hello Nimbits Socket! " + System.currentTimeMillis());
//        ValueHelper valueHelper = HelperFactory.getValueHelper(cloudServer);
//        Random random = new Random();
//        valueHelper.recordValue("foo", random.nextDouble() * 100);100

    }
}