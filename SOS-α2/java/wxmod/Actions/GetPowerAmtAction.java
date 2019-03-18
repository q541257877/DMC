package wxmod.Actions;

import com.megacrit.cardcrawl.core.AbstractCreature;

public class GetPowerAmtAction {
	public static int PowerAmt(AbstractCreature target, String PowerKeyWord) {
		if(target != null && PowerKeyWord != null) {
			if(target.hasPower(PowerKeyWord)) {
				return target.getPower(PowerKeyWord).amount;
			}
			else {
				return 0;
			}
		}
		else {
			return 0;
		}
	}

}
