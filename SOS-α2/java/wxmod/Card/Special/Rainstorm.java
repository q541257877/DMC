package wxmod.Card.Special;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import wxmod.Power.showtime;
import wxmod.Relic.SSS;

public class Rainstorm extends CustomCard{
	
	public static final String ID = "Rainstorm";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Rainstorm.png";
	private static final int COST = 1;

	
	
	public Rainstorm() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCard.CardColor.COLORLESS,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ALL_ENEMY);
		this.exhaust = true;
		this.damage =  5;
		this.baseMagicNumber = this.magicNumber = 0;
		this.isEthereal = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower("showtime")) {
			this.baseMagicNumber = this.magicNumber = p.getPower("showtime").amount * 3;}
			for(int i = 0;i < this.baseMagicNumber; i++) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.getMonsters().getRandomMonster(true), new com.megacrit.cardcrawl.cards.DamageInfo(p, 5 ,DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
			}
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new showtime(p,2), 2));
			SSS.WeaponPonit +=2;
	   }
	
	@Override
    public AbstractCard makeCopy() {
        return new Rainstorm();
    }
    
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
	
    }	

    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Rainstorm");
    	NAME = Rainstorm.cardStrings.NAME;
    	DESCRIPTION = Rainstorm.cardStrings.DESCRIPTION;
    }
	
}
