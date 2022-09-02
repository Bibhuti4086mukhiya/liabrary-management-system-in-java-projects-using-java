package cw2;

public class Bill {String bill_date;

    int book_id,bill_id,discount, sell_piece;

    Bill(Integer bill_id,Integer book_id, Integer sell_piece,Integer discount,
         String bill_date){


        this.book_id =book_id;
        this.bill_id=bill_id;
        this.discount =discount;
        this.sell_piece =sell_piece;
        this.bill_date =bill_date;

    }

}
