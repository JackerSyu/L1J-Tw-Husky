/**
 *                            License
 * THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS OF THIS  
 * CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR "LICENSE"). 
 * THE WORK IS PROTECTED BY COPYRIGHT AND/OR OTHER APPLICABLE LAW.  
 * ANY USE OF THE WORK OTHER THAN AS AUTHORIZED UNDER THIS LICENSE OR  
 * COPYRIGHT LAW IS PROHIBITED.
 * 
 * BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE, YOU ACCEPT AND  
 * AGREE TO BE BOUND BY THE TERMS OF THIS LICENSE. TO THE EXTENT THIS LICENSE  
 * MAY BE CONSIDERED TO BE A CONTRACT, THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED 
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */
package l1j.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartLoader {
	private static String JAVA_INI = "./config/java.properties";

	private static String BIN_FILE = "l1jserver.jar";

	private static Process l1jProcess = null;

	private static boolean isShutdown = false;

	public static void main(String[] args) {
		File file;
		// 檢查設定檔是否存在
		file = new File(JAVA_INI);
		if (!file.exists()) {
			System.err.print("JAVA 啟動參數檔案不存在！");
			System.err.print("請確認 config/java.properties 是否可正常讀取。");
			System.exit(1);
		}

		// 初始化執行命令
		List<String> vars = new ArrayList<String>();
		vars.add("java");

		// 讀取參數資料
		try {
			BufferedReader br = new BufferedReader(new FileReader(JAVA_INI));
			String s;

			// 逐行讀取參數資料
			while ((s = br.readLine()) != null) {
				if (s.startsWith("-")) {
					vars.add(s);
				}
			}
			br.close();
		}
		catch (Exception e) {
			System.err.print("讀取啟動參數時發生錯誤，系統無法啟動。");
			System.exit(1);
		}

		// 設置程序終止時的掛鉤
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				if (l1jProcess != null) {
					isShutdown = true;
					try {
						l1jProcess.destroy();
					}
					catch (Exception e) {}
				}
			}
		});

		// 指定執行目錄
		vars.add("-Duser.dir=" + System.getProperty("user.dir"));

		// 指定主程序
		vars.add("-jar");
		vars.add(BIN_FILE);

		// 加入其他參數
		vars.addAll(Arrays.asList(args));

		// 初始化執行參數
		ProcessBuilder pb = new ProcessBuilder(vars);
		pb.redirectErrorStream(true);

		// 輸出提示訊息
		System.out.println("警告！請勿使用右上角的 X 關閉程式。");
		System.out.println("請使用鍵盤組合鍵 Ctrl + C 關閉。");
		System.out.println("");
		
		// 執行程序
		boolean restart = true;
		while (!isShutdown && restart) {
			try {
				// 起始程序
				l1jProcess = pb.start();

				// 擷取螢幕輸出的資訊
				BufferedReader br = null;
				try {
					br = new BufferedReader(new InputStreamReader(l1jProcess.getInputStream()));
					String echo = null;
					while ((echo = br.readLine()) != null) {
						System.out.println(echo);
					}
				}
				catch (Exception e) {}
				finally {
					br.close();
				}

				// 判斷是否重新啟動(暫時無作用)
				// restart = (l1jProcess.exitValue() == 6);
			}
			catch (Exception e) {
				System.err.print("發生不明錯誤，伺服器無法啟動。");
				System.exit(1);
			}
		}

		System.exit(0);
	}
}