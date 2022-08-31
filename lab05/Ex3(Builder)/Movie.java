package Ex3;
// to complete
import java.util.List;

public class Movie {
   private final String title;
   private final int year;
   private final Person director;
   private final Person writer;
   private final String series;
   private final List<Person> cast;
   private final List<Place> locations;
   private final List<String> languages;
   private final List<String> genres;
   private final boolean isTelevision;
   private final boolean isNetflix;
   private final boolean isIndependent;

   public static class Builder {
      // Required
      private final String movieTitle;
      private final int movieYear;
      // Optional
      private Person movieDirector;
      private Person movieWriter;
      private String movieSeries;
      private List<Person> movieCast;
      private List<Place> movieLocations;
      private List<String> movieLanguages;
      private List<String> movieGenres;
      private boolean television;
      private boolean netflix;
      private boolean independent;

      public Builder(String movieTitle, int movieYear) {
         this.movieTitle = movieTitle;
         this.movieYear = movieYear;
      }

      public Builder movieDirector(Person movieDirector) {
         this.movieDirector = movieDirector;
         return this;
      }
      
      public Builder movieWriter(Person movieWriter) {
         this.movieWriter = movieWriter;
         return this;
      }

      public Builder movieSeries(String movieSeries) {
         this.movieSeries = movieSeries;
         return this;
      }

      public Builder movieCast(List<Person> movieCast) {
         this.movieCast = movieCast;
         return this;
      }

      public Builder movieLocations(List<Place> movieLocations) {
         this.movieLocations = movieLocations;
         return this;
      }

      public Builder movieLanguages(List<String> movieLanguages) {
         this.movieLanguages = movieLanguages;
         return this;
      }

      public Builder movieGenres(List<String> movieGenres) {
         this.movieGenres = movieGenres;
         return this;
      }

      public Builder television(boolean television) {
         this.television = television;
         return this;
      }

      public Builder netflix(boolean netflix) {
         this.netflix = netflix;
         return this;
      }

      public Builder independent(boolean independent) {
         this.independent = independent;
         return this;
      }
   }
   
   private Movie(Builder builder){
      this.title = builder.movieTitle;
      this.year = builder.movieYear;
      this.director = builder.movieDirector;
      this.writer = builder.movieWriter;
      this.series = builder.movieSeries;
      this.cast = builder.movieCast;
      this.locations = builder.movieLocations;
      this.languages = builder.movieLanguages;
      this.genres = builder.movieGenres;
      this.isTelevision = builder.television;
      this.isNetflix = builder.netflix;
      this.isIndependent = builder.independent;
   }
}

