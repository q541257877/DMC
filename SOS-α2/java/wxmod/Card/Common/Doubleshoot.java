package wxmod.Card.Common;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import wxmod.Patches.AbstractCardEnum;

public class Doubleshoot extends CustomCard{
	
	public static final String ID = "Doubleshoot";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Doubleshoot.png";
	private static final int COST = 1;
	private List<TooltipInfo> tips;
	
	
	public Doubleshoot() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DMC,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ALL_ENEMY);
		
		this.damage =  3;
		this.baseMagicNumber = this.magicNumber = 3;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower("showtime")) {
		this.baseMagicNumber = this.magicNumber = (p.getPower("showtime").amount) / 2 + 3;}
		if(p.hasPower("EbonyIvorypower")) {
			for(int i = 0;i < this.baseMagicNumber; i++) {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.getMonsters().getRandomMonster(true), new com.megacrit.cardcrawl.cards.DamageInfo(p, 6 ,DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
			}
		}
		else{
		for(int i = 0;i < this.baseMagicNumber; i++) {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.getMonsters().getRandomMonster(true), new com.megacrit.cardcrawl.cards.DamageInfo(p, 3 ,DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
			}
		  }
		}
	
	@Override
    public AbstractCard makeCopy() {
        return new Doubleshoot();
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
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Doubleshoot");
        NAME = Doubleshoot.cardStrings.NAME;
        DESCRIPTION = Doubleshoot.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Doubleshoot.cardStrings.EXTENDED_DESCRIPTION;
    }
	
}
