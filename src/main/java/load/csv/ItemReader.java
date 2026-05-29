package load.csv;

import util.AbstractKeyWord;
import vo.CreditVo;

public class ItemReader extends AbstractKeyWord {
	public static CreditVo reader(String line) {
		String[] records = line.split(keyWord.getRecordSeparator());
		CreditVo vo = new CreditVo(records);
		return vo;
	}
	public static void print (String[] records){
		for (String record : records){
			System.out.println(record);
		}
	}

}
