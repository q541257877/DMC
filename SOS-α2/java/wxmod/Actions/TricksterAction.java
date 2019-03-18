package wxmod.Actions;
 
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 
public class TricksterAction extends com.megacrit.cardcrawl.actions.AbstractGameAction{
	private AbstractPlayer p;
   
	public TricksterAction() {
		this.p = AbstractDungeon.player;
		this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST;
		this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.CARD_MANIPULATION;
	}
   
	public void update() {
		if (this.duration == com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST) {
			if (this.p.hand.isEmpty()) {
				this.isDone = true;
				return; 
			}
		if (this.p.hand.size() <= 2) {
			for (AbstractCard c :this.p.hand.group){
				if (c.cost > 0) {
					c.freeToPlayOnce = true;
				}
			}
			AbstractDungeon.player.hand.refreshHandLayout();
			this.isDone = true;
			return;
		}
		AbstractDungeon.handCardSelectScreen.open("让其耗能变为0", 2, true,true);
		tickDuration();
		return;
		}
		if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
			for (AbstractCard c1 : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
				if (c1.cost > 0) {
					c1.freeToPlayOnce = true;
				}
				this.p.hand.addToTop(c1);
			}
			AbstractDungeon.player.hand.refreshHandLayout();
			AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
		}
		tickDuration();
	}
}



