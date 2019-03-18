package wxmod.Card.Rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
//import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import basemod.abstracts.CustomCard;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Power.Flex2;


public class Enemystep extends CustomCard{
	public static final String ID = "Enemystep";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Enemystep.png";
	private static final int COST = 0;
	//private EnemyMoveInfo move;
	
	
	public Enemystep() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.DMC, AbstractCard.CardRarity.RARE, 
				AbstractCard.CardTarget.ENEMY);
		
	}

	
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		//if(move.isMultiDamage == true) {
			
		//}
		if ((m != null) && ((m.intent == AbstractMonster.Intent.ATTACK) || (m.intent == AbstractMonster.Intent.ATTACK_BUFF) || (m.intent == AbstractMonster.Intent.ATTACK_DEBUFF) || (m.intent == AbstractMonster.Intent.ATTACK_DEFEND))) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new StrengthPower(p, -10), -10));
			 if ((!m.hasPower("Artifact"))) {
				 AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new GainStrengthPower(m, 10), 10));	 
			 }
				if(upgraded){
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Flex2(p, 2), 2));
				} 
		 }
		
		}
	
	
	public AbstractCard makeCopy() {
		return new Enemystep();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
		}
	}
	
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Enemystep");
        NAME = Enemystep.cardStrings.NAME;
        DESCRIPTION = Enemystep.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = Enemystep.cardStrings.UPGRADE_DESCRIPTION;
    }
	

}