package API;

import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHandler{

	void handleClient(InputStream inputStream, OutputStream outputStream) throws CloneNotSupportedException;

}
