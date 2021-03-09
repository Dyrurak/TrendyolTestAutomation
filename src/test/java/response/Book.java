package response;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    @JsonPropertyOrder({
            "Id",
            "Author",
            "Title",

    })
    public class Book {
        @JsonProperty("Id")
        private int id;

        @JsonProperty("Author")
        private String author;

        @JsonProperty("Title")
        private String title;



        @JsonProperty("Id")
        public int getId() {
            return this.id;
        }

        @JsonProperty("Author")
        public String getAuthor() {
            return this.author;
        }

        @JsonProperty("Title")
        public String getTitle() {
            return this.title;
        }
}
