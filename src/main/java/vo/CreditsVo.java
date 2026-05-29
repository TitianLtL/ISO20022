package vo;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class CreditsVo {
	private Set<CreditVo> vos = new HashSet<CreditVo>();
	private BigDecimal lumpsum = BigDecimal.ZERO;

	public Set<CreditVo> getVos() {
		return vos;
	}
	
	
	public void addCreditVo(CreditVo vo) {
		vos.add(vo);
	}

	public BigDecimal getLumpsum() {
		for (CreditVo vo : vos) {
			String amount = vo.getAmount();
			lumpsum = lumpsum.add(new BigDecimal(amount));
		}
		return lumpsum;
	}
}
