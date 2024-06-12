package l1j.server.server.model.gametime;

import l1j.server.Config;
import l1j.server.server.ClientThread;
import l1j.server.server.GameServer;
import l1j.server.server.GeneralThreadPool;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.L1World;
import l1j.server.server.serverpackets.S_SystemMessage;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class L1GameAutoRestart {
        private static Logger _log = Logger.getLogger(L1GameAutoRestart.class.getName());

        private static L1GameAutoRestart _instance;
        private volatile L1GameTime _currentTime = new L1GameTime();
        private L1GameTime _previousTime = null;

        private List<L1GameTimeListener> _listeners = new CopyOnWriteArrayList<L1GameTimeListener>();

        private static long willRestartTime;
        public  long _remnant ;

        private class TimeUpdaterRestart implements Runnable{
            @Override
            public void run() {
                while ( true ) {
                    _previousTime = _currentTime;
                    _currentTime = new L1GameTime();
                    notifyChanged();
                    long remnantMin = calculateRemainingMinutes(GetRestartTime()) ;
                    long remnant = remnantMin * 60 ;
                    System.out.println("正在載入自動重開設定...完成! "+ (remnantMin + 1) +"分鐘後" );
                    while ( remnant > 0 ) {
                        for ( long i = remnant + 60; i >= 0 ; i -- ) {
                            SetRemnant(i) ;
                            willRestartTime=i;
                            if ( i % 60 == 0 && i <= 300 && i != 0 )  {
                                BroadCastToAll( "伺服器將於 " + i/60 +" 分鐘後自動重啟,請至安全區域準備登出。" );
                                System.out.println( "伺服器將於 " + i/60 +" 分鐘後重新啟動" );
                            } //TODO if (五分鐘內 一分鐘一次)
                            else if ( i <= 30 && i != 0 )  {
                                BroadCastToAll( "伺服器將於 " + i +"秒後重新啟動,煩請儘速下線！" );
                                System.out.println( "伺服器將於 " + i +" 秒後重新啟動" );
                            } //TODO else if (30秒內 一秒一次)
                            else if ( i == 0) {
                                BroadCastToAll("伺服器自動重啟。");
                                System.out.println("伺服器重新啟動。");
                                GameServer.getInstance().shutdown(); //TODO 修正自動重開角色資料會回溯
                                disconnectAllCharacters();
                                System.out.println("所有角色已中斷連線。");
                                System.out.println("等待60秒後重開。");
                                System.exit(1);
                            } //TODO if (1秒)
                            try {
                                Thread.sleep( 1000 );
                            } catch ( InterruptedException e ) {
                                _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
                            }
                        }
                    }
                }
            }
        }

        public void disconnectAllCharacters() {
            Collection<L1PcInstance> players = L1World.getInstance()
                    .getAllPlayers();
            for (L1PcInstance pc : players) {
                pc.getNetConnection().setActiveChar(null);
                pc.getNetConnection().kick();
            }
            // 全員Kickした後に保存処理をする
            for (L1PcInstance pc : players) {
                ClientThread.quitGame(pc);
                L1World.getInstance().removeObject(pc);
            }
        }

        private String GetRestartTime()  {
            return Config.AUTORESTART;
        }

        private void BroadCastToAll( String string ) {
            Collection <L1PcInstance> allpc = L1World.getInstance().getAllPlayers();
            for ( L1PcInstance pc : allpc ) {
                pc.sendPackets( new S_SystemMessage( string ) );
            }
        }

        public void SetRemnant ( long remnant ) {
            _remnant = remnant ;
        }

        public static long getWillRestartTime(){
            return willRestartTime;
        }

        public long GetRemnant ( ) {
            return _remnant ;
        }

        private boolean isFieldChanged(int field) {
            return _previousTime.get(field) != _currentTime.get(field);
        }

        private void notifyChanged() {
            if (isFieldChanged(Calendar.MONTH)) {
                for (L1GameTimeListener listener : _listeners) {
                    listener.onMonthChanged(_currentTime);
                }
            }
            if (isFieldChanged(Calendar.DAY_OF_MONTH)) {
                for (L1GameTimeListener listener : _listeners) {
                    listener.onDayChanged(_currentTime);
                }
            }
            if (isFieldChanged(Calendar.HOUR_OF_DAY)) {
                for (L1GameTimeListener listener : _listeners) {
                    listener.onHourChanged(_currentTime);
                }
            }
            if (isFieldChanged(Calendar.MINUTE)) {
                for (L1GameTimeListener listener : _listeners) {
                    listener.onMinuteChanged(_currentTime);
                }
            }
        }

        private L1GameAutoRestart() {
            GeneralThreadPool.getInstance().execute(new TimeUpdaterRestart());
        }

        public static void init() {
            _instance = new L1GameAutoRestart();
        }

        public static L1GameAutoRestart getInstance() {
            return _instance;
        }

        public L1GameTime getGameTime() {
            return _currentTime;
        }

        public void addListener(L1GameTimeListener listener) {
            _listeners.add(listener);
        }

        public void removeListener(L1GameTimeListener listener) {
            _listeners.remove(listener);
        }

        private  long calculateRemainingMinutes(String timeString) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

            try {
                // 解析字符串到日期對象
                Date restartDate = dateFormat.parse(timeString);
                // 獲取當前時間
                Calendar now = Calendar.getInstance();
                Date nowDate = now.getTime();

                // 將解析的時間設置到當前日期
                Calendar restartCalendar = Calendar.getInstance();
                restartCalendar.setTime(restartDate);
                restartCalendar.set(Calendar.YEAR, now.get(Calendar.YEAR));
                restartCalendar.set(Calendar.MONTH, now.get(Calendar.MONTH));
                restartCalendar.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH));

                // 如果解析的時間早於當前時間，設置為第二天的時間
                if (restartCalendar.before(now)) {
                    restartCalendar.add(Calendar.DAY_OF_MONTH, 1);
                }

                // 計算時間差
                long diffInMillis = restartCalendar.getTimeInMillis() - now.getTimeInMillis();
                return TimeUnit.MILLISECONDS.toMinutes(diffInMillis);

            } catch (ParseException e) {
                e.printStackTrace();
                return -1;
            }
        }
    }
