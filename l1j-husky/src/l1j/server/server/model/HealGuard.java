/**
 * License
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
package l1j.server.server.model;

import static l1j.server.server.model.skill.L1SkillId.WATER_LIFE;
import static l1j.server.server.model.skill.L1SkillId.POLLUTE_WATER;
//import static l1j.server.server.model.skill.L1SkillId.MASS_POLLUTE_WATER;

import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SkillIconWaterLife;
import l1j.server.server.serverpackets.S_SkillSound;

public class HealGuard extends TimerTask {
    private static Logger _log = Logger.getLogger(HealGuard.class.getName());

    private final L1PcInstance Active_pc;

    public HealGuard(L1PcInstance pc) {
        Active_pc = pc;
    }

    @Override
    public void run() {
        try {
            if (Active_pc.isDead()) {
                return;
            }
            Active_pc.sendPackets(new S_SkillSound(Active_pc.getId(), 2245));
            Active_pc.broadcastPacket(new S_SkillSound(Active_pc.getId(), 2245));
        } catch (Throwable e) {
            _log.log(Level.WARNING, e.getLocalizedMessage(), e);
        }
    }

    public void HealingEffect(L1PcInstance _pc, int rnd) {
// 沒有施法動作
        int heal_hp = rnd + 72; // 採韓版補血, 台版公式不清

        if (_pc.hasSkillEffect(WATER_LIFE)) { // 水之元氣
            heal_hp *= 2;
            _pc.killSkillEffectTimer(WATER_LIFE);
            _pc.sendPackets(new S_SkillIconWaterLife());
        }
//        if (_pc.hasSkillEffect(POLLUTE_WATER) || // 汙濁之水
//                _pc.hasSkillEffect(MASS_POLLUTE_WATER)) { // 集體汙濁之水
//            heal_hp /= 2;
//        }
// 治癒侵蝕術是否有影響, 未有相關測試文, heal_hp是否 *-1 ?
        _pc.setCurrentHp((heal_hp + _pc.getCurrentHp())); // maxHP 限制原L1Pc 有
        _pc.sendPackets(new S_SkillSound(Active_pc.getId(), 2187));
        _pc.broadcastPacket(new S_SkillSound(Active_pc.getId(), 2187)); // 他人看到
        _pc.sendPackets(new S_ServerMessage(77)); // 你覺得舒服多了。
    }
}