/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package l1j.server.server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import l1j.server.L1DatabaseFactory;
import l1j.server.server.datatables.SkillsTable;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_DelSkill;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.templates.L1Skills;
import l1j.server.server.utils.Random;
import l1j.server.server.utils.SQLUtil;

public class L1CaoPenaltySkill  {
	
	/**
	 * 死亡隨機掉落魔法 by bizw
	 * 
	 * 印象 天堂私服發佈 www.good-lin.com
	 * 如要轉載，請務必註明出處，謝謝你
	 */

	public static void StartLostSkill(L1PcInstance pc) {

		int lv5 = 0;
		int lv6 = 0;
		int lv7 = 0;
		int lv8 = 0;
		int lv9 = 0;
		int lv10 = 0;
		int lv11 = 0;
		int lv12 = 0;
		int lv13 = 0;
		int lv14 = 0;
		int lv15 = 0;
		int lv16 = 0;
		int lv17 = 0;
		int lv18 = 0;
		int lv19 = 0;
		int lv20 = 0;
		int lv21 = 0;
		int lv22 = 0;
		int lv23 = 0;
		int lv24 = 0;
		int lv25 = 0;
		int lv26 = 0;
		int lv27 = 0;
		int lv28 = 0;

		//取得角色學習魔法數量
		int skillCounts = getSkillLists(pc.getId()).size();
		
		if (skillCounts <= 0) {
			return;
		}
		
		//紀錄魔法數量
		int[] skillLists = new int[skillCounts];

		//置入魔法編號
		int loop = 0;
		for (int skillid : getSkillLists(pc.getId())) {
			skillLists[loop] = skillid;
			loop++;
		}
		
		//隨機取得學習魔法並且掉落他
		int rnd = skillLists[Random.nextInt(skillLists.length)];

		L1Skills l1skills = SkillsTable.getInstance().getTemplate(rnd); 
		pc.removeSkillMastery(rnd); 
		SkillsTable.getInstance().spellLost(pc.getId(),rnd);

		switch (l1skills.getSkillLevel()) {
		case 5:
			lv5 = l1skills.getId();
			break;
		case 6:
			lv6 = l1skills.getId();
			break;
		case 7:
			lv7 = l1skills.getId();
			break;
		case 8:
			lv8 = l1skills.getId();
			break;
		case 9:
			lv9 = l1skills.getId();
			break;
		case 10:
			lv10 = l1skills.getId();
			break;
		case 11:
			lv11 = l1skills.getId();
			break;
		case 12:
			lv12 = l1skills.getId();
			break;
		case 13:
			lv13 = l1skills.getId();
			break;
		case 14:
			lv14 = l1skills.getId();
			break;
		case 15:
			lv15 = l1skills.getId();
			break;
		case 16:
			lv16 = l1skills.getId();
			break;
		case 17:
			lv17 = l1skills.getId();
			break;
		case 18:
			lv18 = l1skills.getId();
			break;
		case 19:
			lv19 = l1skills.getId();
			break;
		case 20:
			lv20 = l1skills.getId();
			break;
		case 21:
			lv21 = l1skills.getId();
			break;
		case 22:
			lv22 = l1skills.getId();
			break;
		case 23:
			lv23 = l1skills.getId();
			break;
		case 24:
			lv24 = l1skills.getId();
			break;
		case 25:
			lv25 = l1skills.getId();
			break;
		case 26:
			lv26 = l1skills.getId();
			break;
		case 27:
			lv27 = l1skills.getId();
			break;
		case 28:
			lv28 = l1skills.getId();
			break;

		default:
			break;

		}
		//刪除skill圖案封包
		pc.sendPackets(new S_DelSkill(0, 0, 0, 0, lv5, lv6,
				lv7, lv8, lv9, lv10, lv11, lv12, lv13, lv14, lv15,
				lv16, lv17, lv18, lv19, lv20, lv21, lv22, lv23, lv24, lv25, lv26, lv27, lv28));
		pc.sendPackets(new S_ServerMessage(166,"損失了 ["+l1skills.getName()+"]"));
		
	}

	//取得角色學習魔法資料
	private static ArrayList<Integer> getSkillLists(int objectId) {
		ArrayList<Integer> skilllists = new ArrayList<Integer>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con
					.prepareStatement("SELECT * FROM character_skills WHERE char_obj_id=?");
			pstm.setInt(1, objectId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				int skillid = rs.getInt("skill_id");
				//不會噴的魔法
				if (skillid <= 32 //法師3級以下
						|| skillid == 154 || skillid == 162 //召喚精靈、強力精靈
						|| skillid == 113  || skillid == 116 //精準目標、呼喚盟友
						|| skillid == 100 || skillid == 101 || skillid == 106 //提煉、行走、暗影閃避
						|| skillid == 181 || skillid == 186	//龍之護鎧、血之渴望
						|| skillid == 205 || skillid == 215) //立方：燃燒、立方：衝擊
				{ 
					continue;
				}
				skilllists.add(skillid);
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
		return skilllists;
	}
}
