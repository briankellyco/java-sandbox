package co.bk.java8mooc;

import java.util.function.Consumer;

class ConsumerClassExample implements Consumer<Long> {
  public void accept(Long t) {
    System.out.println(t*t);
  }
}