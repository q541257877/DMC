package wxmod.Characters;

import basemod.abstracts.*;
import java.util.*;
import com.megacrit.cardcrawl.unlock.*;
import com.megacrit.cardcrawl.screens.*;
import com.megacrit.cardcrawl.characters.*;
import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeDur;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeIntensity;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.utility.ExhaustAllEtherealAction;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import wxmod.Patches.AbstractCardEnum;
import wxmod.Patches.CharacterEnum;
import wxmod.WxMod;
import wxmod.Card.Common.Rebellion;

public class Dante extends CustomPlayer {

	private static final int ENERGY_PER_TURN = 3;
	private static final int ASCENSION_MAX_HP_LOSS = 5;
	private static final String[] orbTextures;



	static {
		
		orbTextures = new String[] { "img/character/orb/enabled/layer1.png", "img/character/orb/enabled/layer2.png", "img/character/orb/enabled/layer3.png", "img/character/orb/enabled/layer4.png", "img/character/orb/enabled/layer5.png", "img/character/orb/enabled/layer6.png", "img/character/orb/disabled/layer1d.png", "img/character/orb/disabled/layer2d.png", "img/character/orb/disabled/layer3d.png", "img/character/orb/disabled/layer4d.png", "img/character/orb/disabled/layer5d.png" };

	}
	
	public Dante(final String name) {
		super(name, CharacterEnum.DANTE, orbTextures, "img/character/orb/vfx.png",  (String)null, (String)null);

		this.dialogX = (this.drawX + 0.0F * Settings.scale);
		this.dialogY = (this.drawY + 220.0F * Settings.scale);

		this.initializeClass(wxmod.Patches.GetCharacterPicture.CharacterPicture(),
				WxMod.makePath(WxMod.MAGES_SHOULDER_2),
				WxMod.makePath(WxMod.MAGES_SHOULDER_1),
				WxMod.makePath(WxMod.MAGES_CORPSE),
				getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

	}

	
	
	@Override
    public void applyEndOfTurnTriggers() {
        for (final AbstractPower p : this.powers) {
            p.atEndOfTurn(true);
        }
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ExhaustAllEtherealAction());
    }

	@Override
	public ArrayList<String> getStartingDeck() {
		final ArrayList<String> retVal = new ArrayList<String>();
		retVal.add("Attack_a");
		retVal.add("Attack_a");
		retVal.add("Attack_b");
		//retVal.add("Straight");
		retVal.add("Shoot");
		retVal.add("Shoot");
		//retVal.add("Risingdragon");
		retVal.add("Provocation");
		//retVal.add("Provocation");
		//retVal.add("Provocation");
		retVal.add("Defend_Dante");
		retVal.add("Defend_Dante");
		retVal.add("Defend_Dante");
		retVal.add("Defend_Dante");
		retVal.add("Redorb");
		//retVal.add("Darkslayer");
		//retVal.add("Rebellion");
		//retVal.add("Gilgamesh");
		//retVal.add("Pandora");
		//retVal.add("Pandora");
		return retVal;
	}

	@Override
	public ArrayList<String> getStartingRelics() {
		final ArrayList<String> retVal = new ArrayList<String>();
		retVal.add("SSS");
		UnlockTracker.markRelicAsSeen("SSS");
		retVal.add("Combocheck");
		UnlockTracker.markRelicAsSeen("Combocheck");
		return retVal;
	}

	@Override
	public CharSelectInfo getLoadout() {
        String title;
        String flavor;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            title = "恶魔猎人";
            flavor = "传说中风骚又强大的斯巴达之子，恶魔猎人但丁。 NL 强不强不知道，但一定够骚（泥垢了";
        }
        else if (Settings.language == Settings.GameLanguage.ZHT) {
            title = "恶魔猎人";
            flavor = "传说中风骚又强大的斯巴达之子，恶魔猎人但丁。 NL 强不强不知道，但一定够骚（泥垢了";
        }
        else {
            title = "Devil Hunter";
            flavor = "The Son of Sparda,from Devil May Cry4.";
        }
		return new CharSelectInfo(title, flavor,
				80, 80, 0, 99, 5,
				(AbstractPlayer)this, getStartingRelics(), getStartingDeck(), false);
	}

	@Override
	public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_HEAVY", MathUtils.random(-0.2f, 0.2f));
        CardCrawlGame.screenShake.shake(ShakeIntensity.MED, ShakeDur.SHORT, true);
	}


	@Override
	public int getAscensionMaxHPLoss() {	
		return ASCENSION_MAX_HP_LOSS;
	}

	@Override
	public CardColor getCardColor() {
		return AbstractCardEnum.DMC;
	}

	@Override
	public int getCardCount() {
		return 0;
	}


	@Override
	public Color getCardRenderColor() {
		return WxMod.SPARDA;
	}

	@Override
	public Color getCardTrailColor() {
		return WxMod.SPARDA;
	}


	@Override
	public String getCustomModeCharacterButtonSoundKey() {
		return "ATTACK_HEAVY";
	}



    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }



	@Override
	public AbstractPlayer newInstance() {
		return new Dante(this.name);
    }
	
	@Override
	public String getLocalizedCharacterName() {
        String title;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            title = "恶魔猎人";
        }
        else if (Settings.language == Settings.GameLanguage.ZHT) {
            title = "恶魔猎人";
        }
        else {
            title = "Devil Hunter";
        }
		return title;
	}


	@Override
	public int getSeenCardCount() {
		return 0;
	}

	@Override
	public Color getSlashAttackColor() {
		return WxMod.SPARDA;
	}

	@Override
	public AttackEffect[] getSpireHeartSlashEffect() {
		return new AbstractGameAction.AttackEffect[] { AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL };
	}

	@Override
	public String getSpireHeartText() {
		return "我还没玩够呢~";
	}

	@Override
	public AbstractCard getStartCardForEvent() {
		return (AbstractCard)new Rebellion();
	}


	@Override
	public String getTitle(final AbstractPlayer.PlayerClass arg0) {
        String title;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            title = "恶魔猎人";
        }
        else if (Settings.language == Settings.GameLanguage.ZHT) {
            title = "恶魔猎人";
        }
        else {
            title = "Devil Hunter";
        }
		return title;
	}


	@Override
	public String getVampireText() {
		title = "斯巴达之子，您应该不需要我们的帮助，不过如果你想的话....";
		return title;
	}


	

}
