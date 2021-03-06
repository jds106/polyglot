package polyglot.server;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.ServerBuilder;
import polyglot.HelloServiceGrpc;

/**
 * A binary which starts a simple gRPC server. This is used to test the client code.
 */
public class Main {
  private static final Logger logger = LoggerFactory.getLogger(Main.class);
  private static final int SERVER_PORT = 12345;

  public static void main(String[] args) {
    logger.info("Starting grpc server on port: " + SERVER_PORT);
    try {
      ServerBuilder.forPort(SERVER_PORT)
          .addService(HelloServiceGrpc.bindService(new HelloServiceImpl()))
          .build()
          .start()
          .awaitTermination();
    } catch (InterruptedException | IOException e) {
      logger.info("Caught exception, shutting down", e);
      System.exit(0);
    }
  }
}
