package no.oslomet.cs.algdat.Oblig3;

////////////////// ObligSBinTre /////////////////////////////////

import java.util.*;



/*
Gruppemedlemmer:    S331398 - Anders Oeyrehagen
                    S330474 - Anders Magnus Ytterboee
                    S330473 - Tommy Grut
                    S331386 - Tobias Dyre Evju
                    S331359 - Theodor Fredheim Aandal

 */

public class ObligSBinTre<T> implements Beholder<T>
{
  private static final class Node<T>   // en indre nodeklasse
  {
    private T verdi;                   // nodens verdi
    private Node<T> venstre, høyre;    // venstre og høyre barn
    private Node<T> forelder;          // forelder

    // konstruktør
    private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder)
    {
      this.verdi = verdi;
      venstre = v; høyre = h;
      this.forelder = forelder;
    }

    private Node(T verdi, Node<T> forelder)  // konstruktør
    {
      this(verdi, null, null, forelder);
    }

    @Override
    public String toString(){
        return "" + verdi;
    }

  } // class Node

  private Node<T> rot;                            // peker til rotnoden
  private int antall;                             // antall noder
  private int endringer;                          // antall endringer

  private final Comparator<? super T> comp;       // komparator

  public ObligSBinTre(Comparator<? super T> c)    // konstruktør
  {
    rot = null;
    antall = 0;
    comp = c;
  }
  
  @Override
  public boolean leggInn(T verdi) {

    Objects.requireNonNull(verdi);


    Node<T> p = rot;    // p starter i roten
    Node<T> q = null;   // hjelpevariabel
    int cmp = 0;

    while (p != null){       // fortsetter til p er ute av treet

      q = p;                                 // q er forelder til p
      cmp = comp.compare(verdi, p.verdi);    // bruker komparatoren
      p = cmp < 0 ? p.venstre : p.høyre;     // flytter p

    }


    // p er nå null, dvs. ute av treet, q er den siste vi passerte

    p = new Node<>(verdi, q);                   // oppretter en ny node

    if (q == null) {
      rot = p;                  // p blir rotnode
      rot.forelder = null;
    }
    else if (cmp < 0){
      q.venstre = p;         // venstre barn til q
    }
    else{
      q.høyre = p;                        // høyre barn til q
    }

    antall++;                                // én verdi mer i treet
    return true;                             // vellykket innlegging

  }
  
  @Override
  public boolean inneholder(T verdi)
  {
    if (verdi == null) return false;

    Node<T> p = rot;

    while (p != null)
    {
      int cmp = comp.compare(verdi, p.verdi);
      if (cmp < 0) p = p.venstre;
      else if (cmp > 0) p = p.høyre;
      else return true;
    }

    return false;
  }
  
  @Override
  public boolean fjern(T verdi) {

      if (verdi == null) {
          return false;  // treet har ingen nullverdier
      }

      Node<T> p = rot;
      Node<T> q = null;   // q skal være forelder til p

      while (p != null){            // leter etter verdi

          int cmp = comp.compare(verdi,p.verdi);      // sammenligner
          if (cmp < 0) {
              q = p;
              p = p.venstre; // går til venstre
          }
          else if (cmp > 0) {
              q = p;
              p = p.høyre; // går til høyre
          }

          else break;    // den søkte verdien ligger i p
      }

      if (p == null){
          return false;   // finner ikke verdi

      }

      if (p.venstre == null || p.høyre == null){  // Tilfelle 1) og 2)

          Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn

          if (p == rot) {
             rot = b;
          }

          else if (p == q.venstre) {
              q.venstre = b;
              p = null;
              if(b != null) {
                  b.forelder = q;
              }
          }

          else{
              q.høyre = b;
              if(b != null) {
                  b.forelder = q;
              }
          }
      }
      else  // Tilfelle 3)
      {
          Node<T> s = p;
          Node<T> r = p.høyre;   // finner neste i inorden

          while (r.venstre != null) {

              s = r;    // s er forelder til r
              r = r.venstre;
          }

          p.verdi = r.verdi;   // kopierer verdien i r til p

          if (s != p){
              s.venstre = r.høyre;
              if(s.venstre != null){
                  s.venstre.forelder = s;
              }

          }
          else{
              s.høyre = r.høyre;
              if(s.venstre != null) {
                  s.høyre.forelder = s;
              }
          }
      }

      antall--;   // det er nå én node mindre i treet
      return true;
  }

  
  public int fjernAlle(T verdi) {
      if(tom()){
          return 0;
      }
      int antallFjernet = 0;
      while(fjern(verdi)){
          antallFjernet++;
      }
      return antallFjernet;
  }
  
  @Override
  public int antall()
  {
    return antall;
  }
  
  public int antall(T verdi) {

    if (verdi == null) {
      return 0;
    }

    int antallAvVerdi = 0;
    Node node = rot;


    java.util.Deque<Node> fifo_queue = new java.util.ArrayDeque<>();
    fifo_queue.addFirst(node);

    while(fifo_queue.size() > 0) {
      Node current = fifo_queue.removeLast();

      if(current.verdi == verdi){
        antallAvVerdi++;
      }

      if (current.venstre != null) {
        fifo_queue.addFirst(current.venstre);
      }
      if (current.høyre != null) {
        fifo_queue.addFirst(current.høyre);
      }
    }

    return antallAvVerdi;

  }
  
  @Override
  public boolean tom()
  {
    return antall == 0;
  }
  
  @Override
  public void nullstill() {

      //traverserer gjennom treet ved hjelp av postorden og fjerner alle verdiene og referansene.

      if(tom()){
          return;

      }
      else {

          deleteBTreePostOrder(rot);
      }
  }

    public void deleteBTreePostOrder(Node node) {
        if (node == null) {
            return;
        }
        else {
            deleteBTreePostOrder(node.venstre);
            deleteBTreePostOrder(node.høyre);
            node.forelder = null;
            node.venstre = null;
            node.høyre = null;
            node.verdi = null;
            if(node == rot) {
                rot = null;
            }
            antall--;

        }
    }
  
  private static <T> Node<T> nesteInorden(Node<T> p) {



    if (p.høyre != null)           // p har høyre subtre
    {
      p = p.høyre;

      while(p.venstre != null){
          p=p.venstre;
      }
    }

    else                           // p har ikke høyre subtre
    {
      while (p.forelder != null && p == p.forelder.høyre)
      {
        p = p.forelder;            // fortsetter opp mot venstre
      }
      p = p.forelder;              // nå er p den neste (eller null)
    }
    return p;                  // returnerer verdien
    }

  
  @Override
  public String toString() {

    if(tom()){

      return "[]";
    }

    Node p = rot;
    while(p.venstre != null){
      p = p.venstre;
    }

    StringBuilder liste = new StringBuilder();
    liste.append("[");
    liste.append(p.verdi);
    p = nesteInorden(p);
    while(p != null){
      liste.append(", " + p.verdi);
      p = nesteInorden(p);
    }

    liste.append("]");
    return liste.toString();

  }
  
  public String omvendtString() {

    if (tom()){
      return "[]"; // tomt tre
    }

    StringBuilder omvendt = new StringBuilder();
    omvendt.append("[");

    Stakk<Node<T>> stakk = new TabellStakk<>();
    Node<T> p = rot;   // starter i roten og går til høyre

    while (true)
    {
      while (p != null)
      {
        stakk.leggInn(p);
        p = p.høyre;
      }
      if (stakk.tom())
        break;
      p = stakk.taUt();
      if(omvendt.length() == 1){
          omvendt.append(p.verdi);
        }
        else {
          omvendt.append(", " + p.verdi);
        }

      p = p.venstre;
    }

    omvendt.append("]");

    return omvendt.toString();
  }
  
  public String høyreGren() {

      if(tom()){
          return "[]";
      }
      if(antall == 1){
          return "[" + rot.verdi + "]";
      }


      StringBuilder hoyreGren = new StringBuilder();
      hoyreGren.append("[");

      Node<T> p = rot.høyre;
      Node<T> q = rot;
      hoyreGren.append(rot.verdi);


      while(true) {
          while (p != null) {
              hoyreGren.append(", " + p.verdi);
              q = p;
              p = p.høyre;
          }
          p = q;

          if(p.venstre != null){

              p = p.venstre;
          }
          else{
              break;
          }
      }
      hoyreGren.append("]");
      return  hoyreGren.toString();
  }
  
  public String lengstGren()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String[] grener()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String bladnodeverdier()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String postString()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  @Override
  public Iterator<T> iterator()
  {
    return new BladnodeIterator();
  }
  
  private class BladnodeIterator implements Iterator<T>
  {
    private Node<T> p = rot, q = null;
    private boolean removeOK = false;
    private int iteratorendringer = endringer;
    
    private BladnodeIterator()  // konstruktør
    {
      throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
    
    @Override
    public boolean hasNext()
    {
      return p != null;  // Denne skal ikke endres!
    }
    
    @Override
    public T next()
    {
      throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
    
    @Override
    public void remove()
    {
      throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

  } // BladnodeIterator

} // ObligSBinTre
