/**--- Generated at Tue Mar 07 14:00:47 CET 2023 
 * --- Mode = Integrated Database 
 * --- Change only in Editable Sections!  
 * --- Do NOT touch section numbering!   
 * --- Do NOT use automatic Eclipse Comment Formatting!   
 */
package generated.cinemaService;
//10 ===== GENERATED: Import Section ==============
import src.db.executer.PersistenceException;
import generated.cinemaService.relationControl.*;
import utilities.InitialProxyLoader;
import utilities.InitialRelationLoader;
import utilities.InitialRelationLoader.IntegerPair;
import exceptions.ConstraintViolation;
import generated.cinemaService.proxies.*;
import observation.Observable;
import src.db.executer.PersistenceExecuterFactory;
import src.db.executer.PersistenceDMLExecuter;
import src.db.connection.DBConnectionData;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
//20 ===== Editable : Your import section =========
//30 ===== GENERATED: Main Section ================
public class CinemaService extends Observable{
   private PersistenceDMLExecuter dmlExecuter;
   private Map<Integer,MovieProxy> movieCache;
   private Map<Integer,MovieScreeningProxy> movieScreeningCache;
   private Map<Integer,CinemaHallProxy> cinemaHallCache;
   private Map<Integer,SeatRowProxy> seatRowCache;
   private Map<Integer,SeatProxy> seatCache;
   private Map<Integer,BookingProxy> bookingCache;
   private Map<Integer,ReservationProxy> reservationCache;
   private Map<Integer,CustomerProxy> customerCache;
   private static CinemaService theInstance = new CinemaService();
   private CinemaService(){
      PersistenceExecuterFactory.setUseDataBase();
      this.dmlExecuter = PersistenceExecuterFactory.getConfiguredFactory().getDBDMLExecuter("CinemaService");
      try{PersistenceExecuterFactory.getConfiguredFactory().getDBDDLExecuter().openDBConnection(new DBConnectionData("jdbc:mysql://localhost:3306", "CinemaService", "root" , ""));
         PersistenceExecuterFactory.getConfiguredFactory().getTypeKeyManager().initializeFor("CinemaService");
         this.loadProxies();
         this.loadRelations();
      }catch(Exception e){assert false : "Failed to establish initial database connection. Program aborted: " + e.getMessage();}
   }
   public static CinemaService getInstance(){return theInstance;}
   
// The methods loadProxies/Relations will be replaced by more intelligent lazy-load-strategies!
   private void loadProxies() throws PersistenceException{
      this.movieCache = new InitialProxyLoader<MovieProxy>("generated", "CinemaService", "cinemaService", "Movie", "Movie").perform();
      this.movieScreeningCache = new InitialProxyLoader<MovieScreeningProxy>("generated", "CinemaService", "cinemaService", "MovieScreening", "MovieScreening").perform();
      this.cinemaHallCache = new InitialProxyLoader<CinemaHallProxy>("generated", "CinemaService", "cinemaService", "CinemaHall", "CinemaHall").perform();
      this.seatRowCache = new InitialProxyLoader<SeatRowProxy>("generated", "CinemaService", "cinemaService", "SeatRow", "SeatRow").perform();
      this.seatCache = new InitialProxyLoader<SeatProxy>("generated", "CinemaService", "cinemaService", "Seat", "Seat").perform();
      this.bookingCache = new InitialProxyLoader<BookingProxy>("generated", "CinemaService", "cinemaService", "Booking", "BookingState").perform();
      this.reservationCache = new InitialProxyLoader<ReservationProxy>("generated", "CinemaService", "cinemaService", "Reservation", "BookingState").perform();
      this.customerCache = new InitialProxyLoader<CustomerProxy>("generated", "CinemaService", "cinemaService", "Customer", "Customer").perform();
   }
   private void loadRelations() throws PersistenceException{
      for(IntegerPair pair : new InitialRelationLoader("Movie_MovieScreening").perform(this.dmlExecuter).values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "Movie");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "MovieScreening");
         try{this.addMovie_MovieScreeningElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("CinemaHall_MovieScreening").perform(this.dmlExecuter).values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "CinemaHall");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "MovieScreening");
         try{this.addCinemaHall_MovieScreeningElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("MovieScreening_BookingState").perform(this.dmlExecuter).values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "MovieScreening");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "BookingState");
         try{this.addMovieScreening_BookingStateElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
      }
      for(IntegerPair pair : new InitialRelationLoader("CinemaHall_SeatRow").perform(this.dmlExecuter).values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "CinemaHall");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "SeatRow");
         try{this.addCinemaHall_SeatRowElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
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
      for(IntegerPair pair : new InitialRelationLoader("Seat_BookingState").perform(this.dmlExecuter).values()){
         String className1 = this.dmlExecuter.getNameOfConcreteType(pair.getP1(), "Seat");
         String className2 = this.dmlExecuter.getNameOfConcreteType(pair.getP2(), "BookingState");
         try{this.addSeat_BookingStateElement(pair.getP1(), className1, pair.getP2(), className2);}catch(ConstraintViolation cv){throw new PersistenceException(cv.getMessage());}
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
   private void addCinemaHall_MovieScreeningElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      ICinemaHall proxy1 = null; IMovieScreening proxy2 = null; 
      if(className2.equals("MovieScreening"))  proxy2 = this.movieScreeningCache.get(id2);
      if(className1.equals("CinemaHall"))  proxy1 = this.cinemaHallCache.get(id1);
      CinemaHall_MovieScreeningSupervisor.getInstance().addAlreadyPersistent(proxy1, proxy2);
   }
   private void addMovieScreening_BookingStateElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      IMovieScreening proxy1 = null; IBookingState proxy2 = null; 
      if(className1.equals("MovieScreening"))  proxy1 = this.movieScreeningCache.get(id1);
      if(className2.equals("Booking"))  proxy2 = this.bookingCache.get(id2);
      if(className2.equals("Reservation"))  proxy2 = this.reservationCache.get(id2);
      MovieScreening_BookingStateSupervisor.getInstance().addAlreadyPersistent(proxy1, proxy2);
   }
   private void addCinemaHall_SeatRowElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      ICinemaHall proxy1 = null; ISeatRow proxy2 = null; 
      if(className1.equals("CinemaHall"))  proxy1 = this.cinemaHallCache.get(id1);
      if(className2.equals("SeatRow"))  proxy2 = this.seatRowCache.get(id2);
      CinemaHall_SeatRowSupervisor.getInstance().addAlreadyPersistent(proxy1, proxy2);
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
   private void addSeat_BookingStateElement(Integer id1, String className1, Integer id2, String className2) throws ConstraintViolation, PersistenceException{
      ISeat proxy1 = null; IBookingState proxy2 = null; 
      if(className1.equals("Seat"))  proxy1 = this.seatCache.get(id1);
      if(className2.equals("Booking"))  proxy2 = this.bookingCache.get(id2);
      if(className2.equals("Reservation"))  proxy2 = this.reservationCache.get(id2);
      Seat_BookingStateSupervisor.getInstance().addAlreadyPersistent(proxy1, proxy2);
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
      PersistenceExecuterFactory.getConfiguredFactory().getDBDDLExecuter().closeConnection("CinemaService");
   }
   //80 ===== Editable : Your Operations =============
   public static synchronized <T> Map<Integer, T> getCacheOf(Class<T> type)
   {
      if (_caches == null)
      {
         _caches = new HashMap<>();
         _caches.put(Movie.class, cinema -> cinema.getMovieCache());
         _caches.put(MovieScreening.class, cinema -> cinema.getMovieScreeningCache());
         _caches.put(CinemaHall.class, cinema -> cinema.getCinemaHallCache());
         _caches.put(SeatRow.class, cinema -> cinema.getSeatRowCache());
         _caches.put(Seat.class, cinema -> cinema.getSeatCache());
         _caches.put(Booking.class, cinema -> cinema.getBookingCache());
         _caches.put(Reservation.class, cinema -> cinema.getReservationCache());
         _caches.put(Customer.class, cinema -> cinema.getCustomerCache());
         Function<CinemaService, Map> getBookingStateCache = cinema -> {
            final var bookings = cinema.getBookingCache();
            final var reservations = cinema.getReservationCache();
            final Map<Integer, BookingStateProxy> results = new HashMap<>();
            results.putAll(bookings);
            results.putAll(reservations);
            return results;
         };
         _caches.put(BookingState.class, getBookingStateCache);
      }
      Function<CinemaService, Map> cacheFactory = _caches.getOrDefault(type, cinema -> null);
      var result = cacheFactory.apply(CinemaService.getInstance());
      return (Map<Integer, T>)result;
   }

   public static <T> Collection<T> getSetOf(Class<T> type)
   {
      return getCacheOf(type).values();
   }

   private static Map<Class, Function<CinemaService, Map>> _caches = null;
//90 ===== GENERATED: End of Your Operations ======
}
