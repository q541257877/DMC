package wxmod.Card.Common;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import wxmod.Patches.AbstractCardEnum;

public class Chargeshoot extends CustomCard{
	
	public static final String ID = "Chargeshoot";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Chargeshoot.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 9;
	private int power;
	private List<TooltipInfo> tips;
	
	
	public Chargeshoot() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DMC,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
		this.baseDamage  = ATTACK_DMG;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower("showtime")) {
			this.power = p.getPower("showtime").amount;
		if(p.hasPower("EbonyIvorypower")) {
			this.baseDamage = this.baseDamage + this.power * 3 + 3;
			}
		else{
			this.baseDamage = this.baseDamage + this.power * 3;
			}
		}
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.baseDamage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));	
       }
        
        
	
	@Override
    public AbstractCard makeCopy() {
        return new Chargeshoot();
    }
	
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
    }
    
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Chargeshoot");
        NAME = Chargeshoot.cardStrings.NAME;
        DESCRIPTION = Chargeshoot.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Chargeshoot.cardStrings.EXTENDED_DESCRIPTION;
    }
	
}
