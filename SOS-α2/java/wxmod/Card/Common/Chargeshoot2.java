package wxmod.Card.Common;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import wxmod.Patches.AbstractCardEnum;

public class Chargeshoot2 extends CustomCard{
	
	public static final String ID = "Chargeshoot2";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Chargeshoot.png";
	private static final int COST = 2;
	private int power;
	private List<TooltipInfo> tips;
	
	
	public Chargeshoot2() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DMC,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ALL_ENEMY);
		this.baseDamage  = this.damage = 0;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower("showtime")) {
			this.power = p.getPower("showtime").amount;
			this.baseDamage =(int)Math.round(Math.random()*(9-5)+5);
			this.damage = this.baseDamage + this.power * 2;
		}
		else {
			this.damage = (int)Math.round(Math.random()*(9-5)+5);
					}		
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage,true), DamageInfo.DamageType.NORMAL, AttackEffect.SLASH_VERTICAL));	
       }
        
        
	
	@Override
    public AbstractCard makeCopy() {
        return new Chargeshoot2();
    }
	
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
    }
    
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Chargeshoot2");
        NAME = Chargeshoot2.cardStrings.NAME;
        DESCRIPTION = Chargeshoot2.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Chargeshoot2.cardStrings.EXTENDED_DESCRIPTION;
    }
	
}
