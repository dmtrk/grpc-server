package grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    //
    private static Server server;
    private static ParsingService parsingService = new ParsingService();

    public static void main(String[] args) {
        try{
            System.out.println(System.getProperties());
            ServerBuilder builder = ServerBuilder.forPort(3333);
            builder.addService(parsingService);
            server = builder.build();
            System.out.println("builder: "+builder);
            server.start();
            System.out.println("server: "+server);

            while(1==1){
                System.out.println("server: "+server.getServices());
                Thread.sleep(1000);
            }

        }catch(Throwable t){
            log.error(t.getMessage(),t);
        }
    }
}
