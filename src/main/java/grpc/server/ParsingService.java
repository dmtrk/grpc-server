package grpc.server;

import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.data.ParseReply;
import util.data.ParseRequest;
import util.data.ParsingServiceGrpc;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ParsingService extends ParsingServiceGrpc.ParsingServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(ParsingService.class);
    //
/*    private final int port;
    private final Server server;

    public ParsingService(int port) throws IOException {
        this(ServerBuilder.forPort(port), port);
    }

    public ParsingService(ServerBuilder<?> serverBuilder, int port) {
        this.port = port;
        server = serverBuilder.addService(new RouteGuideService(features))
                .build();
    }
...
    public void start() throws IOException {
        server.start();
        logger.info("Server started, listening on " + port);
 ...
    }



    */





    @Override
    public void parse(ParseRequest request, StreamObserver<ParseReply> responseObserver) {
        //super.parse(request, responseObserver);
        if(log.isTraceEnabled()) log.trace("parse() request: "+request);
        try{
            ByteString data = request.getData();
            List<ByteString> list = new ArrayList<>();
            if(data!=null && !data.isEmpty()){
                try (BufferedReader br = new BufferedReader(new StringReader(data.toStringUtf8()))) {
                    String line;
                    while((line=br.readLine())!=null){
                        list.add(ByteString.copyFromUtf8(line));
                    }
                }
            }
            ParseReply reply = ParseReply.newBuilder().addAllData(list).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }catch(Exception e) {
            log.error(e.getMessage());
            responseObserver.onError(e);
        }
    }
}
