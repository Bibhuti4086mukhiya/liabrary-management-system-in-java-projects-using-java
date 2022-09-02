package cw2;

public class Book {
    String book_name, book_writer, publisher, published_date ;
    int book_id,price,added_book, rem_book;

    Book(Integer book_id, String book_name, String book_writer, String publisher,String published_date, Integer price, Integer added_book, Integer rem_book){
        this.book_id = book_id;

        this.book_name = book_name;
        this.book_writer =book_writer;
        this.publisher=publisher;
        this.published_date =published_date;
        this.price =price;
        this.added_book =added_book;
        this.rem_book =rem_book;

    }

}