package com.gamelabgraz.jam.tpbjg.items.implementation;

import java.util.LinkedList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.gamelabgraz.jam.tpbjg.Player;
import com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame;
import com.gamelabgraz.jam.tpbjg.items.IItemAction;

public class CometAction implements IItemAction {

  final float speedfactor = 2;

  private final LinkedList<Float> speedDiffs = new LinkedList<Float>();

  private Sound sound = null;

  public CometAction() {
    try {
      sound = new Sound("assets/sounds/speed.wav");
    } catch (SlickException e) {
      e.printStackTrace();
    }
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.items.IItemAction#process(com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame)
   */
  @Override
  public void startEffect(ThePeanutButterJellyGame game, Player effector) {
    if (sound != null)
      sound.play();
    float normalspeed = effector.getSpeed();
    final float speedDiff = normalspeed * (speedfactor - 1);
    effector.setSpeed(effector.getSpeed() + speedDiff);
    speedDiffs.add(speedDiff);
  }

  /**
   * {@inheritDoc}
   * 
   * @see com.gamelabgraz.jam.tpbjg.items.IItemAction#endEffect(com.gamelabgraz.jam.tpbjg.ThePeanutButterJellyGame,
   *      com.gamelabgraz.jam.tpbjg.Player)
   */
  @Override
  public void endEffect(ThePeanutButterJellyGame game, Player effector) {
    effector.setSpeed(effector.getSpeed() - speedDiffs.poll());
  }

}
