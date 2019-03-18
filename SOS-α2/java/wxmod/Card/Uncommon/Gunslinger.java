package wxmod.Card.Uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.Gunslingerpower;
import wxmod.Relic.SSS;

public class Gunslinger extends CustomCard{
	
	public static final String ID = "Gunslinger";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;	
	public static final String IMG_PATH = "img/cards/Gunslinger.png";
	private static final int COST = 2;

	
	
	public Gunslinger() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DMC,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		

	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		SSS.WeaponPonit = 0;
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Gunslingerpower(p,1), 1));					    
		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.animations.VFXAction(p, new com.megacrit.cardcrawl.vfx.combat.InflameEffect(p), 1.0F));
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Gunslinger();
    }
    
    public void upgrade() {
    	
        if (!this.upgraded) {        
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
	
    }	

    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Gunslinger");
    	NAME = Gunslinger.cardStrings.NAME;
    	DESCRIPTION = Gunslinger.cardStrings.DESCRIPTION;
    }
	
}
