package com.satesh.blackjack.model;

import static java.util.Objects.nonNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.satesh.blackjack.view.Card;

/**
 * Represents a deck of cards for use by a particular game.
 *
 * @see DeckFactory#createDeck()
 */
final class Deck {
   private final Queue<Card> cards;

   /**
    * @param cards
    *           the contents of the deck, in the order they are to be dealt
    */
   Deck(List<Card> cards) {
      this.cards = new LinkedList<>(cards);
   }

   /**
    * Removes and returns the next {@code Card}.
    *
    * @throws IllegalStateException
    *            if the deck is empty
    */
   Card deal() {
      Card next = poll();
      if (nonNull(next)) {
         return next;
      } else {
         throw new IllegalStateException("deck is empty");
      }
   }

   private Card poll() {
      synchronized (cards) {
         return cards.poll();
      }
   }
}
