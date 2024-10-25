//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Election.java
// Course: CS 300 Fall 2024
//
// Author: Tristin Yun
// Email: tyun7@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: NOBODY
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * This class can be instantiated to represent an election; it keeps tracks of a list of Candidate
 * objects running for a seat and other attributes related to the election
 */
public class Election {
  // initialize fields
  private Candidate[] candidates; // compact && oversize array of candidates
  private int numCandidates = 0; // # of candidates in the election
  public final String SEAT_NAME; // name of the seat that the election is for

  /**
   * Constructor method that initializes the election's oversize array and declares the name of the
   * seat
   * 
   * @param seatName      the name of the position the candidates are running for
   * @param maxCandidates the maximum allowed of candidates to run in the election
   * @throws IllegalArgumentException if maxCandidates is not > 0
   */
  public Election(String seatName, int maxCandidates) {
    if (!(maxCandidates > 0)) {
      throw new IllegalArgumentException("max Candidates is not greater than 0");
    }
    this.SEAT_NAME = seatName;
    candidates = new Candidate[maxCandidates];
  }

  /**
   * Accessor method that returns the # of candidates in election
   * 
   * @return the number of candidates running in this election
   */
  public int getNumCandidates() {
    return numCandidates;
  }

  /**
   * Accessor method that returns the maximum # of candidates in election
   * 
   * @return the max # of candidates allowed to be in election
   */
  public int capacity() {
    return this.candidates.length;
  }

  /**
   * Mutator method that adds candidate to the end of the candidates election list (not
   * alphabetical)
   * 
   * @param candidate the candidate to be added
   * @throws IllegalArgumentException if the candidate is already in the list
   */
  public void addCandidate(Candidate candidate) {
    // iterate to see if the candidate list already contains the candidate
    for (Candidate c : candidates) {
      if (c != null) {
        if (c.equals(candidate)) {
          throw new IllegalArgumentException("Candidate is already in the list");
        }
      }
    }
    // Find where the first null is, and replace it with the new candidate
    for (int i = 0; i < candidates.length; i++) {
      if (candidates[i] == null) {
        candidates[i] = new Candidate("placer", "placer");
        candidates[i] = candidate;
        numCandidates++;
        return;
      }
    }
  }

  /**
   * Mutator method that removes the desire candidate from list
   * 
   * @param candidate the candidate to be removed
   * @throws IllegalArgumentException if trying to remove from an empty candidates list
   * @throws NoSuchElementException   if the candidate is not in the list
   */
  public void removeCandidate(Candidate candidate) {
    Candidate[] tempCandidates = Arrays.copyOf(candidates, candidates.length);
    // checks that the list is not empty
    if (numCandidates > 0) {
      // iterates through list and removes if it is present
      for (int i = 0; i < candidates.length; i++) {
        if (candidates[i] != null) {
          if (candidates[i].equals(candidate)) {
            for (int j = i; j < candidates.length - 1; j++) {
              // we want to drop a candidate and moving forward
              if (tempCandidates[j + 1] != null) { // as long as the Candidate at the current index
                // +1 isnt null, we can replace the current Candidate with it; essentially bumps
                // all of the candidates down a spot.

                candidates[j] = tempCandidates[j + 1];
              } else {
                // otherwise, the next array must be null so we replace the current index
                // with null
                candidates[j] = null;
              }
            }

            // since we can't use the above loop to update the very last array in the 2d array,
            // since no array follows it, we manually change it to null
            candidates[candidates.length - 1] = null;

            numCandidates--;
            return;
          }
        }
        if (i == candidates.length - 1) {
          throw new NoSuchElementException("The candidate is not in the list");
        }
      }
    } else {
      throw new IllegalArgumentException("the candidates list is empty");
    }
  }

  /**
   * Determines the candidate with > 50% votes and returns a reference to it
   * 
   * @throws IllegalArgumentException if there is empty candidates list
   * @throws NoSuchElementException   if there is a contingent election
   * @return a reference to the Candidate with >50% of votes
   */
  public Candidate findWinner() {
    int totalVotes = 0;
    double votePortion = 0;
    if (candidates.length > 0) {
      // iterates through list and gets total votes
      for (int i = 0; i < candidates.length; i++) {
        if (candidates[i] != null) { // make sure not a null candidate
          totalVotes += candidates[i].getNumVotes();
        }
      }
      for (int i = 0; i < candidates.length; i++) {
        if (candidates[i] != null) { // make sure not a null candidate
          votePortion = (double) candidates[i].getNumVotes() / totalVotes;
          if (votePortion > 0.5) {
            return candidates[i];
          }
        }
      }
      throw new NoSuchElementException("contingent election");
    } else {
      throw new IllegalArgumentException("the candidates list is empty");
    }
  }

  /**
   * Increases the number of votes of a candidate by 1
   * 
   * @param candidate the candidate to add a vote to
   * @throws NoSuchElementException if the candidate is not in the election list
   */
  public void vote(Candidate candidate) {
    // iterates through list and adds a vote to the desired candidate
    for (int i = 0; i < candidates.length; i++) {
      if (candidates[i] != null) {
        if (candidates[i].equals(candidate)) {
          candidates[i].addVote();
          return;
        }
      }
    }
    throw new NoSuchElementException("Candidate is not in this election");
  }

  /**
   * Creates and returns a String representation of this Election object, with each Candidate's
   * string representation on different lines. The first line contains the name of the seat.
   * 
   * @return the String representation of the current Election, NOT ending with a new line
   */
  @Override
  public String toString() {
    String thisElection = "" + this.SEAT_NAME;
    // iterates through the candidates and, if they are not null, add them
    // to the thisElection string representation
    for (int i = 0; i < candidates.length; i++) {
      if (candidates[i] != null) {
        thisElection += "\n" + candidates[i];
      }
    }
    return thisElection;
  }

  /**
   * Determines whether the given object is equivalent to this election. If anObject is not an
   * Election object, they are not equal. If it is an election object, they are equal iff their seat
   * names match, ignoring capitalization.
   * 
   * @param anObject the object to compare to this election object
   * @return true if the given object is equivalent based on the described conditions
   */
  @Override
  public boolean equals(Object anObject) {
    if (anObject instanceof Election) {
      Election otherElection = (Election) anObject;
      return this.SEAT_NAME.equalsIgnoreCase(otherElection.SEAT_NAME);
    }
    return false;
  }


}
