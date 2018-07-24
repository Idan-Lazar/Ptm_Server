
package API;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MyServer implements Server {

	private int port;
	private volatile boolean stop;
	private Socket aClient;
	public MyCh c = new MyCh();
	Comparator<MyCh> comparator = new Comp();
	PriorityQueue<MyCh> q = new PriorityQueue<MyCh>(10, comparator);
	ExecutorService executor = Executors.newFixedThreadPool(3);

	public MyServer(int port) {
		this.setPort(port);
		setStop(false);
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public boolean isStop() {
		return stop;
	}
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	private void runServer()throws Exception
	{
		ServerSocket server=new ServerSocket(port);
		server.setSoTimeout(1000);
		while(!stop){
			try{
				Socket aClient=server.accept(); // blocking call
				executor.execute(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							c.handleClient(aClient.getInputStream(), aClient.getOutputStream());
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

		          try {
					aClient.getInputStream().close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		           try {
					aClient.getOutputStream().close();
				} catch (IOException e) {/*...*/}
		           try {
					aClient.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					}
				});
			}

			catch(SocketTimeoutException e) {/*...*/}
		} server.close();
	}
	public void start() {
		/*try
		{
			q.add(c);
			executor.execute(()->{
				try {
					runServer(q.remove());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		new Thread(() -> {
			try {
				runServer();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();


	}
	public void stop() {
		stop = true;
	}




}
