package edu.cnm.deepdive.model;

import java.util.LongSummaryStatistics;
import javax.swing.WindowConstants;

public enum State {

  COME_OUT {
    @Override
    public State next(Roll roll) {
      State next;
      int value = roll.getValue();
      if (value == 7 || value == 11) {
        next = State.WIN;
      } else if (value == 2 || value == 3 || value == 12) {
        next = State.LOSS;
      } else {
        next = State.POINT;
      }
      return next;
    }

  },

  WIN,

  LOSS,

  POINT {
    @Override
    public State next(Roll roll, int point) {
      State next = this;
      int value = roll.getValue();
      if (value == point) {
        next = State.WIN;
      } else if (value == 7) {
        next = State.LOSS;
      }
      return next;
    }

    @Override
    public State next(Roll roll) {
      throw new IllegalStateException("This method can't be invoked when in the POINT state.");
    }
  };

  public State next(Roll roll) {
    return this;

  }

  public State next(Roll roll, int point) {
    return next(roll);

  }


}
