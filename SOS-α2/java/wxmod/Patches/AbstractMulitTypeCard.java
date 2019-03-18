 package wxmod.Patches;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

 
 public abstract class AbstractMulitTypeCard extends CustomCard
 {
   public AbstractMulitTypeCard(String id, String name, String img, int cost, String rawDescription, CardType type,
			CardColor color, CardRarity rarity, CardTarget target)
   {
     super(id, name, img, cost, rawDescription, type, color, rarity, target);
   }
   
 
   public com.megacrit.cardcrawl.cards.AbstractCard makeCopy()
   {
     return null;
   }
   
 
 
   public void upgrade() {}
   
 
   public void use(AbstractPlayer arg0, AbstractMonster arg1) {}
   
 
public abstract void optionYamato();

public abstract void optionRebellion();

public abstract void optionGilgamesh();

public abstract void optionLucifer();

public abstract void optionEbonyIvory();

public abstract void optionCoyoteA();

public abstract void optionPandora();

public abstract void optionNeutral();

public void applyPowers() {
	super.applyPowers();
	if (AbstractDungeon.player.hasPower("Yamatopower")) {
 	 	optionYamato();
	} 
	else if (AbstractDungeon.player.hasPower("Rebellionpower")||AbstractDungeon.player.hasPower("Rebellionpower2")) {
 	 	optionRebellion();
	} 
	else if(AbstractDungeon.player.hasPower("Gilgameshpower")){
 	 	optionGilgamesh();
	}
	else if(AbstractDungeon.player.hasPower("Luciferpower")||AbstractDungeon.player.hasPower("Luciferpower2")){
 	 	optionLucifer();
	}
	else if(AbstractDungeon.player.hasPower("EbonyIvorypower")){
	 	optionEbonyIvory();
	}
	else if(AbstractDungeon.player.hasPower("CoyoteApower")){
	 	optionCoyoteA();
	}
	else if(AbstractDungeon.player.hasPower("Pandorapower")||AbstractDungeon.player.hasPower("Pandorapower2")){
	 	optionPandora();
	}
	else {
 	 optionNeutral();
	}
}



}

