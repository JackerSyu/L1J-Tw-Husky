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
package l1j.server.server.clientpackets;

import java.util.logging.Logger;
import l1j.server.Config;
import l1j.server.server.ClientThread;
import l1j.server.server.GetNowTime;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.gametime.L1GameAutoRestart;
import l1j.server.server.serverpackets.S_WhoAmount;
import l1j.server.server.serverpackets.S_WhoCharinfo;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.model.gametime.L1GameRestart;
import l1j.server.server.model.gametime.L1GameAutoRestart;

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket

/**
 * 處理收到由客戶端傳來查詢線上人數的封包
 */


public class C_Who extends ClientBasePacket {

	private static final String C_WHO = "[C] C_Who";
	private static Logger _log = Logger.getLogger(C_Who.class.getName());

	public C_Who(byte[] decrypt, ClientThread client) {
		super(decrypt);
		String s = readS();
		L1PcInstance find = L1World.getInstance().getPlayer(s);
		L1PcInstance pc = client.getActiveChar();

		if (find != null) {
			S_WhoCharinfo s_whocharinfo = new S_WhoCharinfo(find);
			pc.sendPackets(s_whocharinfo);
		} else {
			if (Config.ALT_WHO_COMMAND) {
				String amount = String.valueOf(L1World.getInstance().getAllPlayers().size());
				//TODO 線上資訊
				S_WhoAmount s_whoamount = new S_WhoAmount(amount);
				pc.sendPackets(s_whoamount);
				int i = 1;
				for (L1PcInstance pc1 : L1World.getInstance().getAllPlayers()) {
					if (pc.isGm() == true) {
						pc.sendPackets(new S_SystemMessage(i + "【玩家】【"+ pc1.getName() + "】【血盟】【" + pc1.getClanname()+ "】【等級【" + pc1.getLevel() + "】"));
					} else {
						pc.sendPackets(new S_SystemMessage(i + "【玩家】【"+ pc1.getName() + "】【血盟】【" + pc1.getClanname()+ "】"));
					}
					i++;
				}
				pc.sendPackets(new S_SystemMessage("\\fU" + "================================="));
				pc.sendPackets(new S_SystemMessage("\\fU" + "經驗值: " + Config.RATE_XP+ " 倍"));
				pc.sendPackets(new S_SystemMessage("\\fU" + "掉寶率: "+ Config.RATE_DROP_ITEMS + " 倍"));
				pc.sendPackets(new S_SystemMessage("\\fU" + "取得金幣: "+ Config.RATE_DROP_ADENA + " 倍"));
				pc.sendPackets(new S_SystemMessage("\\fU" + "友好度倍率: "+ Config.RATE_KARMA + " 倍"));
				pc.sendPackets(new S_SystemMessage("\\fU" + "正義值倍率: "+ Config.RATE_LA + " 倍"));
				pc.sendPackets(new S_SystemMessage("\\fU" + "衝裝率: 武器 "+ Config.ENCHANT_CHANCE_WEAPON + "%  /  防具 "+ Config.ENCHANT_CHANCE_ARMOR + "%"));
				if (Config.RESTART_TIME != 0) {
					//TODO 今天日期
					int Mon = GetNowTime.GetNowMonth();//TODO 月份錯誤補正
					pc.sendPackets(new S_SystemMessage("\\fU" + "今天是 "+ GetNowTime.GetNowYear() + " 年 " + (Mon + 1)+ " 月 " + GetNowTime.GetNowDay() + " 日。"));
					//TODO 目前時間
					pc.sendPackets(new S_SystemMessage("\\fU" + "現在時間(24h): "+ GetNowTime.GetNowHour() + " 時 "+ GetNowTime.GetNowMinute() + " 分 "+ GetNowTime.GetNowSecond() + " 秒。"));
					int second = L1GameRestart.getInstance().GetRemnant();
					pc.sendPackets(new S_SystemMessage("\\fU" + "距離伺服器重啟時間還有: "+ (second / 60) / 60 + " 小時 " + (second / 60) % 60+ " 分 " + second % 60 + " 秒。"));
				}
				if (Config.AUTORESTART != "") {
					//TODO 今天日期
					int Mon = GetNowTime.GetNowMonth();//TODO 月份錯誤補正
					pc.sendPackets(new S_SystemMessage("\\fU" + "今天是 "+ GetNowTime.GetNowYear() + " 年 " + (Mon + 1)+ " 月 " + GetNowTime.GetNowDay() + " 日。"));
					//TODO 目前時間
					pc.sendPackets(new S_SystemMessage("\\fU" + "現在時間(24h): "+ GetNowTime.GetNowHour() + " 時 "+ GetNowTime.GetNowMinute() + " 分 "+ GetNowTime.GetNowSecond() + " 秒。"));
					long second = L1GameAutoRestart.getInstance().GetRemnant();
					pc.sendPackets(new S_SystemMessage("\\fU" + "距離伺服器重啟時間還有: "+ (second / 60) / 60 + " 小時 " + (second / 60) % 60+ " 分 " + second % 60 + " 秒。"));
				}


				pc.sendPackets(new S_SystemMessage("\\fU" + "================================="));
			} else {
				String amount = String.valueOf(L1World.getInstance().getAllPlayers().size());
				S_WhoAmount s_whoamount = new S_WhoAmount(amount);
				pc.sendPackets(s_whoamount);
			}
		}
	}

	@Override
	public String getType() {
		return C_WHO;
	}
}