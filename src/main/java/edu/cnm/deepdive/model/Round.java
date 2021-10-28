package edu.cnm.deepdive.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Round {
  private State state;
  private final List<Roll> rolls;
  private int point;

  public Round() {
    this.rolls = new LinkedList<>();
    this.state = State.initial();
  }

  public State play(Random rng) {
    do {
      Roll roll = new Roll(rng);
      addRoll(roll);
    } while(!getState().isTerminal());
    return state;
  }

  public void addRoll(Roll roll) {
    if(point == 0) {
      state = state.next(roll);
      if(state == State.POINT) {
        point = roll.getValue();
      }
    } else {
      state = state.next(roll, point);
    }
    rolls.add(roll);


  }

  public List<Roll> getRolls() {
    return Collections.unmodifiableList(rolls);
  }

  public State getState() {
    return state;
  }

  public boolean isWin() {
    return state == State.WIN;
  }

}
