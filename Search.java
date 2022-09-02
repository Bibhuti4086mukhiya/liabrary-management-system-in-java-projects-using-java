package cw2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

class Search {
    // Returns index of x if it is present in arr[], else
    // return -1
   public static int binarySearch(ArrayList arr, String x)
    {
        int l = 0, r = arr.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            int res = x.compareTo((String) arr.get(m));

            // Check if x is present at mid
            if (res == 0)
                return m;

            // If x greater, ignore left half
            if (res > 0)
                l = m + 1;

                // If x is smaller, ignore right half
            else
                r = m - 1;
        }

        return -1;
    }



    public  ArrayList  allBooks() {
        Search se = new Search();
        ArrayList<String> ok= new ArrayList<String>();

        String query2 = "select * from books";
        DbConnect db = new DbConnect();

        try {

            ResultSet result = db.connection().executeQuery(query2);
            while (result.next()){

            String book_name = result.getString("book_name");
            ok.add(book_name);

            }
           Collections.sort(ok);
//            Collections.sort(ok, Collections.reverseOrder());
            System.out.println(ok);




    } catch (SQLException throwables) {
        throwables.printStackTrace();

    }
        return ok;



    }



}

