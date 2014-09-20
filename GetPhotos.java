import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetPhotos {
	public static String address1 = "http://xsgl.nju.edu.cn/sw-services/apps/showimage.jsp?id=X";
	public static String address2 = "X&objPath=SWMS/XSJBXXGLZXT/JBXX/T_JBXX_JBXX&ZDM=ZPWJM&userId=";

	public static String getBase64(String src) {
		if (src == null)
			return null;
		else
			return (new sun.misc.BASE64Encoder()).encode(src.getBytes());
	}

	public static void download(String studentNumber)
			throws MalformedURLException {
		int bytesum = 0;
		int byteread = 0;
		URL url = new URL(address1 + getBase64(studentNumber) + address2
				+ studentNumber);

		try {
			URLConnection conn = url.openConnection();
			InputStream inStream = conn.getInputStream();
			File file = new File(studentNumber + ".jpg");
			file.createNewFile();
			FileOutputStream fs = new FileOutputStream(file);

			byte[] buffer = new byte[1204];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				System.out.println(bytesum);
				fs.write(buffer, 0, byteread);
			}
			fs.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		try {
			download("141250001");
			// for (int i = 10; i < 100; i++){
			// 	String s = "1412500" + i;
			// 	download(s);
			// }
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}

}
