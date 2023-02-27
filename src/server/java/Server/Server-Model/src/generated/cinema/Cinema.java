/**--- Generated at Mon Feb 27 14:09:22 CET 2023 
 * --- Mode = No Database 
 * --- Change only in Editable Sections!  
 * --- Do NOT touch section numbering!   
 * --- Do NOT use automatic Eclipse Comment Formatting!   
 */
package generated.cinema;
//10 ===== GENERATED: Import Section ==============
import src.db.executer.PersistenceException;
import generated.cinema.relationControl.*;
import utilities.InitialProxyLoader;
import utilities.InitialRelationLoader;
import utilities.InitialRelationLoader.IntegerPair;
import exceptions.ConstraintViolation;
import generated.cinema.proxies.*;
import observation.Observable;
import src.db.executer.PersistenceExecuterFactory;
import src.db.executer.PersistenceDMLExecuter;
import src.db.connection.DBConnectionData;
import java.util.Map;
//20 ===== Editable : Your import section =========
//30 ===== GENERATED: Main Section ================
public class Cinema extends Observable{
   private PersistenceDMLExecuter dmlExecuter;
   private Map<Integer,MovieProxy> movieCache;
   private Map<Integer,MovieScreeningProxy> movieScreeningCache;
   private Map<Integer,CinemaHallProxy> cinemaHallCache;
   private Map<Integer,SeatRowProxy> seatRowCache;
   private Map<Integer,SeatProxy> seatCache;
   private Map<Integer,BookingProxy> bookingCache;
   private Map<Integer,ReservationProxy> reservationCache;
   private Map<Integer,CustomerProxy> customerCache;
   private static Cinema theInstance = new Cinema();
   private Cinema(){
      this.dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter("Cinema");
      try{
         PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().initializeFor("Cinema");
         this.loadProxies();
         this.loadRelations();
      }catch(Exception e){assert false : "Failed to establish initial database connection. Program aborted: " + e.getMessage();}
   }
   public static Cinema getInstance(){return theInstance;}
   
// The methods loadProxies/Relations will be replaced by more intelligent lazy-load-strategies!
   private void loadProxies() throws PersistenceException{
      this.movieCache = new InitialProxyLoader<MovieProxy>("generated", "Cinema", "cinema", "Movie", "Movie").perform();
      this.movieScreeningCache = new InitialProxyLoader<MovieScreeningProxy>("generated", "Cinema", "cinema", "MovieScreening", "MovieScreening").perform();
      this.cinemaHallCache = new InitialProxyLoader<CinemaHallProxy>("generated", "Cinema", "cinema", "CinemaHall", "CinemaHall").perform();
      this.seatRowCache = new InitialProxyLoader<SeatRowProxy>("generated", "Cinema", "cinema", "SeatRow", "SeatRow").perform();
      this.seatCache = new InitialProxyLoader<SeatProxy>("generated", "Cinema", "cinema", "Seat", "Seat").perform();
      this.bookingCache = new InitialProxyLoader<BookingProxy>("generated", "Cinema", "cinema", "Booking", "BookingState").perform();
      this.reservationCache = new InitialProxyLoader<ReservationProxy>("generated", "Cinema", "cinema", "Reservation", "BookingState").perform();
      this.customerCache = new InitialProxyLoader<CustomerProxy>("generated", "Cinema", "cinema", "Customer", "Customer").perform();
   }
   private void loadRelations() throws PersistenceException{
      for(IntegerPair pair : new InitialRelationLoader("Movie_MovieScreening").perform(this.dmlExecuter).values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "Movie");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "MovieScreening");
         try{this.addMovie_MovieScreeningElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("MovieScreeninig_Movie").perform(this.dmlExecuter).values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "MovieScreening");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "Movie");
         try{this.addMovieScreeninig_MovieElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("MovieScreening_BookingState").perform(this.dmlExecuter).values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "MovieScreening");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "BookingState");
         try{this.addMovieScreening_BookingStateElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("BookingState_MovieScreening").perform(this.dmlExecuter).values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "BookingState");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "MovieScreening");
         try{this.addBookingState_MovieScreeningElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("MovieScreening_CinemaHall").perform(this.dmlExecuter).values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "MovieScreening");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "CinemaHall");
         try{this.addMovieScreening_CinemaHallElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("CinemaHall_SeatRow").perform(this.dmlExecuter).values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "CinemaHall");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "SeatRow");
         try{this.addCinemaHall_SeatRowElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("SeatRow_CinemaHall").perform(this.dmlExecuter).values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "SeatRow");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "CinemaHall");
         try{this.addSeatRow_CinemaHallElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("SeatRow_PriceCategory").perform(this.dmlExecuter).values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "SeatRow");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "PriceCategory");
         try{this.addSeatRow_PriceCategoryElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("SeatRow_Seat").perform(this.dmlExecuter).values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "SeatRow");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "Seat");
         try{this.addSeatRow_SeatElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("Seat_SeatRow").perform(this.dmlExecuter).values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "Seat");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "SeatRow");
         try{this.addSeat_SeatRowElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("Seat_BookingState").perform(this.dmlExecuter).values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "Seat");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "BookingState");
         try{this.addSeat_BookingStateElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("BookingState_Seat").perform(this.dmlExecuter).values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "BookingState");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "Seat");
         try{this.addBookingState_SeatElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("BookingState_Customer").perform(this.dmlExecuter).values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "BookingState");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "Customer");
         try{this.addBookingState_CustomerElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("Customer_BookingState").perform(this.dmlExecuter).values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "Customer");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "BookingState");
         try{this.addCustomer_BookingStateElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
   }
   private void addMovie_MovieScreeningElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      IMovie proxy1 = null; IMovieScreening proxy2 = null; 
      if(className1.equals("Movie"))  proxy1 = this.movieCache.get(id1);
      if(className2.equals("MovieScreening"))  proxy2 = this.movieScreeningCache.get(id2);
      Movie_MovieScreeningSupervisor.getInstance().addAlreadyPersistent(proxy1, proxy2);
   }
   private void addMovieScreeninig_MovieElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      IMovieScreening proxy1 = null; IMovie proxy2 = null; 
      if(className2.equals("Movie"))  proxy2 = this.movieCache.get(id2);
      if(className1.equals("MovieScreening"))  proxy1 = this.movieScreeningCache.get(id1);
      MovieScreeninig_MovieSupervisor.getInstance().setAlreadyPersistent(proxy1, proxy2);
   }
   private void addMovieScreening_BookingStateElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      IMovieScreening proxy1 = null; IBookingState proxy2 = null; 
      if(className1.equals("MovieScreening"))  proxy1 = this.movieScreeningCache.get(id1);
      if(className2.equals("Booking"))  proxy2 = this.bookingCache.get(id2);
      if(className2.equals("Reservation"))  proxy2 = this.reservationCache.get(id2);
      MovieScreening_BookingStateSupervisor.getInstance().addAlreadyPersistent(proxy1, proxy2);
   }
   private void addBookingState_MovieScreeningElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      IBookingState proxy1 = null; IMovieScreening proxy2 = null; 
      if(className2.equals("MovieScreening"))  proxy2 = this.movieScreeningCache.get(id2);
      if(className1.equals("Booking"))  proxy1 = this.bookingCache.get(id1);
      if(className1.equals("Reservation"))  proxy1 = this.reservationCache.get(id1);
      BookingState_MovieScreeningSupervisor.getInstance().setAlreadyPersistent(proxy1, proxy2);
   }
   private void addMovieScreening_CinemaHallElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      IMovieScreening proxy1 = null; ICinemaHall proxy2 = null; 
      if(className1.equals("MovieScreening"))  proxy1 = this.movieScreeningCache.get(id1);
      if(className2.equals("CinemaHall"))  proxy2 = this.cinemaHallCache.get(id2);
      MovieScreening_CinemaHallSupervisor.getInstance().setAlreadyPersistent(proxy1, proxy2);
   }
   private void addCinemaHall_SeatRowElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      ICinemaHall proxy1 = null; ISeatRow proxy2 = null; 
      if(className1.equals("CinemaHall"))  proxy1 = this.cinemaHallCache.get(id1);
      if(className2.equals("SeatRow"))  proxy2 = this.seatRowCache.get(id2);
      CinemaHall_SeatRowSupervisor.getInstance().addAlreadyPersistent(proxy1, proxy2);
   }
   private void addSeatRow_CinemaHallElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      ISeatRow proxy1 = null; ICinemaHall proxy2 = null; 
      if(className2.equals("CinemaHall"))  proxy2 = this.cinemaHallCache.get(id2);
      if(className1.equals("SeatRow"))  proxy1 = this.seatRowCache.get(id1);
      SeatRow_CinemaHallSupervisor.getInstance().setAlreadyPersistent(proxy1, proxy2);
   }
   private void addSeatRow_PriceCategoryElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      ISeatRow proxy1 = null; IPriceCategory proxy2 = null; 
      if(className1.equals("SeatRow"))  proxy1 = this.seatRowCache.get(id1);
      if(className2.equals("PriceCategoryBox"))  proxy2 = PriceCategoryBox.getInstance();
      if(className2.equals("PriceCategoryServiceBox"))  proxy2 = PriceCategoryServiceBox.getInstance();
      if(className2.equals("PriceCategoryStalls"))  proxy2 = PriceCategoryStalls.getInstance();
      SeatRow_PriceCategorySupervisor.getInstance().setAlreadyPersistent(proxy1, proxy2);
   }
   private void addSeatRow_SeatElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      ISeatRow proxy1 = null; ISeat proxy2 = null; 
      if(className1.equals("SeatRow"))  proxy1 = this.seatRowCache.get(id1);
      if(className2.equals("Seat"))  proxy2 = this.seatCache.get(id2);
      SeatRow_SeatSupervisor.getInstance().addAlreadyPersistent(proxy1, proxy2);
   }
   private void addSeat_SeatRowElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      ISeat proxy1 = null; ISeatRow proxy2 = null; 
      if(className2.equals("SeatRow"))  proxy2 = this.seatRowCache.get(id2);
      if(className1.equals("Seat"))  proxy1 = this.seatCache.get(id1);
      Seat_SeatRowSupervisor.getInstance().setAlreadyPersistent(proxy1, proxy2);
   }
   private void addSeat_BookingStateElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      ISeat proxy1 = null; IBookingState proxy2 = null; 
      if(className1.equals("Seat"))  proxy1 = this.seatCache.get(id1);
      if(className2.equals("Booking"))  proxy2 = this.bookingCache.get(id2);
      if(className2.equals("Reservation"))  proxy2 = this.reservationCache.get(id2);
      Seat_BookingStateSupervisor.getInstance().setAlreadyPersistent(proxy1, proxy2);
   }
   private void addBookingState_SeatElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      IBookingState proxy1 = null; ISeat proxy2 = null; 
      if(className2.equals("Seat"))  proxy2 = this.seatCache.get(id2);
      if(className1.equals("Booking"))  proxy1 = this.bookingCache.get(id1);
      if(className1.equals("Reservation"))  proxy1 = this.reservationCache.get(id1);
      BookingState_SeatSupervisor.getInstance().setAlreadyPersistent(proxy1, proxy2);
   }
   private void addBookingState_CustomerElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      IBookingState proxy1 = null; ICustomer proxy2 = null; 
      if(className1.equals("Booking"))  proxy1 = this.bookingCache.get(id1);
      if(className1.equals("Reservation"))  proxy1 = this.reservationCache.get(id1);
      if(className2.equals("Customer"))  proxy2 = this.customerCache.get(id2);
      BookingState_CustomerSupervisor.getInstance().setAlreadyPersistent(proxy1, proxy2);
   }
   private void addCustomer_BookingStateElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      ICustomer proxy1 = null; IBookingState proxy2 = null; 
      if(className2.equals("Booking"))  proxy2 = this.bookingCache.get(id2);
      if(className2.equals("Reservation"))  proxy2 = this.reservationCache.get(id2);
      if(className1.equals("Customer"))  proxy1 = this.customerCache.get(id1);
      Customer_BookingStateSupervisor.getInstance().addAlreadyPersistent(proxy1, proxy2);
   }
   public Movie getMovie(Integer id){
      return this.movieCache.get(id).getTheObject();
   }
   public void addMovieProxy(MovieProxy p) throws PersistenceException{
      this.movieCache.put(p.getId(), p);
   }
   public Map<Integer,MovieProxy> getMovieCache(){
      return this.movieCache;
   }
   public MovieScreening getMovieScreening(Integer id){
      return this.movieScreeningCache.get(id).getTheObject();
   }
   public void addMovieScreeningProxy(MovieScreeningProxy p) throws PersistenceException{
      this.movieScreeningCache.put(p.getId(), p);
   }
   public Map<Integer,MovieScreeningProxy> getMovieScreeningCache(){
      return this.movieScreeningCache;
   }
   public CinemaHall getCinemaHall(Integer id){
      return this.cinemaHallCache.get(id).getTheObject();
   }
   public void addCinemaHallProxy(CinemaHallProxy p) throws PersistenceException{
      this.cinemaHallCache.put(p.getId(), p);
   }
   public Map<Integer,CinemaHallProxy> getCinemaHallCache(){
      return this.cinemaHallCache;
   }
   public SeatRow getSeatRow(Integer id){
      return this.seatRowCache.get(id).getTheObject();
   }
   public void addSeatRowProxy(SeatRowProxy p) throws PersistenceException{
      this.seatRowCache.put(p.getId(), p);
   }
   public Map<Integer,SeatRowProxy> getSeatRowCache(){
      return this.seatRowCache;
   }
   public Seat getSeat(Integer id){
      return this.seatCache.get(id).getTheObject();
   }
   public void addSeatProxy(SeatProxy p) throws PersistenceException{
      this.seatCache.put(p.getId(), p);
   }
   public Map<Integer,SeatProxy> getSeatCache(){
      return this.seatCache;
   }
   public Booking getBooking(Integer id){
      return this.bookingCache.get(id).getTheObject();
   }
   public void addBookingProxy(BookingProxy p) throws PersistenceException{
      this.bookingCache.put(p.getId(), p);
   }
   public Map<Integer,BookingProxy> getBookingCache(){
      return this.bookingCache;
   }
   public Reservation getReservation(Integer id){
      return this.reservationCache.get(id).getTheObject();
   }
   public void addReservationProxy(ReservationProxy p) throws PersistenceException{
      this.reservationCache.put(p.getId(), p);
   }
   public Map<Integer,ReservationProxy> getReservationCache(){
      return this.reservationCache;
   }
   public Customer getCustomer(Integer id){
      return this.customerCache.get(id).getTheObject();
   }
   public void addCustomerProxy(CustomerProxy p) throws PersistenceException{
      this.customerCache.put(p.getId(), p);
   }
   public Map<Integer,CustomerProxy> getCustomerCache(){
      return this.customerCache;
   }
   public PersistenceDMLExecuter getDmlExecuter() {
      return this.dmlExecuter;
   }
   public void closeDBConnection() throws java.sql.SQLException{
      PersistenceExecuterFactory.getConfiguredFactory().getDBDDLExecuter().closeConnection("Cinema");
   }
   //80 ===== Editable : Your Operations =============
//90 ===== GENERATED: End of Your Operations ======
}
