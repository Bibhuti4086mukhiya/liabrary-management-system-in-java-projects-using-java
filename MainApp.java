package cw2;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
class MainApp {
    public static void main(String[]args){




        JFrame fr = new JFrame("Bibhuti  Pustak Bhandar");
        MainApp ma = new MainApp();

        ma.addBooks(fr);

//        ma.updateBooks(fr, ma.bookTable(fr));

//        ================================================================================================================
        String query3 = "Select * from books";
        DbConnect db = new DbConnect();
        ArrayList<Book> bok = new ArrayList<Book>();

        try {
            ResultSet result = db.connection().executeQuery(query3);

            while (result.next()) {

                String book_name = result.getString("book_name");
                String book_writer = result.getString("book_writer");
                String publisher = result.getString("publisher");
                String published_date = result.getString("published_date");

                Integer book_id = Integer.parseInt(result.getString("book_id"));
                Integer price = Integer.parseInt(result.getString("price"));
                Integer added_book = Integer.parseInt(result.getString("added_book"));
                Integer rem_book = Integer.parseInt(result.getString("rem_book"));


                System.out.println(book_name + book_writer + publisher + published_date + price + added_book + rem_book);
                Book bks = new Book(book_id,book_name, book_writer, publisher, published_date, price, added_book, rem_book);
                bok.add(bks);

            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String column[] = {"Id","Book", "Writer", "Publisher", "Published Date", "Price", "Added pcs.", "Rem pcs."};
        Object[][] data = new Object[bok.size()][column.length];

        for (int i = 0; i < bok.size(); i++) {
//            System.out.println(emp.get(i).name);
//            System.out.println(emp.get(i).code);
//            System.out.println(emp.get(i).position);
//            System.out.println(emp.get(i).salalry);
            data[i][0] = bok.get(i).book_id;
            data[i][1] = bok.get(i).book_name;
            data[i][2] = bok.get(i).book_writer;
            data[i][3] = bok.get(i).publisher;
            data[i][4] = bok.get(i).published_date;
            data[i][5] = bok.get(i).price;
            data[i][6] = bok.get(i).added_book;
            data[i][7] = bok.get(i).rem_book;
        }

        JTable jt = new JTable(data, column);
        jt.setBounds(400, 100, 200, 300);
        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds(490, 180, 800, 400);
        fr.add(sp);


//        ================================================================================================================

        JButton btnDel;
        btnDel = new JButton("Delete");
        fr.add(btnDel);
        btnDel.setBounds(1090, 600, 90, 25);

        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row1 = (jt.getSelectedRow());
                System.out.println(row1);
                if (row1 >= 0) {

                    TableModel model = (jt.getModel());
                    int getid= (int) model.getValueAt(row1,0);
                    System.out.println(getid);


                    String dquery = "Delete from books where book_id = "+getid+"";
                    int select =JOptionPane.showConfirmDialog(btnDel,"Do you really wanna delete ?");
                    if (select==0){

                        try {
                            DbConnect db= new DbConnect();
                            Integer rdelete= db.connection().executeUpdate(dquery);

                            System.out.println(rdelete);
                            if(rdelete >=1){
                                JOptionPane.showMessageDialog(fr,"Book deleted");
                                fr.dispose();
                                 new MainApp();

//                                    new MainApp();
//                                    fr.dispose();
                            }

                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                    else {
                        System.out.println("op");
                    };
                }
                else{
                    JOptionPane.showMessageDialog(fr, "Select the row first!");
                };
            }
        });
//        =========================================================================================================
        JLabel desc, lbookName, lbookWriter, lbookPublisher, lbookPublishDate, lbookPrice, laddedBook, lremBook;
        desc = new JLabel("Update Book Details");
        lbookName = new JLabel("Book's Name");
        lbookWriter = new JLabel("Book's Writer");
        lbookPublisher = new JLabel("Publication");
        lbookPublishDate = new JLabel("Published Date");
        lbookPrice = new JLabel("Price");
        laddedBook = new JLabel("Added piece");
        lremBook = new JLabel("Remaining piece");


        fr.add(desc);
        desc.setBounds(200, 330, 200, 45);
        fr.add(lbookName);
        lbookName.setBounds(100, 380, 200, 45);
        fr.add(lbookWriter);
        lbookWriter.setBounds(100, 420, 200, 45);
        fr.add(lbookPublisher);
        lbookPublisher.setBounds(100, 460, 200, 45);
        fr.add(lbookPublishDate);
        lbookPublishDate.setBounds(100, 500, 200, 45);
        fr.add(lbookPrice);
        lbookPrice.setBounds(100, 540, 200, 45);
        fr.add(laddedBook);
        laddedBook.setBounds(100, 580, 200, 45);
        fr.add(lremBook);
        lremBook.setBounds(100, 620, 200, 45);


        JTextField tfbookName, tfbookWriter, tfbookPublisher, tfbookPublishDate, tfbookPrice, tfaddedBook, tfremBook;
        tfbookName = new JTextField(60);
        tfbookWriter = new JTextField(60);
        tfbookPublisher = new JTextField(100);
        tfbookPublishDate = new JTextField(30);
        tfbookPrice = new JTextField(10);
        tfbookPrice.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
        tfaddedBook = new JTextField(4);
        tfaddedBook.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });



        tfremBook = new JTextField(4);
        tfremBook.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });


        fr.add(tfbookName);
        tfbookName.setBounds(210, 390, 170, 25);
        fr.add(tfbookWriter);
        tfbookWriter.setBounds(210, 430, 170, 25);
        fr.add(tfbookPublisher);


        tfbookPublisher.setBounds(210, 470, 170, 25);
        fr.add(tfbookPublishDate);
        tfbookPublishDate.setBounds(210, 510, 170, 25);
        fr.add(tfbookPrice);
        tfbookPrice.setBounds(210, 550, 170, 25);
        fr.add(tfaddedBook);
        tfaddedBook.setBounds(210, 590, 170, 25);
        fr.add(tfremBook);
        tfremBook.setBounds(210, 630, 60, 25);

        JButton btnUpd;
        btnUpd = new JButton("Update");
        fr.add(btnUpd);
        btnUpd.setBounds(290, 630, 90, 25);

        btnUpd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = jt.getSelectedRow();
                System.out.println(row);

                if (row >= 0) {
                    btnUpd.setEnabled(false);
                    TableModel model = jt.getModel();
                    String book_name=(String) model.getValueAt(row,1);
                    tfbookName.setText(book_name);

                    TableModel model1 = jt.getModel();
                    String book_writer=(String) model1.getValueAt(row,2);
                    tfbookWriter.setText(book_writer);

                    TableModel model2 = jt.getModel();
                    String publisher=(String) model2.getValueAt(row,3);
                    tfbookPublisher.setText(publisher);

                    TableModel model3 = jt.getModel();
                    String publisheddate=(String) model3.getValueAt(row,4);
                    tfbookPublishDate.setText(publisheddate);


                    TableModel model4 = jt.getModel();
                    int price=(int) model4.getValueAt(row,5);
                    tfbookPrice.setText(price+"");

                    TableModel model5 = jt.getModel();
                    int addedpiece=(int) model5.getValueAt(row,6);
                    tfaddedBook.setText(addedpiece+"");

                    TableModel model6 = jt.getModel();
                    int remainpiece=(int) model6.getValueAt(row,7);
                    tfremBook.setText(remainpiece+"");

                    JButton updConfirm;
                    updConfirm = new JButton("Confirm update");
                    fr.add(updConfirm);
                    updConfirm.setBounds(400, 630, 130, 25);

                    updConfirm.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            TableModel model9 = jt.getModel();
                            int book_id=(int) model9.getValueAt(row,0);

                            String bookname = tfbookName.getText();
                            String bookwriter = tfbookWriter.getText();
                            String publisher = tfbookPublisher.getText();
                            String publisheddate = tfbookPublishDate.getText();
                            String price = tfbookPrice.getText();
                            String addedbook = tfaddedBook.getText();
                            String rembook = tfremBook.getText();





                            if (bookname.isBlank() || bookwriter.isBlank() || publisher.isBlank() || publisheddate.isBlank()
                                    || price.isBlank() || addedbook.isBlank() || rembook.isBlank()) {
                                JOptionPane.showMessageDialog(fr, "Fill out all the fields first!");


                            } else {
                                if (bookname.isBlank() || bookwriter.isBlank() || publisher.isBlank() || publisheddate.isBlank()
                                        || price.isBlank() || addedbook.isBlank() || rembook.isBlank()) {
                                    JOptionPane.showMessageDialog(fr, "Fill out all the fields first!");}
                                else {

                                    try {
                                        DbConnect db = new DbConnect();
                                        String query = " UPDATE books set book_name='" + bookname + "',book_writer='" + bookwriter + "',publisher='" + publisher + "',published_date='" + publisheddate + "',price=" + price + ",added_book=" + addedbook + ",rem_book=" + rembook + " where book_id = " + book_id + "";
                                        System.out.println(query);
                                        db.connection().executeUpdate(query);
                                        JOptionPane.showMessageDialog(fr, "Book updated successfully.");
                                        fr.dispose();
                                        MainApp ma = new MainApp();
                                        ma.main(null);


                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }
                                }
                            }
                        }
                    });
                }
                else{
                    JOptionPane.showMessageDialog(fr, "Select a row from the table first!");

                }
            }
        });

//        =========================================================================================================

        JButton btnBack;
        btnBack = new JButton("Exit");
        fr.add(btnBack);
        btnBack.setBounds(1190, 600, 100, 25);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int select =JOptionPane.showConfirmDialog(btnBack,"Do you really wanna exit ?");

                if (select==0){
                    fr.dispose();
                }
            }
        });
        JButton btnRefresh;
        btnRefresh = new JButton("Refresh");
        fr.add(btnRefresh);
        btnRefresh.setBounds(640, 600, 100, 25);
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    fr.dispose();
                    MainApp ma = new MainApp();
                    ma.main(null);

            }
        });

        JButton btnSold;
        btnSold = new JButton("All Sold books");
        fr.add(btnSold);
        btnSold.setBounds(490, 600, 130, 25);

        btnSold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame soldfr = new JFrame("Bill Details ");



                String query9 = "Select * from bills";
                DbConnect db = new DbConnect();
                ArrayList<Bill> bil = new ArrayList<Bill>();

                try {
                    ResultSet result = db.connection().executeQuery(query9);

                    while (result.next()) {


                        Integer bill_id = Integer.parseInt(result.getString("bill_id"));
                        Integer book_id = Integer.parseInt(result.getString("book_id"));
                        Integer sell_piece = Integer.parseInt(result.getString("sell_piece"));
                        Integer discount = Integer.parseInt(result.getString("discount"));
                        String bill_date = result.getString("bill_date");



                        Bill bils = new Bill(bill_id,book_id, sell_piece, discount, bill_date);

                        bil.add(bils);

                    }



                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                String column[] = {"Bill Id","Book Id", "Book Name", "Sold Piece", "Offered discount", "Billed date"};
                Object[][] data = new Object[bil.size()][column.length];

                for (int i = 0; i < bil.size(); i++) {
//            System.out.println(emp.get(i).name);
//            System.out.println(emp.get(i).code);
//            System.out.println(emp.get(i).position);
//            System.out.println(emp.get(i).salalry);
                    data[i][0] = bil.get(i).bill_id;
                    data[i][1] = bil.get(i).book_id;
                    int id = bil.get(i).book_id;

                    try {
                        data[i][2]=(ma.getBookName(id));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    data[i][3] = bil.get(i).sell_piece;
                    data[i][4] = bil.get(i).discount;
                    data[i][5] = bil.get(i).bill_date;
                }

                JTable st = new JTable(data, column);
                st.setBounds(400, 100, 200, 300);
                JScrollPane stp = new JScrollPane(st);
                stp.setBounds(90, 80, 800, 400);
                soldfr.add(stp);

                soldfr.setLayout(null);
                soldfr.setSize(1000, 1080);
                soldfr.setVisible(true);

            }


        });

        JButton btnSell;
        btnSell = new JButton("Sell");
        fr.add(btnSell);
        btnSell.setBounds(965, 600, 100, 25);
        btnSell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = jt.getSelectedRow();

                TableModel model2 = jt.getModel();


                System.out.println(row);
                if (row >= 0) {
                    int avapiece=(int) model2.getValueAt(row,7);
                    if(avapiece>0){
                        JFrame sellfr = new JFrame("Sell Books ");
                        JLabel lbBookname, lbDiscount, lbremPiece, lbsellPiece;
                        lbBookname = new JLabel("Willing to sell: ");
                        lbDiscount =new JLabel("Discount");
                        lbremPiece=new JLabel("Available pcs");
                        lbsellPiece=new JLabel("No. of piece");

                        sellfr.add(lbBookname);
                        lbBookname.setBounds(100,30,120,25);
                        sellfr.add(lbDiscount);
                        lbDiscount.setBounds(90,80,120,25);
                        sellfr.add(lbremPiece);
                        lbremPiece.setBounds(170,80,120,25);
                        sellfr.add(lbsellPiece);
                        lbsellPiece.setBounds(270,80,120,25);

                        JTextField tfdiscount, tfsellpiece;
                        JLabel tfbookName, tfAvapiece;

                        tfdiscount= new JTextField(10);
                        tfdiscount.addKeyListener(new KeyAdapter() {
                            public void keyTyped(KeyEvent e) {
                                char c = e.getKeyChar();
                                if (!(Character.isDigit(c) ||
                                        (c == KeyEvent.VK_BACK_SPACE) ||
                                        (c == KeyEvent.VK_DELETE))) {
                                    e.consume();
                                }
                            }
                        });


                        tfsellpiece=new JTextField(2);
                        tfsellpiece.addKeyListener(new KeyAdapter() {
                            public void keyTyped(KeyEvent e) {
                                char c = e.getKeyChar();
                                if (!(Character.isDigit(c) ||
                                        (c == KeyEvent.VK_BACK_SPACE) ||
                                        (c == KeyEvent.VK_DELETE))) {
                                    e.consume();
                                }
                            }
                        });


                        tfbookName=new JLabel("");
                        tfAvapiece=new JLabel("");

                        sellfr.add(tfbookName);
                        tfbookName.setBounds(190,30,120,25);

                        sellfr.add(tfdiscount);
                        tfdiscount.setBounds(90,120,40,25);

                        sellfr.add(tfAvapiece);
                        tfAvapiece.setBounds(190,120,40,25);

                        sellfr.add(tfsellpiece);
                        tfsellpiece.setBounds(270,120,40,25);

                        TableModel model = jt.getModel();
                        String book_name=(String) model.getValueAt(row,1);
                        tfbookName.setText(book_name);


                        tfAvapiece.setText(avapiece+"");


                        JButton btnStore;
                        btnStore= new JButton("Add to bills");
                        sellfr.add(btnStore);
                        btnStore.setBounds(230,200,120,25);

                        btnStore.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                TableModel model10 = jt.getModel();
                                int book_id=(int) model10.getValueAt(row,0);

                                String discount = tfdiscount.getText();
                                String sellpiece = tfsellpiece.getText();

                                if(sellpiece.isBlank() || discount.isBlank()){
                                    JOptionPane.showMessageDialog(sellfr, "Fill out both discount and book piece!");

                                }
                                else if(avapiece< Integer.parseInt(sellpiece)){
                                    JOptionPane.showMessageDialog(sellfr, "Only "+avapiece+" pieces available!");
                                }

                                else if(Integer.parseInt(sellpiece)==0){
                                    JOptionPane.showMessageDialog(sellfr, "0 piece doesn't make sense !");

                                }
                                else if (Integer.parseInt(discount)>50){
                                    JOptionPane.showMessageDialog(sellfr, "Can't give more than 50% discount!");
                                }
                                else{
                                    DbConnect db = new DbConnect();
                                    String query = "insert into bills(book_id, discount, sell_piece) values (" + book_id + "," + discount + "," + sellpiece + ")";
                                    System.out.println(query);
                                    try {
                                        db.connection().executeUpdate(query);
                                        JOptionPane.showMessageDialog(fr, "Added to bills successfully.");
                                        int remaftersell= avapiece-Integer.parseInt(sellpiece);
                                        String query1 = " UPDATE books set rem_book=" + remaftersell + " where book_id = " + book_id + "";
                                        try {
                                            db.connection().executeUpdate(query1);
                                            sellfr.dispose();
                                            new MainApp();

                                        }
                                        catch (SQLException throwables) {
                                            throwables.printStackTrace();
                                        }

                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }

                                }

                            }
                        });
                        sellfr.setLayout(null);
                        sellfr.setSize(450, 300);
                        sellfr.setVisible(true);

                    }
                    else {
                        JOptionPane.showMessageDialog(fr, "Seems like the book is out of stock!");

                    }
                }
                else{
                    JOptionPane.showMessageDialog(fr, "Select the book you want make a sell first!");
                }



            }
        });

//====================================================================================

        JLabel lsort = new JLabel("Sort by:");
        JLabel lsearch = new JLabel("Search by:");
        JLabel lkeyword = new JLabel("Keyword:");
        JTextField tfKeyword = new JTextField(50);

        JButton btnSort = new JButton("Sort");
        JButton btnSearch = new JButton("🔎 Search");


        fr.add(lsort);
        lsort.setBounds(800, 50, 150, 25);
        fr.add(lsearch);
        lsearch.setBounds(1060, 50, 150, 25);
        fr.add(lkeyword);
        lkeyword.setBounds(1060, 90, 150, 25);

        fr.add(tfKeyword);
        tfKeyword.setBounds(1140, 90, 150, 25);




        String[] sortList = {
                "Ascending",
                "Descending",

        };

        String[] searchList = {
                "Book Name",
                "Writer",
                "Publisher",
                "Published Date",

        };


        JComboBox sortComb = new JComboBox(sortList);
        sortComb.setEditable(false);

        JComboBox searchComb = new JComboBox(searchList);
        searchComb.setEditable(false);

        fr.add(btnSort);
        btnSort.setBounds(940, 90, 70, 25);

        fr.add(btnSearch);
        btnSearch.setBounds(1140, 130, 150, 25);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = tfKeyword.getText();
                if (keyword.isBlank()){
                    JOptionPane.showMessageDialog(fr, "Give some word hint so that i can help you!");
                    fr.dispose();
                    MainApp m=new MainApp();
                    m.main(null);

                }
                else{
                    MainApp ma= new MainApp();
                    Search se = new Search();
                    ArrayList books = se.allBooks();
                    int sresult = se.binarySearch(books,keyword);
                    if (sresult == -1){
                        JOptionPane.showMessageDialog(fr, "No book found named: "+keyword);
                    }
                    else{

                            try {
                                ResultSet result = ma.getBookDetail(keyword);
                                while (result.next()){
                                System.out.println(result.getString("book_id"));

                                    String column[] = {"Id","Book", "Writer", "Publisher", "Published Date", "Price", "Added pcs.", "Rem pcs."};
                                    Object[][] data = new Object[1][column.length];

                                    for (int i = 0; i < 1; i++) {
//            System.out.println(emp.get(i).name);
//            System.out.println(emp.get(i).code);
//            System.out.println(emp.get(i).position);
//            System.out.println(emp.get(i).salalry);
                                        data[i][0] = result.getString("book_id");
                                        data[i][1] = result.getString("book_name");
                                        data[i][2] = result.getString("book_writer");
                                        data[i][3] = result.getString("publisher");
                                        data[i][4] = result.getString("published_date");
                                        data[i][5] = result.getString("price");
                                        data[i][6] = result.getString("added_book");
                                        data[i][7] = result.getString("rem_book");







//                                        data[i][2] = bok.get(i).book_writer;
//                                        data[i][3] = bok.get(i).publisher;
//                                        data[i][4] = bok.get(i).published_date;
//                                        data[i][5] = bok.get(i).price;
//                                        data[i][6] = bok.get(i).added_book;
//                                        data[i][7] = bok.get(i).rem_book;
                                    }

                                    JTable jt1 = new JTable(data, column);
                                    jt1.setBounds(400, 100, 200, 300);
                                    JScrollPane sp = new JScrollPane(jt1);
                                    sp.setBounds(490, 180, 800, 400);
                                    fr.add(sp);







                                }
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }


                        //            System.out.println(emp.get(i).code);
    //            System.out.println(emp.get(i).position);
    //            System.out.println(emp.get(i).salalry);


                    }}}



        });


        fr.add(sortComb);
        sortComb.setBounds(860, 50, 150, 25);

        fr.add(searchComb);
        searchComb.setBounds(1140, 50, 150, 25);



//  ======================================================================
        fr.setLayout(null);
        fr.setSize(1920, 1080);
        fr.setVisible(true);

    }

    public void addBooks(Frame fr) {
        JLabel desc, lbookName, lbookWriter, lbookPublisher, lbookPublishDate, lbookPrice, laddedBook;
        desc = new JLabel("Add Book Details");
        lbookName = new JLabel("Book's Name");
        lbookWriter = new JLabel("Book's Writer");
        lbookPublisher = new JLabel("Publication");
        lbookPublishDate = new JLabel("Published Date");
        lbookPrice = new JLabel("Price");

        laddedBook = new JLabel("Added piece");

        fr.add(desc);
        desc.setBounds(200, 20, 200, 45);
        fr.add(lbookName);
        lbookName.setBounds(100, 70, 200, 45);
        fr.add(lbookWriter);
        lbookWriter.setBounds(100, 110, 200, 45);
        fr.add(lbookPublisher);
        lbookPublisher.setBounds(100, 150, 200, 45);
        fr.add(lbookPublishDate);
        lbookPublishDate.setBounds(100, 190, 200, 45);
        fr.add(lbookPrice);
        lbookPrice.setBounds(100, 230, 200, 45);
        fr.add(laddedBook);
        laddedBook.setBounds(100, 270, 200, 45);


        JTextField tfbookName, tfbookWriter, tfbookPublisher, tfbookPublishDate, tfbookPrice, tfaddedBook;
        tfbookName = new JTextField(60);
        tfbookWriter = new JTextField(60);
        tfbookPublisher = new JTextField(100);
        tfbookPublishDate = new JTextField(30);
        tfbookPrice = new JTextField(10);
        tfbookPrice.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
        tfaddedBook = new JTextField(4);
        tfaddedBook.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });


        fr.add(tfbookName);
        tfbookName.setBounds(210, 80, 170, 25);
        fr.add(tfbookWriter);
        tfbookWriter.setBounds(210, 120, 170, 25);
        fr.add(tfbookPublisher);
        tfbookPublisher.setBounds(210, 160, 170, 25);
        fr.add(tfbookPublishDate);
        tfbookPublishDate.setBounds(210, 200, 170, 25);
        fr.add(tfbookPrice);
        tfbookPrice.setBounds(210, 240, 170, 25);
        fr.add(tfaddedBook);
        tfaddedBook.setBounds(210, 280, 90, 25);

        JButton btnAdd;
        btnAdd = new JButton("Add");
        fr.add(btnAdd);
        btnAdd.setBounds(310, 280, 70, 25);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookname = tfbookName.getText();
                String bookwriter = tfbookWriter.getText();
                String publisher = tfbookPublisher.getText();
                String publisheddate = tfbookPublishDate.getText();
                String price = tfbookPrice.getText();
                String addedbook = tfaddedBook.getText();

                if (bookname.isBlank() || bookwriter.isBlank() || publisher.isBlank() || publisheddate.isBlank()
                        || price.isBlank() || addedbook.isBlank()) {
                    JOptionPane.showMessageDialog(fr, "Fill out all the fields first!");


                } else {
                    try {
                        DbConnect db = new DbConnect();
                        String query = "insert into books(book_name, book_writer, publisher, published_date, price, added_book, rem_book) values ('" + bookname + "','" + bookwriter + "','" + publisher + "','" + publisheddate + "'," + price + "," + addedbook + "," + addedbook + ")";
                        System.out.println(query);
                        db.connection().executeUpdate(query);
                        JOptionPane.showMessageDialog(fr, "Book added successfully.");
                        fr.dispose();
                        new MainApp();


                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
    }

    public ResultSet getBookDetail(String bookname) throws SQLException {
        String query2= "Select * from books where book_name ='"+bookname+"'";
        DbConnect db = new DbConnect();
        ResultSet result = db.connection().executeQuery(query2);
        return result;
    }

    public String getBookName(int id) throws SQLException {
        String query = "Select book_name from books where book_id ="+id+"";
        DbConnect db = new DbConnect();

        ResultSet result = db.connection().executeQuery(query);
        while(result.next())
            return result.getString(1);

        return result.getString(1);
    }


}





//CREATE TABLE books (
//book_id int auto_increment primary key,
//book_name VARCHAR(50),
//book_writer VARCHAR(70),
//publisher VARCHAR(50),
//published_date date,
//added_book int,
//rem_book int
//);

