package wxmod.Card.Special;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
import wxmod.Power.Catastrophepower;

public class PF262 extends CustomCard{
	
	public static final String ID = "PF262";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/PF262.png";
	private static final int COST = 0;

	
	
	public PF262() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCard.CardColor.COLORLESS,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ALL_ENEMY);
		this.exhaust = true;
		this.damage =  3;
		this.baseMagicNumber = 0;
		this.isEthereal = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower("showtime")) {
		this.baseMagicNumber = this.magicNumber = p.getPower("showtime").amount;}
		for(int i = 0;i < this.baseMagicNumber; i++) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(p, 3 ,DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		}
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Catastrophepower(p,2), 2));
	   }
	
	@Override
    public AbstractCard makeCopy() {
        return new PF262();
    }
    
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
	
    }	

    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("PF262");
    	NAME = PF262.cardStrings.NAME;
    	DESCRIPTION = PF262.cardStrings.DESCRIPTION;
    }
	
}
