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
 * http://TODOwww.gnu.org/copyleft/gpl.html
 */
package l1j.server.server;
import java.util.Calendar;

/**
 * 循環公告
 * @return bymca
 */
public class GetNowTime {
    public static int GetNowYear() {
        Calendar rightNow = Calendar.getInstance();//TODO 取得預設月曆物件
        int nowYear;
        nowYear = rightNow.get(Calendar.YEAR);//TODO 取得現年之值
        return nowYear;//TODO 傳回取得現年之值
    }

    public static int GetNowMonth() {
        Calendar rightNow = Calendar.getInstance();//TODO 取得預設月曆物件
        int nowMonth;
        nowMonth = rightNow.get(Calendar.MONTH);//TODO 取得現月之值
        return nowMonth;//TODO 傳回取得現月之值
    }

    public static int GetNowDay() {
        Calendar rightNow = Calendar.getInstance();//TODO 取得預設月曆物件
        int nowDay;
        nowDay = rightNow.get(Calendar.DATE);//TODO 取得今日之值
        return nowDay;//TODO 傳回取得今日之值
    }

    public static int GetNowHour() {
        Calendar rightNow = Calendar.getInstance();//TODO 取得預設月曆物件
        int nowHour;
        nowHour = rightNow.get(Calendar.HOUR_OF_DAY);//TODO 取得此時之值
        return nowHour;//TODO 傳回取得此時之值
    }

    public static int GetNowMinute() {
        Calendar rightNow = Calendar.getInstance();//TODO 取得預設月曆物件
        int nowMinute;
        nowMinute = rightNow.get(Calendar.MINUTE);//TODO 取得此分之值
        return nowMinute;//TODO 傳回取得此分之值
    }

    public static int GetNowSecond() {
        Calendar rightNow = Calendar.getInstance();//TODO 取得預設月曆物件
        int nowSecond;
        nowSecond = rightNow.get(Calendar.SECOND);//TODO 取得此秒之值
        return nowSecond;//TODO 傳回取得此秒之值
    }
}
