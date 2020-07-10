package com.link.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomUtils
{
	public static String			beginDate	= "20060101010101";
	public static String			endDate		= "20160331010101";
	public static DecimalFormat		DF			= new DecimalFormat("############");
	public static SimpleDateFormat	format2		= new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat	format3		= new SimpleDateFormat("yyyy");
	public static SimpleDateFormat	format1		= new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat	formatlong	= new SimpleDateFormat("yyyyMMddHHmmss");
	public static Random			randomlong	= new Random();
	public static SimpleDateFormat	format		= new SimpleDateFormat("yyyy-MM-dd");
	public static Random			random		= new Random();
	public static String[]			Surname		= { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒", "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季", "麻", "强", "贾", "路", "娄", "危", "江", "童", "颜", "郭", "梅", "盛", "林", "刁", "钟", "徐", "邱", "骆", "高", "夏", "蔡", "田", "樊", "胡", "凌", "霍", "虞", "万", "支", "柯", "昝", "管", "卢", "莫", "经", "房", "裘", "缪", "干", "解", "应", "宗", "丁", "宣", "贲", "邓", "郁", "单", "杭", "洪", "包", "诸", "左", "石", "崔", "吉", "钮", "龚", "程", "嵇", "邢", "滑", "裴", "陆", "荣", "翁", "荀", "羊", "于", "惠", "甄", "曲", "家", "封", "芮", "羿", "储", "靳", "汲", "邴", "糜", "松", "井", "段", "富", "巫", "乌", "焦", "巴", "弓", "牧", "隗", "山", "谷", "车", "侯", "宓", "蓬", "全", "郗", "班", "仰", "秋", "仲", "伊", "宫", "宁", "仇", "栾", "暴", "甘", "钭", "厉", "戎", "祖", "武", "符", "刘", "景", "詹", "束", "龙", "叶", "幸", "司", "韶", "郜", "黎", "蓟", "溥", "印", "宿", "白", "怀", "蒲", "邰", "从", "鄂", "索", "咸", "籍", "赖", "卓", "蔺", "屠", "蒙", "池", "乔", "阴", "郁", "胥", "能", "苍", "双", "闻", "莘", "党", "翟", "谭", "贡", "劳", "逄", "姬", "申", "扶", "堵", "冉", "宰", "郦", "雍", "却", "璩", "桑", "桂", "濮", "牛", "寿", "通", "边", "扈", "燕", "冀", "浦", "尚", "农", "温", "别", "庄", "晏", "柴", "瞿", "阎", "充", "慕", "连", "茹", "习", "宦", "艾", "鱼", "容", "向", "古", "易", "慎", "戈", "廖", "庾", "终", "暨", "居", "衡", "步", "都", "耿", "满", "弘", "匡", "国", "文", "寇", "广", "禄", "阙", "东", "欧", "殳", "沃", "利", "蔚", "越", "夔", "隆", "师", "巩", "厍", "聂", "晁", "勾", "敖", "融", "冷", "訾", "辛", "阚", "那", "简", "饶", "空", "曾", "毋", "沙", "乜", "养", "鞠", "须", "丰", "巢", "关", "蒯", "相", "查", "后", "荆", "红", "游", "郏", "竺", "权", "逯", "盖", "益", "桓", "公", "仉", "督", "岳", "帅", "缑", "亢", "况", "郈", "有", "琴", "归", "海", "晋", "楚", "闫", "法", "汝", "鄢", "涂", "钦", "商", "牟", "佘", "佴", "伯", "赏", "墨", "哈", "谯", "篁", "年", "爱", "阳", "佟", "言", "福", "南", "火", "铁", "迟", "漆", "官", "冼", "真", "展", "繁", "檀", "祭", "密", "敬", "揭", "舜", "楼", "疏", "冒", "浑", "挚", "胶", "随", "高", "皋", "原", "种", "练", "弥", "仓", "眭", "蹇", "覃", "阿", "门", "恽", "来", "綦", "召", "仪", "风", "介", "巨", "木", "京", "狐", "郇", "虎", "枚", "抗", "达", "杞", "苌", "折", "麦", "庆", "过", "竹", "端", "鲜", "皇", "亓", "老", "是", "秘", "畅", "邝", "还", "宾", "闾", "辜", "纵", "侴", "万俟", "司马", "上官", "欧阳", "夏侯", "诸葛", "闻人", "东方", "赫连", "皇甫", "羊舌", "尉迟", "公羊", "澹台", "公冶", "宗正", "濮阳", "淳于", "单于", "太叔", "申屠", "公孙", "仲孙", "轩辕", "令狐", "钟离", "宇文", "长孙", "慕容", "鲜于", "闾丘", "司徒", "司空", "兀官", "司寇", "南门", "呼延", "子车", "颛孙", "端木", "巫马", "公西", "漆雕", "车正", "壤驷", "公良", "拓跋", "夹谷", "宰父", "谷梁", "段干", "百里", "东郭", "微生", "梁丘", "左丘", "东门", "西门", "南宫", "第五", "公仪", "公乘", "太史", "仲长", "叔孙", "屈突", "尔朱", "东乡", "相里", "胡母", "司城", "张廖", "雍门", "毋丘", "贺兰", "綦毋", "屋庐", "独孤", "南郭", "北宫", "王孙" };

	public static String getRandomNumber(int paramInt) {
		String str = randomString("0123456789", paramInt);
		return str;
	}

	public static String randomDateLong() {
		try
		{
			Date localDate1 = formatlong.parse(beginDate);
			Date localDate2 = formatlong.parse(endDate);
			if (localDate1.getTime() >= localDate2.getTime())
				return formatlong.format(localDate1);
			long l = random(localDate1.getTime(), localDate2.getTime());
			return formatlong.format(Long.valueOf(l));
		}
		catch (Exception localException)
		{
			localException.printStackTrace();
		}
		return "";
	}

	public static String getSeq(int paramInt1, int paramInt2) {
		StringBuilder localStringBuilder = new StringBuilder();
		String str = String.valueOf(paramInt1);
		localStringBuilder.append("1");
		for (int i = str.length() + 1; i < paramInt2; i++)
			localStringBuilder.append("0");
		localStringBuilder.append(str);
		return localStringBuilder.toString();
	}

	public static void main(String[] paramArrayOfString) {
		int i = 0;
		while (i < 10000)
		{
			System.out.println(randomDate("20100101", "20160409"));
			i += 100;
		}
	}

	public static boolean randomBoolean() {
		return randomInt(2) != 0;
	}

	public static char randomChar(String paramString) {
		return paramString.charAt(randomInt(paramString.length()));
	}

	public static int randomInt(int paramInt) {
		return (int) (Math.random() * paramInt);
	}

	public static int randomIntBetween(int paramInt1, int paramInt2) {
		return paramInt1 + randomInt(paramInt2 - paramInt1);
	}

	public static float getRandomFloat(int paramInt1, int paramInt2) {
		String str1 = getRandomNumber(paramInt1);
		String str2 = getRandomNumber(paramInt2);
		String str3 = new StringBuilder().append(str1).append(".").append(str2).toString();
		return Float.parseFloat(str3);
	}

	public static String getRandomFloatString(int paramInt1, int paramInt2) {
		String str1 = getRandomNumber(paramInt1);
		String str2 = getRandomNumber(paramInt2);
		String str3 = new StringBuilder().append(DF.format(Float.parseFloat(str1))).append(".").append(str2).toString();
		return str3;
	}

	public static String randomString(String paramString, int paramInt) {
		StringBuffer localStringBuffer = new StringBuffer();
		for (int i = 0; i < paramInt; i++)
			localStringBuffer.append(randomChar(paramString));
		return localStringBuffer.toString();
	}

	public static String getRandomString(String paramString, int paramInt1, int paramInt2) {
		int i = randomIntBetween(paramInt1, paramInt2);
		StringBuffer localStringBuffer = new StringBuffer();
		for (int j = 0; j < i; j++)
			localStringBuffer.append(randomChar(paramString));
		return localStringBuffer.toString();
	}

	public static String randomDate(String paramString1, String paramString2) {
		try
		{
			Date localDate1 = format2.parse(paramString1);
			Date localDate2 = format2.parse(paramString2);
			if (localDate1.getTime() >= localDate2.getTime())
				return format2.format(localDate1);
			long l = random(localDate1.getTime(), localDate2.getTime());
			return format2.format(Long.valueOf(l));
		}
		catch (Exception localException)
		{
			localException.printStackTrace();
		}
		return "";
	}

	public static String randomDateShort(String paramString1, String paramString2) {
		try
		{
			Date localDate1 = format3.parse(paramString1);
			Date localDate2 = format3.parse(paramString2);
			if (localDate1.getTime() >= localDate2.getTime())
				return format3.format(localDate1);
			long l = random(localDate1.getTime(), localDate2.getTime());
			return format3.format(Long.valueOf(l));
		}
		catch (Exception localException)
		{
			localException.printStackTrace();
		}
		return "";
	}

	public static String randomOrg() {
		StringBuffer localStringBuffer = new StringBuffer();
		localStringBuffer.append(randomChar("123456789"));
		localStringBuffer.append("0");
		localStringBuffer.append("0");
		localStringBuffer.append(randomChar("0123456789"));
		localStringBuffer.append("0");
		localStringBuffer.append("0");
		localStringBuffer.append(randomChar("0123456789"));
		localStringBuffer.append(randomChar("0123456789"));
		localStringBuffer.append(randomChar("0123456789"));
		return localStringBuffer.toString();
	}

	public static String getRandomPhonenumber() {
		StringBuffer localStringBuffer = new StringBuffer();
		localStringBuffer.append("0");
		localStringBuffer.append(getRandomNumber(4));
		localStringBuffer.append("/");
		localStringBuffer.append(getRandomNumber(7));
		return localStringBuffer.toString();
	}

	public static String getRandomMobilnumber() {
		StringBuffer localStringBuffer = new StringBuffer();
		localStringBuffer.append("1");
		localStringBuffer.append(getRandomNumber(2));
		localStringBuffer.append(getRandomNumber(8));
		return localStringBuffer.toString();
	}

	public static String getRandomFaxnumber(String paramString) {
		String str1 = paramString.substring(0, paramString.length() - 2);
		String str2 = paramString.substring(paramString.length() - 2, paramString.length());
		Integer localInteger = new Integer(str2);
		int i = localInteger.intValue() + 1;
		str1.concat(new StringBuilder().append("").append(i).toString());
		return str1;
	}

	public static String getRandomEmail() {
		StringBuffer localStringBuffer = new StringBuffer();
		String str1 = randomString("abcdefghijklmnopqrstuvwxyz0123456789", randomInt(20) + 1);
		String str2 = randomString("abcdefghijklmnopqrstuvwxyz", randomInt(12) + 1);
		String str3 = randomString("abcdefghijklmnopqrstuvwxyz", 2);
		localStringBuffer.append(str1);
		localStringBuffer.append("@");
		localStringBuffer.append(str2);
		localStringBuffer.append(".");
		localStringBuffer.append(str3);
		return localStringBuffer.toString();
	}

	public static String getRandomWebsite() {
		String str1 = null;
		StringBuffer localStringBuffer = new StringBuffer();
		String str2 = "http://www";
		String str3 = randomString("abcdefghijklmnopqrstuvwxyz", randomInt(12) + 1);
		String str4 = randomString("abcdefghijklmnopqrstuvwxyz", 2);
		localStringBuffer.append(str2);
		localStringBuffer.append(".");
		localStringBuffer.append(str3);
		localStringBuffer.append(".");
		localStringBuffer.append(str4);
		str1 = localStringBuffer.toString();
		return str1;
	}

	public static String getRandomPassword(int paramInt) {
		String str = randomString("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", paramInt);
		return str;
	}

	public static String getChinaName() {
		int i = random.nextInt(Surname.length - 1);
		String str = Surname[i];
		if (random.nextBoolean())
			str = new StringBuilder().append(str).append(getChinaWord()).append(getChinaWord()).toString();
		else
			str = new StringBuilder().append(str).append(getChinaWord()).toString();
		return str;
	}

	public static String getChinaWord() {
		String str = null;
		int i = 176 + Math.abs(random.nextInt(71));
		int j = 161 + Math.abs(random.nextInt(94));
		byte[] arrayOfByte = new byte[2];
		arrayOfByte[0] = new Integer(i).byteValue();
		arrayOfByte[1] = new Integer(j).byteValue();
		try
		{
			str = new String(arrayOfByte, "GB2312");
		}
		catch (Exception localException)
		{
			localException.printStackTrace();
		}
		return str;
	}

	private static long random(long paramLong1, long paramLong2) {

		long l = paramLong1 + (long) (Math.random() * (paramLong2 - paramLong1));

		if ((l == paramLong1) || (l == paramLong2))
			return random(paramLong1, paramLong2);
		return l;
	}

	public static String getRandomName_bk() {
		StringBuffer localStringBuffer = new StringBuffer();
		localStringBuffer.append(randomChar("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
		localStringBuffer.append(getRandomString("abcdefghijklmnopqrstuvwxyz", 1, 4));
		localStringBuffer.append(" ");
		localStringBuffer.append(randomChar("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
		localStringBuffer.append(getRandomString("abcdefghijklmnopqrstuvwxyz", 1, 8));
		return localStringBuffer.toString();
	}
}
